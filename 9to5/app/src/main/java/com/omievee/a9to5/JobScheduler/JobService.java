package com.omievee.a9to5.JobScheduler;

import android.app.job.JobParameters;

import com.omievee.a9to5.Weather.WeatherCreate;

/**
 * Created by omievee on 5/3/17.
 */

public class JobService extends android.app.job.JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        String cityQuery = "New York";
        WeatherCreate.getCityWeather(cityQuery, getApplicationContext());
        
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
