package com.kaptanas.simpleweather;

import android.app.ListActivity;
import android.os.Bundle;

import com.kaptanas.simpleweather.adapters.DailyAdapter;
import com.kaptanas.simpleweather.model.Forecast;


public class DailyForecastActivity extends ListActivity {
    Forecast mForecast=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        mForecast=(Forecast)getIntent().getSerializableExtra("forecast");
       if(mForecast!=null){
           DailyAdapter dailyAdapter = new DailyAdapter(this,mForecast);
           setListAdapter(dailyAdapter);
       }



    }



}
