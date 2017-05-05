package com.omievee.a9to5.Weather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.omievee.a9to5.AlertThrower;
import com.omievee.a9to5.MainActivity;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherCreate {

    private static final String TAG = "WeatherCreate";

    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String ID = "bfb4d3e098fa94eb0ea53de3c479236e";
    public static final String UNITS = "imperial";

    //working on this for the alerts

    public static void getCityWeather(final String cityQuery, final Context context, final boolean alert, final TextView local) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OpenWeatherService service = retrofit.create(OpenWeatherService.class);
            final Call<WeatherContainer> weatherCall = service.getWeather(ID, cityQuery, UNITS);

            // Log.d(TAG, "getCityWeather: " + weatherCall.request().toString());

            weatherCall.enqueue(new Callback<WeatherContainer>() {
                @Override
                public void onResponse(Call<WeatherContainer> call, Response<WeatherContainer> response) {

                    WeatherContainer weather = response.body();

                    if (weather == null) {
                        Log.d(TAG, "onResponse: " + weather);
                        //Toast.makeText(MainActivity.context, "City Unknown, Please try again", Toast.LENGTH_SHORT).show();
                    } else {
                        //TODO create object and add it to recyclerview
                        if(weather.getName().toLowerCase().contains(cityQuery.toLowerCase())){
                            MainActivity.sCityQuery=cityQuery;
                            if (alert == false) {

                                WeatherInfoObject mTemp = new WeatherInfoObject(
                                        weather.getName(), weather.getWeather().get(0).getDescription(),
                                        weather.getMain().getTemp(),
                                        weather.getMain().getTempMax(),
                                        weather.getMain().getTempMin());

                                InterfaceSingleton.getInstance().updateList(mTemp);

                                Log.d(TAG, "city: " + weather.getName());
                                Log.d(TAG, "description: " + weather.getWeather().get(0).getDescription());
                                Log.d(TAG, "temp: " + weather.getMain().getTemp());
                                Log.d(TAG, "temp: " + weather.getMain().getTempMax());
                                Log.d(TAG, "temp: " + weather.getMain().getTempMin());
                            } else {
                                String title = "Current Temp: " + String.format("%.1f\u2109", weather.getMain().getTemp());
                                String content = "Hi: " + String.format("%.1f\u2109", weather.getMain().getTempMax())
                                        + ", Low: " + String.format("%.1f\u2109", weather.getMain().getTempMax())
                                        + ", Current Conditions: " + weather.getWeather().get(0).getDescription();
                                AlertThrower.setAlert(context, title, content);
                            }
                        } else {
                            if (local!= null)
                            local.callOnClick();
                        }
                    }
                }

                @Override
                public void onFailure(Call<WeatherContainer> call, Throwable t) {
                    //Toast.makeText(MainActivity.context, "Sorry didn't work", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: ");
                    t.printStackTrace();
                }
            });
        } else {
            WeatherInfoObject mTemp = new WeatherInfoObject(false);
            //Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "getCityWeather: No network connection");
        }
    }
}
