package com.kaptanas.simpleweather;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private CurrentWeather currentWeather;
    private Location mCurrentLocation;
    WeatherService weatherService;

    @InjectView(R.id.timeLabel) TextView mTimeLabel;
    @InjectView(R.id.temperatureLabel) TextView mTemperatureLabel;
    @InjectView(R.id.humidityValue) TextView mHumidityValue;
    @InjectView(R.id.precipValue) TextView mPrecipValue;
    @InjectView(R.id.iconImageView) ImageView mIconImageView;
    @InjectView(R.id.refreshImageView) ImageView mRefreshImageView;
    @InjectView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mProgressBar.setVisibility(View.INVISIBLE);

        setLocation();
        setWeatherAdapter();
        weatherService.getCurrentWeather(mCurrentLocation.getLatitude()+"",mCurrentLocation.getLongitude()+"",new Callback<CurrentlyResponse>() {
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


        final Double latitude = mCurrentLocation.getLatitude();
        final Double longitude = mCurrentLocation.getLongitude();

        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(latitude, longitude);
            }
        });

        getForecast(latitude, longitude);

        Log.d(TAG, "Main UI code is running!");
    }

    private void setLocation() {
        LocationManager manager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (isGpsEnabled && !isNetworkEnabled) {
             mCurrentLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else {
             mCurrentLocation= manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
    }

    private void getForecast(double latitude, double longitude) {
        String apiKey = "7e6fd0a47433db6cc3a8f0159dc19746";
        String forecastUrl = "https://api.forecast.io/forecast/" + apiKey +
                "/" + latitude + "," + longitude;

        if (isNetworkAvailable()) {
            toggleRefresh();


        }
        else {
            Toast.makeText(this, getString(R.string.network_unavailable_message),
                    Toast.LENGTH_LONG).show();
        }
    }



    private void toggleRefresh() {
        if (mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        }
        else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay(CurrentWeather currentWeather) {
        mTemperatureLabel.setText(currentWeather.getCelcius() + "");
        mTimeLabel.setText("At " + currentWeather.getFormattedTime() + " it will be");
        mHumidityValue.setText(currentWeather.getHumidity() + "");
        mPrecipValue.setText(currentWeather.getPrecipProbability() + "%");

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


    private void setWeatherAdapter(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("https://api.forecast.io/forecast/7e6fd0a47433db6cc3a8f0159dc19746/")
                        .build();

        weatherService = restAdapter.create(WeatherService.class);
    }

    private void setAdressAdapter(){

    }

}














