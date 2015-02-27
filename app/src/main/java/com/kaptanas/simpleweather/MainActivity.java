package com.kaptanas.simpleweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.locationLabel)
    TextView mLocationLabel;

    private Location mCurrentLocation;
    WeatherService weatherService;
    Double latitude, longitude;
    List<Address> addresses = null;

    @InjectView(R.id.timeLabel)
    TextView mTimeLabel;
    @InjectView(R.id.temperatureLabel)
    TextView mTemperatureLabel;
    @InjectView(R.id.humidityValue)
    TextView mHumidityValue;
    @InjectView(R.id.precipValue)
    TextView mPrecipValue;
    @InjectView(R.id.iconImageView)
    ImageView mIconImageView;
    @InjectView(R.id.refreshImageView)
    ImageView mRefreshImageView;
    @InjectView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mProgressBar.setVisibility(View.INVISIBLE);





    }

    @Override
    protected void onStart() {
        super.onStart();
        setWeatherAdapter();

        setUiWithDatas();

        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUiWithDatas();
            }
        });

    }

    private void setUiWithDatas() {
        setLocation();
        if(mCurrentLocation != null && isNetworkAvailable()) {
            latitude = mCurrentLocation.getLatitude();
            longitude = mCurrentLocation.getLongitude();
            getAddresses(latitude, longitude);
            setUI();
        }else{
            if(!isNetworkAvailable()){
                showMaterialDialogNetwork();
            }else if(mCurrentLocation == null)
                showMaterialDialogLocation();
           // alertUserAboutError();
        }
    }


    private void setUI() {
        toggleRefresh();
        weatherService.getCurrentWeather(mCurrentLocation.getLatitude() + "", mCurrentLocation.getLongitude() + "", new Callback<CurrentlyResponse>() {
            @Override
            public void success(CurrentlyResponse currentlyResponse, Response response) {
                CurrentWeather currentWeather = currentlyResponse.getCurrently();
                currentWeather.setTimeZone(currentlyResponse.getTimezone());
                updateDisplay(currentWeather);
            }

            @Override
            public void failure(RetrofitError error) {
                alertUserAboutError();
            }
        });
    }

    private void getAddresses(Double latitude, Double longitude) {
        Geocoder gcd = new Geocoder(MainActivity.this, Locale.getDefault());

        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setLocation() {
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isGpsEnabled) {
            mCurrentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(mCurrentLocation == null)
                mCurrentLocation = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } else{
            mCurrentLocation = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }



    private void toggleRefresh() {
        if (mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay(CurrentWeather currentWeather) {
        toggleRefresh();
        mTemperatureLabel.setText(currentWeather.getCelcius() + "");
        mTimeLabel.setText("At " + currentWeather.getFormattedTime() + " it will be");
        mHumidityValue.setText(currentWeather.getHumidity() + "");
        mPrecipValue.setText(currentWeather.getPrecipProbability() + "%");
        if (addresses.size() > 0) {
            mLocationLabel.setText(addresses.get(0).getAddressLine(0));
        } else {
            mLocationLabel.setText("--");
        }

        Drawable drawable = getResources().getDrawable(currentWeather.getIconId());
        mIconImageView.setImageDrawable(drawable);


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }


    private void setWeatherAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.forecast.io/forecast/7e6fd0a47433db6cc3a8f0159dc19746/")
                .build();

        weatherService = restAdapter.create(WeatherService.class);
    }


    private void showMaterialDialogNetwork(){

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                       .title("Opss , Sorry!")
                       .content("Please check network settings.")
                       .positiveText("Wifi ")
                       .negativeText("Mobile")
                       .neutralText("Cancel")
                       .autoDismiss(true);
                       builder.callback(new MaterialDialog.ButtonCallback() {
                           @Override
                           public void onPositive(MaterialDialog dialog) {
                               super.onPositive(dialog);
                               startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);

                           }

                           @Override
                           public void onNegative(MaterialDialog dialog) {
                               super.onNegative(dialog);
//                               startActivityForResult(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS),0);
                               startActivityForResult(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS), 0);
                           }

                           @Override
                           public void onNeutral(MaterialDialog dialog) {
                               super.onNeutral(dialog);

                           }
                       });
                       builder.show();

    }

    private void showMaterialDialogLocation(){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Opss , Sorry!")
                .content("Please check Location and GPS settings.")
                .positiveText("Location Settings")
                .negativeText("Cancel")
                .autoDismiss(true);
        builder.callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                super.onPositive(dialog);
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);

            }

            @Override
            public void onNegative(MaterialDialog dialog) {
                super.onNegative(dialog);
            }


        });
        builder.show();
    }


}














