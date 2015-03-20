package com.kaptanas.simpleweather.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaptanas.simpleweather.R;
import com.kaptanas.simpleweather.model.Forecast;
import com.kaptanas.simpleweather.model.Hour;

import java.util.ArrayList;

/**
 * Created by Cihan on 19.03.2015.
 */
public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder> {
    private ArrayList<Hour> hours;
    public HourlyAdapter(Forecast forecast){
        hours = forecast.getHourly().getData();
    }

    @Override
    public HourlyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hourly_list_item,viewGroup,false);

        HourlyViewHolder hourlyViewHolder = new HourlyViewHolder(view);

        return hourlyViewHolder;
    }

    @Override
    public void onBindViewHolder(HourlyViewHolder hourlyViewHolder, int i) {
            hourlyViewHolder.bindHour(hours.get(i));
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class HourlyViewHolder extends RecyclerView.ViewHolder{
        TextView mTimeLabel;
        ImageView mIconImageView;
        TextView mTemperatureLabel;
        TextView mSummaryLabel;

        public HourlyViewHolder(View itemView) {
            super(itemView);

            mTimeLabel =(TextView)itemView.findViewById(R.id.timeLabel);
            mIconImageView =(ImageView)itemView.findViewById(R.id.iconImageView);
            mTemperatureLabel = (TextView)itemView.findViewById(R.id.temperatureLabel);
            mSummaryLabel = (TextView)itemView.findViewById(R.id.summaryLabel);
        }


        public void bindHour(Hour hour){
            mTimeLabel.setText(hour.getHour());
            mIconImageView.setImageResource(hour.getIconId());
            mTemperatureLabel.setText(hour.getCelcius());
            mSummaryLabel.setText(hour.getSummary());
        }
    }
}
