package com.kaptanas.simpleweather.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cihankaptan on 05/03/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HourlyResponse implements Serializable{

    private static final long serialVersionUID = -7253332674447281729L;

    private String summary;
    private String rain;

    private ArrayList<Hour> data;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public ArrayList<Hour> getData() {
        return data;
    }

    public void setData(ArrayList<Hour> data) {
        this.data = data;
    }
}
