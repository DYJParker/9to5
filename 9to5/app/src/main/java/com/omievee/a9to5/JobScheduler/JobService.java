package com.omievee.a9to5.JobScheduler;

import android.app.job.JobParameters;
import android.util.Log;

import com.omievee.a9to5.MainActivity;
import com.omievee.a9to5.Weather.WeatherCreate;

/**
 * Created by omievee on 5/3/17.
 */

public class JobService extends android.app.job.JobService {
    private static final String TAG = "JobService";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob ran");
        WeatherCreate.getCityWeather(MainActivity.sCityQuery, getApplicationContext(),false, null);
        jobFinished(params,true);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "onStopJob stopped!");
        return false;
    }
}
