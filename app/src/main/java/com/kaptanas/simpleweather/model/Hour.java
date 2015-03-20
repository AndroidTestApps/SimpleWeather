package com.kaptanas.simpleweather.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cihankaptan on 05/03/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Hour implements Serializable {

    private static final long serialVersionUID = 3500600554851648649L;

    private long time;
    private String summary;
    private double temperature;
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

    public int getTemperature() {
        return (int)Math.round(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public int getIconId(){
        return Forecast.getIcon(getIcon());
    }

    public String getHour(){
        SimpleDateFormat formatter = new SimpleDateFormat("H");
        Date date = new Date(getTime()*1000);
        return formatter.format(date)+":00";
    }

    public String getCelcius(){
        return (int)Math.round(((temperature - 32)*5)/9)+"";
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
}
