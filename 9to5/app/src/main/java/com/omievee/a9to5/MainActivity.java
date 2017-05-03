package com.omievee.a9to5;

import android.database.Cursor;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.Cardinfo;
import com.omievee.a9to5.RecyclerView.RECYAdapter;
import com.omievee.a9to5.Weather.OpenWeatherService;
import com.omievee.a9to5.Weather.Weather;
import com.omievee.a9to5.Weather.WeatherContainer;
import com.omievee.a9to5.Weather.WeatherCreate;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    //private static final String TAG = "MainActivity";
    private static final int CALENDAR_LOADER = 0;
    public static String cityQuery = "New York";


    RecyclerView mRV;
    RECYAdapter mAdapt;
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
        float dpWidth = metrics.widthPixels/metrics.density;
        mCardinfo = new ArrayList<>();
        mCardinfo.add(new Cardinfo("Test", "Test", "Test"));
        mRV = (RecyclerView) findViewById(R.id.RECY);
        RecyclerView.LayoutManager manager;
        if (dpWidth < 500 ) manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        else manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRV.setLayoutManager(manager);

        mAdapt = new RECYAdapter(new ArrayList<AbstractBaseInformationObject>());
        mRV.setAdapter(mAdapt);

        //mEditText.getText().toString();
//        if (cityQuery.trim().isEmpty()) {
//            //mEditText.setError("Pleas re-enter");
//        }
//        else {
//
//        }

        AlertThrower.setAlert(this);
        //WeatherCreate.getCityWeather(cityQuery, this);

    }
}





//Extra Bullshit////////////////////////////////////////////////////////////////////////////////////



/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dpWidth = metrics.widthPixels/metrics.density;

        //RecyclerView / LLM / Async Task
        mCardinfo = new ArrayList<>();
        mCardinfo.add(new Cardinfo("Test", "Test", "Test"));
        mRV = (RecyclerView) findViewById(R.id.RECY);
        RecyclerView.LayoutManager manager;
        if (dpWidth < 500 ) manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        else manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRV.setLayoutManager(manager);

        mAdapt = new RECYAdapter(new ArrayList<AbstractBaseInformationObject>());
        mRV.setAdapter(mAdapt);
//        mTask.execute();

        getSupportLoaderManager().initLoader(CALENDAR_LOADER, null, this);

        AlertThrower.setAlert(this);
    }

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

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CALENDAR_LOADER:
                //return CalendarCallbacks.onCreateCalendarLoader(this);
            default: return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            //mAdapt.addToList(CalendarCallbacks.onCalendarLoadFinished(data));
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
*/