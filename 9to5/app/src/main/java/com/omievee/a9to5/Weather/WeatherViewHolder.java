package com.omievee.a9to5.Weather;

import android.view.View;
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

    public WeatherViewHolder(View itemView) {
        super(itemView);

        mCityText = (TextView) itemView.findViewById(R.id.city_textview);
        mTemperatureText = (TextView) itemView.findViewById(R.id.temperature_textview);
        mDescriptionText = (TextView) itemView.findViewById(R.id.description_textview);

        //Add more if time allows
        //Highs and lows
        //icon
        //Edit Text for search
    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {

    }
}

