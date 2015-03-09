package com.kaptanas.simpleweather.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kaptanas.simpleweather.R;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by cihankaptan on 18/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Forecast implements Serializable{

    private static final long serialVersionUID = 3593017286271929636L;
    Current currently;
    DailyResponse daily;
    HourlyResponse hourly;
    String timezone;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public DailyResponse getDaily() {
        return daily;
    }

    public void setDaily(DailyResponse daily) {
        this.daily = daily;
    }

    public HourlyResponse getHourly() {
        return hourly;
    }

    public void setHourly(HourlyResponse hourly) {
        this.hourly = hourly;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Current getCurrently() {
        return currently;
    }

    public void setCurrently(Current currently) {
        this.currently = currently;
    }



    public static int getIcon(String icon){
        int iconId = R.drawable.clear_day;

        if (icon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (icon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (icon.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (icon.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (icon.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (icon.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (icon.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (icon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (icon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (icon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;
    }
}
