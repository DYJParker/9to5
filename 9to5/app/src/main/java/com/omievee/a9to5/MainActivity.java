package com.omievee.a9to5;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.omievee.a9to5.Calendar.CalendarCallbacks;
import com.omievee.a9to5.JobScheduler.JobService;
import com.omievee.a9to5.MTA_API.MTA_GetStatus;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;
import com.omievee.a9to5.RecyclerView.NetworkFailureImageHolder;
import com.omievee.a9to5.RecyclerView.NetworkFailureObject;
import com.omievee.a9to5.RecyclerView.RECYAdapter;
import com.omievee.a9to5.Weather.WeatherCreate;
import com.omievee.a9to5.Weather.WeatherInfoObject;
import com.omievee.a9to5.Weather.WeatherViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MainActivity";
    private static final int CALENDAR_LOADER = 0;
    public static String sCityQuery = "New York";

    //JobScheduler
    public static final int JOB_ID = 1;

    RecyclerView mRV;
    public RECYAdapter mAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    //        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //        setSupportActionBar(toolbar);

        //RecyclerView / LLM / Async Task
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dpWidth = metrics.widthPixels / metrics.density;

        mRV = (RecyclerView) findViewById(R.id.RECY);
        RecyclerView.LayoutManager manager;
        if (dpWidth < 500)
            manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        else
            manager = new StaggeredGridLayoutManager((int) dpWidth / 250, StaggeredGridLayoutManager.VERTICAL);
        mRV.setLayoutManager(manager);

        mAdapt = new RECYAdapter(new ArrayList<AbstractBaseInformationObject>());
        mRV.setAdapter(mAdapt);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            //Toast.makeText(MainActivity.this, "No network connection", Toast.LENGTH_SHORT).show();
            NetworkFailureObject temp = new NetworkFailureObject();
            InterfaceSingleton.getInstance().updateList(temp);
            //create new card
        }else {
            //things that need internet access here
            WeatherCreate.getCityWeather(sCityQuery, this, false);

            MTA_GetStatus.getMTAStatus(this);
        }


        //JobInfo job = new JobInfo.Builder(JOB_ID,
        //        new ComponentName(this, JobService.class))
        //        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        //        .setPeriodic(1000 * 60 * 10)
        //        .setBackoffCriteria(1000 * 60 * 10, JobInfo.BACKOFF_POLICY_LINEAR)
        //        .build();
        //((JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE)).schedule(job);

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CALENDAR_LOADER:
                return CalendarCallbacks.onCreateCalendarLoader(this);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            InterfaceSingleton.getInstance().updateList(CalendarCallbacks.onCalendarLoadFinished(data));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        ((JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE)).cancel(JOB_ID);
    }
}
