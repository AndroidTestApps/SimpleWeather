package com.kaptanas.simpleweather.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cihankaptan on 05/03/15.
 */
public class DailyResponse implements Serializable {

    private static final long serialVersionUID = -5231593325403796157L;

    private String summary;
    private String icon;
    private ArrayList<Day> data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArrayList<Day> getData() {
        return data;
    }

    public void setData(ArrayList<Day> data) {
        this.data = data;
    }


}
