package com.kaptanas.simpleweather;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kaptanas.simpleweather.adapters.HourlyAdapter;
import com.kaptanas.simpleweather.model.Forecast;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class HourlyForecastActivity extends ActionBarActivity {
    Forecast mForecast = null;
    @InjectView(R.id.reyclerView)
    RecyclerView reyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);
        ButterKnife.inject(this);

        mForecast = (Forecast) getIntent().getSerializableExtra("forecast");
        if (mForecast != null) {
            HourlyAdapter hourlyAdapter = new HourlyAdapter(mForecast);
            reyclerView.setAdapter(hourlyAdapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            reyclerView.setLayoutManager(layoutManager);

        }

    }
}
