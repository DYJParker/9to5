package com.omievee.a9to5.Weather;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

/**
 * Created by justinstanger on 5/2/17.
 */



public class WeatherViewHolder extends AbstractBaseHolder{
    TextView mCityText;
    TextView mTemperatureText;
    TextView mDescriptionText;
    TextView mHiText;
    TextView mLowText;

    public WeatherViewHolder(View itemView) {
        super(itemView);

        View content = LayoutInflater.from(itemView.getContext()).inflate(R.layout.weather_cardview, null);
        //Don't need to fix if we change the layout, because it will be a View.


        mCityText = (TextView) content.findViewById(R.id.city_textview);
        mTemperatureText = (TextView) content.findViewById(R.id.temperature_textview);
        mDescriptionText = (TextView) content.findViewById(R.id.description_textview);

        mHiText = (TextView) content.findViewById(R.id.hi_textview);
        mLowText = (TextView) content.findViewById(R.id.low_textview);

        ((CardView) itemView).addView(content,0);

        //Add more if time allows
        //Highs and lows
        //icon
        //Edit Text for search

    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {
        WeatherInfoObject localData = (WeatherInfoObject) data;

        mCityText.setText(localData.getCity());
        mTemperatureText.setText(String.format("%.1f\u2109",localData.getTemperature()));
        mDescriptionText.setText(localData.getDescription());

        mHiText.setText("Hi: " + String.format("%.1f\u2109",localData.getHi()));
        mLowText.setText("Low: " + String.format("%.1f\u2109",localData.getLow()));
    }
}

