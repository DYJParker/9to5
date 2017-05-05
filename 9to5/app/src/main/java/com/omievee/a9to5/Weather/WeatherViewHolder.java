package com.omievee.a9to5.Weather;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.omievee.a9to5.MainActivity;
import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

import static android.R.id.content;
import static android.view.View.GONE;
import static com.omievee.a9to5.MainActivity.sCityQuery;

/**
 * Created by justinstanger on 5/2/17.
 */

public class WeatherViewHolder extends AbstractBaseHolder {
    private static final String TAG = "WeatherViewHolder";
    TextView mCityText;
    TextView mTemperatureText;
    TextView mDescriptionText;
    TextView mHiText;
    TextView mLowText;
    TextView mNetworkDown;

    public WeatherViewHolder(View itemView) {
        super(itemView);

        View content = LayoutInflater.from(itemView.getContext()).inflate(R.layout.weather_cardview, null);
        //Don't need to fix if we change the layout, because it will be a View.

        mCityText = (TextView) content.findViewById(R.id.city_textview);
        mTemperatureText = (TextView) content.findViewById(R.id.temperature_textview);
        mDescriptionText = (TextView) content.findViewById(R.id.description_textview);

        mHiText = (TextView) content.findViewById(R.id.hi_textview);
        mLowText = (TextView) content.findViewById(R.id.low_textview);

        mNetworkDown = (TextView) content.findViewById(R.id.weather_not_conected);

        ((CardView) itemView).addView(content, 0);

        //Add more if time allows
        //Highs and lows
        //icon
        //Edit Text for search
    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {
        WeatherInfoObject localData = (WeatherInfoObject) data;

        if (localData.getConnection()) {
            mCityText.setText(localData.getCity());
            mTemperatureText.setText(String.format("%.1f\u2109", localData.getTemperature()));
            mDescriptionText.setText(localData.getDescription());

            mHiText.setText("Hi: " + String.format("%.1f\u2109", localData.getHi()));
            mLowText.setText("Low: " + String.format("%.1f\u2109", localData.getLow()));


            mCityText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    LayoutInflater inflater = LayoutInflater.from(v.getContext());

                    View view = inflater.inflate(R.layout.custom_change_city, null);
                    builder.setView(view);
                    final EditText mEdit = (EditText) view.findViewById(R.id.edit_find_city);

                    builder.setPositiveButton("submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            String q = mEdit.getText().toString();
                            Log.d(TAG, "onClick: " + q);
                            WeatherCreate.getCityWeather(q, v.getContext().getApplicationContext(), false);

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        } else {
            ((View) mCityText.getParent()).setVisibility(GONE);
            ((View) mDescriptionText.getParent()).setVisibility(GONE);
            mNetworkDown.setVisibility(View.VISIBLE);
        }
    }
}








