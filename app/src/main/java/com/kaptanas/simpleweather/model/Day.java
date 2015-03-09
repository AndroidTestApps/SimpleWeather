package com.kaptanas.simpleweather.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by cihankaptan on 05/03/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Day implements Serializable {

    private static final long serialVersionUID = -1308542339937338436L;

    private long time;
    private String summary;
    private double temperatureMax;
    private String icon;
    private String timezone;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getTemperatureMax() {
        return (int)Math.round(temperatureMax);
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getIconId(){
        return Forecast.getIcon(icon);
    }

    public String getDayOfTheWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        Date dateTime = new Date(time * 1000);
        return formatter.format(dateTime);
    }
}
