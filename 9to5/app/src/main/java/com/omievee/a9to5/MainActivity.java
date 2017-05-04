package com.omievee.a9to5;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;

import com.omievee.a9to5.JobScheduler.JobService;
import com.omievee.a9to5.MTA_API.MTA_GetStatus;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.Cardinfo;
import com.omievee.a9to5.RecyclerView.RECYAdapter;
import com.omievee.a9to5.Weather.WeatherCreate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    //private static final String TAG = "MainActivity";
    private static final int CALENDAR_LOADER = 0;

    public static String sCityQuery = "New York";

    //JobScheduler
    public static final int JOB_ID = 1;

    RecyclerView mRV;
    public RECYAdapter mAdapt;
    List<AbstractBaseInformationObject> mCardinfo;

    public static final String TAG = "TEST TEST TEST ";

    public static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String ID = "bfb4d3e098fa94eb0ea53de3c479236e";
    public static final String UNITS = "imperial";

    public RECYAdapter getmAdapt() {
        return mAdapt;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        System.out.print("LOADER is being called");

        return null;

        //Check Dave's Shit
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //Check Dave's Shit

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //Check Dave's Shit

    }

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
        mCardinfo = new ArrayList<>();
        mCardinfo.add(new Cardinfo("Test", "Test", "Test"));
        mRV = (RecyclerView) findViewById(R.id.RECY);
        RecyclerView.LayoutManager manager;
        if (dpWidth < 500)
            manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        else
            manager = new StaggeredGridLayoutManager((int) dpWidth / 250, StaggeredGridLayoutManager.VERTICAL);
        mRV.setLayoutManager(manager);

        mAdapt = new RECYAdapter(new ArrayList<AbstractBaseInformationObject>());
        mRV.setAdapter(mAdapt);

        WeatherCreate.getCityWeather(sCityQuery, this, false);

        getSupportLoaderManager().initLoader(CALENDAR_LOADER, null, this);

        JobInfo job = new JobInfo.Builder(JOB_ID,
                new ComponentName(this, JobService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(1000 * 60 * 10)
                .setBackoffCriteria(1000 * 60 * 10, JobInfo.BACKOFF_POLICY_LINEAR)
                .build();
        ((JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE)).schedule(job);

        AlertThrower.setAlert(this, "testTitle", "testContent");

        MTA_GetStatus.getMTAStatus(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        ((JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE)).cancel(JOB_ID);
    }
}
