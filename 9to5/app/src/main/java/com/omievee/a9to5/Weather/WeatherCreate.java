package com.omievee.a9to5.Weather;

import android.database.Cursor;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.omievee.a9to5.MainActivity;
import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.Cardinfo;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;
import com.omievee.a9to5.RecyclerView.RECYAdapter;
import com.omievee.a9to5.Weather.OpenWeatherService;
import com.omievee.a9to5.Weather.WeatherContainer;

import java.util.ArrayList;
import java.util.List;

public class WeatherCreate{

    public static final String TAG = "TEST TEST TEST ";

    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String ID = "bfb4d3e098fa94eb0ea53de3c479236e";
    public static final String UNITS = "imperial";

/*
working on this for the alerts
    public static ArrayList<String> getCityWeatherAlert(String cityQuery, final Context context) {
        final ArrayList<String> arr = new ArrayList<String>();

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OpenWeatherService service = retrofit.create(OpenWeatherService.class);
            final Call<WeatherContainer> weatherCall = service.getWeather(ID, cityQuery, UNITS);


            weatherCall.enqueue(new Callback<WeatherContainer>() {
                @Override
                public void onResponse(Call<WeatherContainer> call, Response<WeatherContainer> response) {


                    WeatherContainer weather = response.body();


                    if (weather == null) {
                        Log.d(TAG, "onResponse: " + weather);
                        //Toast.makeText(MainActivity.context, "City Unknown, Please try again", Toast.LENGTH_SHORT).show();
                        arr.add(1, "Error");
                    } else {
                        //TODO create object and add it to recyclerview

                        String title = "Current Temp: " + String.format("%.1f\u2109", weather.getMain().getTemp());
                        String content = "Hi: " + String.format("%.1f\u2109",weather.getMain().getTempMax())
                                + ", Low: " + String.format("%.1f\u2109",weather.getMain().getTempMax())
                                + ", Current Conditions: " + weather.getWeather().get(0).getDescription();

                        arr.add(1, content);
                        arr.add(2,content);
                        Log.d(TAG, title);
                        Log.d(TAG, content);

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
            Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show();
        }
        return arr;
    }
*/
        public static void getCityWeather(String cityQuery, final Context context) {

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

                        WeatherInfoObject mTemp = new WeatherInfoObject(
                                weather.getName(),
                                weather.getWeather().get(0).getDescription(),
                                weather.getMain().getTemp(),
                                weather.getMain().getTempMax(),
                                weather.getMain().getTempMin());



                        InterfaceSingleton.getInstance().updateList(mTemp);

                        Log.d(TAG, "city: " + weather.getName());
                        Log.d(TAG, "description: " + weather.getWeather().get(0).getDescription());
                        Log.d(TAG, "temp: " +  weather.getMain().getTemp());
                        Log.d(TAG, "temp: " +  weather.getMain().getTempMax());
                        Log.d(TAG, "temp: " +  weather.getMain().getTempMin());

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
            Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show();
        }
    }
}
