package com.kaptanas.simpleweather;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by cihankaptan on 18/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CurrentlyResponse implements Serializable{

    private static final long serialVersionUID = 3593017286271929636L;
    CurrentWeather currently;
    String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public CurrentWeather getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentWeather currently) {
        this.currently = currently;
    }
}
