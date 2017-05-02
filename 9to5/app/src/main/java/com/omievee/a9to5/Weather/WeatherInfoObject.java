package com.omievee.a9to5.Weather;

import android.widget.TextView;

import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

/**
 * Created by justinstanger on 5/1/17.
 */

public class WeatherInfoObject extends AbstractBaseInformationObject {
private String mCity, mDescription;
private Double mTemperature;


    public WeatherInfoObject(String city, String description, Double tempurature){
        mCity = city;
        mDescription = description;
        mTemperature = tempurature;
    }
    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double mTemperature) {
        this.mTemperature = mTemperature;
    }
}
