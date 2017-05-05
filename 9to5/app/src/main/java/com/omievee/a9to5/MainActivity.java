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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.omievee.a9to5.Calendar.CalendarCallbacks;
import com.omievee.a9to5.JobScheduler.JobService;
import com.omievee.a9to5.MTA_API.MTA_GetStatus;
import com.omievee.a9to5.NEWS.NEWS_OBJECT;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;
import com.omievee.a9to5.RecyclerView.RECYAdapter;
import com.omievee.a9to5.Weather.WeatherCreate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MainActivity";
    private static final int CALENDAR_LOADER = 0;

    public static String sCityQuery = "New York";

    //JobScheduler
    public static final int JOB_ID = 1;

    //news
    public static final String URL_V = "https://newsapi.org/v1/articles?source=al-jazeera-english&sortBy=top&apiKey=f12a4585dde14bd39a8e0405c0925671";



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

        WeatherCreate.getCityWeather(sCityQuery, this, false);

        getSupportLoaderManager().initLoader(CALENDAR_LOADER, null, this);

        //JobInfo job = new JobInfo.Builder(JOB_ID,
        //        new ComponentName(this, JobService.class))
        //        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        //        .setPeriodic(1000 * 60 * 10)
        //        .setBackoffCriteria(1000 * 60 * 10, JobInfo.BACKOFF_POLICY_LINEAR)
        //        .build();
        //((JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE)).schedule(job);

        MTA_GetStatus.getMTAStatus(this);
        volleyNEWS();
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

    public void volleyNEWS() {

        RequestQueue request = Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, URL_V
                , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            NEWS_OBJECT news = new NEWS_OBJECT();

                            JSONObject root = response;
                            JSONArray articles = root.getJSONArray("articles");
                            for (int i = 0; i < articles.length(); i++) {
                                JSONObject article = articles.getJSONObject(i);
                                news.addArticles(
                                        article.getString("title"),
                                        article.getString("description"),
                                     article.getString("urlToImage")

                                );
                                Log.d(TAG, "onResponse: " + article);

                            }
                            InterfaceSingleton.getInstance().updateList(news);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        request.add(jsonArrayRequest);

    }


}
