package com.kaptanas.simpleweather.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaptanas.simpleweather.R;
import com.kaptanas.simpleweather.model.Day;
import com.kaptanas.simpleweather.model.Forecast;

/**
 * Created by cihankaptan on 09/03/15.
 */
public class DailyAdapter extends BaseAdapter {
    Activity mActivity;
    Forecast forecast;
    public DailyAdapter(Activity activity,Forecast forecast){
        mActivity=activity;
        this.forecast = forecast;
    }
    @Override
    public int getCount() {
        return forecast.getDaily().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return forecast.getDaily().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.daily_list_item,null);
            holder = new ViewHolder();
            holder.iconLabel = (ImageView)convertView.findViewById(R.id.iconImageView);
            holder.dayLabel =(TextView)convertView.findViewById(R.id.dayNameLabel);
            holder.temperatureLabel=(TextView)convertView.findViewById(R.id.temperatureLabel);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = forecast.getDaily().getData().get(position);
        holder.iconLabel.setImageResource(day.getIconId());
        holder.temperatureLabel.setText(day.getCelcius()+"");
        holder.dayLabel.setText(day.getDayOfTheWeek(forecast.getTimezone()));
        return convertView;
    }


    public static class ViewHolder{
        ImageView iconLabel;
        TextView temperatureLabel;
        TextView dayLabel;
    }
}
