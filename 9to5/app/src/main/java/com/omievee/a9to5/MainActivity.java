package com.omievee.a9to5;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.omievee.a9to5.RecyclerView.Cardinfo;
import com.omievee.a9to5.RecyclerView.RECYAdapter;

import java.util.ArrayList;
import java.util.List;

import com.omievee.a9to5.Calendar.CalendarEventInstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.provider.CalendarContract.Instances.*;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MainActivity";
    private static final int CALENDAR_LOADER = 0;

    RecyclerView mRV;
    RECYAdapter mAdapt;
    List<Cardinfo> mCardinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //RecyclerView / LLM / Async Task
        mCardinfo = new ArrayList<>();
        mCardinfo.add(new Cardinfo("a","b","c"));
        mRV = (RecyclerView) findViewById(R.id.RECY);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRV.setLayoutManager(manager);


        mAdapt = new RECYAdapter(mCardinfo);
        mRV.setAdapter(mAdapt);
//        mTask.execute();


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
        if (id == CALENDAR_LOADER) {
            return new CursorLoader(this,
                    CONTENT_URI,
                    new String[]{_ID, BEGIN, END, DESCRIPTION, CALENDAR_COLOR},
                    BEGIN + " > ?",
                    new String[]{Long.toString(Calendar.getInstance().getTimeInMillis())},
                    BEGIN + " asc");
        } else return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data!= null && data.moveToFirst()){
            for (int i = 0; i < 3; i++) {
                List<CalendarEventInstance> events = new ArrayList();
                Log.d(TAG, "onLoadFinished: " + data.getLong(data.getColumnIndex(_ID)));
                Log.d(TAG, "onLoadFinished: " + data.getString(data.getColumnIndex(DESCRIPTION)));
                data.moveToNext();
                if(data.isAfterLast()) break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    //AsyncTask loading Card info
    private AsyncTask mTask = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {

            return params;
        }
    };

}
