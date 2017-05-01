package com.omievee.a9to5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TEST TEST TEST ";

    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String ID = "bfb4d3e098fa94eb0ea53de3c479236e";
    public static final String UNITS = "imperial";

    TextView mCityText;
    TextView mTemperatureText;
    TextView mDescriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mEditText = (EditText) findViewById(R.id.query_text);

        mCityText = (TextView) findViewById(R.id.city_textview);
        mTemperatureText = (TextView) findViewById(R.id.temperature_textview);
        mDescriptionText = (TextView) findViewById(R.id.description_textview);

        //mButton.setOnClickListener(new View.OnClickListener() {

        String cityQuery = "New York";
                        //mEditText.getText().toString();
        if (cityQuery.trim().isEmpty()) {
            //mEditText.setError("Pleas re-enter");
                } else {
                    getCityWeather(cityQuery);

                }
            }

    protected void getCityWeather(String cityQuery) {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OpenWeatherService service = retrofit.create(OpenWeatherService.class);
            Call<WeatherContainer> weatherCall = service.getWeather(ID, cityQuery, UNITS);
//            Log.d(TAG, "getCityWeather: " + weatherCall.request().toString());

            weatherCall.enqueue(new Callback<WeatherContainer>() {
                @Override
                public void onResponse(Call<WeatherContainer> call, Response<WeatherContainer> response) {

                    WeatherContainer weather = response.body();


                    if (weather == null) {
                        Log.d(TAG, "onResponse: " + weather);
                        Toast.makeText(MainActivity.this, "City Unknown, Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        mCityText.setText(String.format("City: "+ weather.getName()));
                        mTemperatureText.setText(String.format("Weather: "+ weather.getMain().getTemp()));
                        mDescriptionText.setText(String.format("Description: "+ weather.getWeather().get(0).getDescription()));
                    }
                }

                @Override
                public void onFailure(Call<WeatherContainer> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Sorry didn't work", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: ");
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
        }
    }

    /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
