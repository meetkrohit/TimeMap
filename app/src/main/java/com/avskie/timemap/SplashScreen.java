package com.avskie.timemap;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avskie.timemap.utils.GPSTracker;
import com.avskie.timemap.utils.InternetConnection;
import com.avskie.timemap.utils.Prefs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SplashScreen extends Activity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private static final int REQUEST_CONTACT = 2;
    private static final int REQUEST_USAGE = 3;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final String TAG = "LocationManagerActivity";
    String mLastUpdateTime;
    Location mCurrentLocation;
    private String address = null;
    private String city = null;
    private TextView skip;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_CALL_PHONE = 2;
    AnimatorSet set;
    GPSTracker gpsTracker;
    ProgressBar progressBar;
    boolean isProcessRuning_ = false;
    InternetConnection myConnection;
    Boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.progressBar);
        mGoogleApiClient = new GoogleApiClient.Builder(SplashScreen.this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();



    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(SplashScreen.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(SplashScreen.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }

        } else {

            processStart();
        }
    }


    private void requestCallPermison() {

        int checkPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_CONTACTS)) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        REQUEST_CONTACT);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        REQUEST_CONTACT);
            }

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashScreen.this, SignUpActivity.class));

                }
            }, SPLASH_DISPLAY_LENGTH);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateUI();
                    requestCallPermison();
                } else {
                    //AppConstant.saveDataWithKeyAndValue(getApplicationContext(), "address", address);
                    //AppConstant.saveDataWithKeyAndValue(getApplicationContext(), "city", city);
                    SplashScreen.this.finish();
                    // Snackbar.make(layout,"Permission Denied, You cannot access location data.",Snackbar.LENGTH_LONG).show();
                }
                break;
            case REQUEST_CONTACT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    processStart();


                } else {
                    //AppConstant.saveDataWithKeyAndValue(getApplicationContext(), "address", address);
                    //AppConstant.saveDataWithKeyAndValue(getApplicationContext(), "city", city);
                    SplashScreen.this.finish();
                    // Snackbar.make(layout,"Permission Denied, You cannot access location data.",Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();
                final LocationSettingsStates LS_state = locationSettingsResult.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        //updateUI();
                        //processStart();
                        checkPermission();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                        try {
                            status.startResolutionForResult(SplashScreen.this, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        //updateUI();
                        //processStart();
                        checkPermission();
                        break;
                    case Activity.RESULT_CANCELED:
                        //AppConstant.saveDataWithKeyAndValue(getApplicationContext(), "address", address);
                        //AppConstant.saveDataWithKeyAndValue(getApplicationContext(), "city", city);
                        SplashScreen.this.finish();

                        break;
                    default:
                        break;
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mGoogleApiClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        try {
            mGoogleApiClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }


    @Override
    public void onConnected(Bundle bundle) {

        //Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        //startLocationUpdates();
        createLocationRequest();

    }

    protected void startLocationUpdates() {
        /*if (Build.VERSION.SDK_INT >= 23) {
			if (ContextCompat.checkSelfPermission(SplashScreen.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
					ContextCompat.checkSelfPermission(SplashScreen.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				requestPermission();
			}

		}else {
			PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
					mGoogleApiClient, mLocationRequest, this);
		}*/
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, (LocationListener) this);
        Log.d(TAG, "Location update started ..............: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        }
        updateUI();

    }

    private void updateUI() {
        Log.d(TAG, "UI update initiated .............");

        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());

            System.out.println("At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation.getProvider());

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            double latitude = mCurrentLocation.getLatitude();
            double longitude = mCurrentLocation.getLongitude();

            try {

                addresses = geocoder.getFromLocation(latitude, longitude, 3);

                if (addresses != null && addresses.size() > 0) {
                    for (Address adr : addresses) {
                        if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                            city = adr.getLocality();
                            address = adr.getAddressLine(0);
                            break;
                        }
                    }

                    System.out.println(address + " " + city);
                    System.out.println(address + " " + addresses);

                    Prefs.saveDataWithKeyAndValue(getApplicationContext(), "address", address);
                    Prefs.saveDataWithKeyAndValue(getApplicationContext(), "city", city);

                    Prefs.saveDataWithKeyAndValue(getApplicationContext(), "latitude", String.valueOf(latitude));
                    Prefs.saveDataWithKeyAndValue(getApplicationContext(), "longitude", String.valueOf(longitude));


                }
                //processStart();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            Log.d(TAG, "location is null ...............");
        }
    }


    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(SplashScreen.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(SplashScreen.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermission();//processStart();
            } else {
                processStart();
            }

        } else {
            processStart();
        }
    }

    public void processStart() {
        stopLocationUpdates();
        progressBar.setVisibility(View.VISIBLE);

        isProcessRuning_ = false;
        myConnection = new InternetConnection(SplashScreen.this);
        isInternetPresent = myConnection.isConnectingToInternet();
        if (!isInternetPresent) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(SplashScreen.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        } else {
            //txt_internet.setVisibility(View.GONE);

            startActivity(new Intent(SplashScreen.this, SignUpActivity.class));
            finish();
        }
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, (LocationListener) this);
        Log.d(TAG, "Location update stopped .......................");
    }
}
