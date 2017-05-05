package com.omievee.a9to5;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;

import com.omievee.a9to5.Calendar.CalendarCallbacks;
import com.omievee.a9to5.JobScheduler.JobService;
import com.omievee.a9to5.MTA_API.MTA_GetStatus;
import com.omievee.a9to5.News.News_Fetcher;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;
import com.omievee.a9to5.RecyclerView.NetworkFailureObject;
import com.omievee.a9to5.RecyclerView.RECYAdapter;
import com.omievee.a9to5.Weather.WeatherCreate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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

        //RecyclerView / LLM
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dpWidth = metrics.widthPixels / metrics.density;

        mRV = (RecyclerView) findViewById(R.id.RECY);
        RecyclerView.LayoutManager manager;
        if (dpWidth < 500)
            manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        else
            manager = new StaggeredGridLayoutManager(/*(int) (dpWidth / 250)*/2, StaggeredGridLayoutManager.VERTICAL);
        mRV.setLayoutManager(manager);

        mAdapt = (RECYAdapter) InterfaceSingleton.getInstance().getListener();
        if (mAdapt == null) {
            mAdapt = new RECYAdapter(new ArrayList<AbstractBaseInformationObject>());
        }
        mRV.setAdapter(mAdapt);

        CalendarCallbacks callbacks = new CalendarCallbacks(this);
        getSupportLoaderManager().initLoader(CALENDAR_LOADER, null, callbacks);

        AlertThrower.timeListener(this.getApplicationContext(), null);

        JobInfo job = new JobInfo.Builder(JOB_ID,
                new ComponentName(this, JobService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(1000 * 60 * 10)
                .setBackoffCriteria(1000 * 60 * 10, JobInfo.BACKOFF_POLICY_LINEAR)
                .build();
        ((JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE)).schedule(job);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (((RECYAdapter) InterfaceSingleton.getInstance().getListener()).getItemCount() == 0) {
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected()) {
                //Toast.makeText(MainActivity.this, "No network connection", Toast.LENGTH_SHORT).show();
                NetworkFailureObject temp = new NetworkFailureObject();
                InterfaceSingleton.getInstance().updateList(temp);
                //create new card
            } else {
                //things that need internet access here
                WeatherCreate.getCityWeather(sCityQuery, this, false, null);
                MTA_GetStatus.getMTAStatus(this);
                News_Fetcher.volleyNEWS(this);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        ((JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE)).cancel(JOB_ID);
    }
}
