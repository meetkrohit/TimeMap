package com.avskie.timemap;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;

import com.avskie.timemap.utils.AppLocationService;
import com.avskie.timemap.utils.LocationAddress;
import com.hbb20.CountryCodePicker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class SignUpActivity extends Activity{

    CountryCodePicker country_code;
    TextInputEditText phone_number, user_name, email_id, user_location;
    private LocationManager locationManager;
    private String provider;
    AppLocationService appLocationService;
    Geocoder geocoder;
    List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        country_code = findViewById(R.id.ccp);
        phone_number = findViewById(R.id.phone_number);
        user_name = findViewById(R.id.user_name);
        email_id = findViewById(R.id.email_id);
        user_location = findViewById(R.id.user_location);

        appLocationService = new AppLocationService(
                SignUpActivity.this);
        Location gpsLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);
        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();

            geocoder = new Geocoder(this, Locale.getDefault());

            try {

                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                String result = address + ", " + city + ", " + state + ", " + country + ", " + postalCode;
                user_location.setText(address);
            } catch (IOException e) {
                e.printStackTrace();
            }



           /* LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getApplicationContext(), new GeocoderHandler());*/

        } else {
            showSettingsAlert();
        }

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                email_id.setText(possibleEmail);
            }
        }

    }

    public void doSignUp(View view) {
        isValidMail(email_id.getText().toString());
        isValidMobile(phone_number.getText().toString());
        startActivity(new Intent(this, MainActivity.class));
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                SignUpActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        SignUpActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            user_location.setText(locationAddress);
        }
    }
}

