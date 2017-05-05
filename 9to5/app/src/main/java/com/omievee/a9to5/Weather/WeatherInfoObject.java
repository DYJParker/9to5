package com.omievee.a9to5.Weather;

import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

/**
 * Created by justinstanger on 5/1/17.
 */

public class WeatherInfoObject extends AbstractBaseInformationObject {
    private String mCity, mDescription;
    private Double mTemperature, mHi, mLow;
    private boolean mConnected = true;

    public WeatherInfoObject(String city, String description, Double tempurature, Double hi, Double low) {
        mCity = city;
        mDescription = description;
        mTemperature = tempurature;
        mHi = hi;
        mLow = low;
    }

    public WeatherInfoObject(boolean ifConnected){
        mConnected = ifConnected;
        //everything else is null.
    }

    public boolean getConnection(){
        return mConnected;
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

    public Double getHi() {
        return mHi;
    }

    public void setHi(Double mHi) {
        this.mHi = mHi;
    }

    public Double getLow() {
        return mLow;
    }

    public void setLow(Double mTemperature) {
        this.mTemperature = mTemperature;
    }
}
