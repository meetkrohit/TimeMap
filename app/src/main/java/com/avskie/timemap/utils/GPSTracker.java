package com.avskie.timemap.utils;


import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GPSTracker extends Service implements LocationListener {

	private final Context mContext;

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	Location location = null; // location
	double latitude; // latitude
	double longitude; // longitude
	Dialog alert_dialog_ = null;
	private TextView alert_dlgTextTV;

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

	// Declaring a Location Manager
	protected LocationManager locationManager;


	public GPSTracker(Context context) {
		this.mContext = context;
		getLocation();
		/*alert_dialog_ = new Dialog(context);
		alert_dialog_.requestWindowFeature(Window.FEATURE_NO_TITLE);
		alert_dialog_.setCancelable(false);
		alert_dialog_.setContentView(R.layout.alert_dialog);
		alert_dlgTextTV = (TextView) alert_dialog_.findViewById(R.id.txt_dia);*/
	}

	public Location getLocation() {
		try {

			if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED	)
				return location;



			locationManager = (LocationManager) mContext
					.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			//isNetworkEnabled = false;

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {


				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
						this.canGetLocation = true;
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
							this.canGetLocation = true;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return location;
	}
	
	/**
	 * Stop using GPS listener
	 * Calling this function will stop using GPS in your app
	 * */
	/*public void stopUsingGPS(){
		if(locationManager != null){
			locationManager.removeUpdates(GPSTracker.this);
		}		
	}*/
	
	/**
	 * Function to get latitude
	 * */
	public double getLatitude(){
		if(location != null){
			latitude = location.getLatitude();
		}
		
		// return latitude
		return latitude;
	}
	
	/**
	 * Function to get longitude
	 * */
	public double getLongitude(){
		if(location != null){
			longitude = location.getLongitude();
		}
		
		// return longitude
		return longitude;
	}
	public List<Address> getAddress(){
		List<Address> addresses = null;
        StringBuilder builder = new StringBuilder();
        String fnialAddress = null;
		if(location != null) {
            Geocoder gcd = new Geocoder(this.mContext, Locale.getDefault());
            try {
                addresses = gcd.getFromLocation(latitude, longitude, 3);
                int maxLines = addresses.get(0).getMaxAddressLineIndex();
                for (int i = 0; i < maxLines; i++) {
                    String addressStr = addresses.get(0).getAddressLine(i);
                    builder.append(addressStr);
                    builder.append(" ");
				/*addresses = gcd.getFromLocation(location.getLatitude(),
						location.getLongitude(), 1);

				while (addresses.size()==0) {
					addresses = gcd.getFromLocation(location.getLatitude(),
							location.getLongitude(), 1);
					System.out.println("address==" + addresses);
				}*/

                }
                fnialAddress = builder.toString();
                Log.e("Add",fnialAddress);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
		return addresses;
	}

    public String getAddressLocality(){
        String fnialAddress = null;
        String add = null;
        Geocoder geoCoder = new Geocoder(mContext, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geoCoder.getFromLocation(latitude, longitude, 5);
            if (address != null && address.size() > 0) {

				for(Address adr : address)
				{
					if (adr.getLocality() != null && adr.getLocality().length() > 0)
					{
						fnialAddress = adr.getLocality();
						break;
					}
				}
				//int maxLines = address.get(0).getMaxAddressLineIndex();
                //Address addr = address.get(0);
                //builder.append(addr.getLocality());
	        }
	      	//fnialAddress = builder.toString();

           //This is the complete address.
        } catch (IOException e) {
           e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return fnialAddress;
    }
	/**
	 * Function to check GPS/wifi enabled
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return this.canGetLocation;
	}
	
	/**
	 * Function to show settings alert dialog
	 * On pressing Settings button will lauch Settings Options
	 * */



	public void showSettingsAlert(){
		/*alert_dlgTextTV.setText("Device location seems to be disabled.\nDo you want to enable it?");
		Button btn_yes = (Button) alert_dialog_.findViewById(R.id.btn_yes);
		Button btn_no = (Button) alert_dialog_.findViewById(R.id.btn_no);

		btn_yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alert_dialog_.dismiss();
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mContext.startActivity(intent);

			}
		});

		btn_no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alert_dialog_.dismiss();

			}
		});
		alert_dialog_.show();*/

	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
