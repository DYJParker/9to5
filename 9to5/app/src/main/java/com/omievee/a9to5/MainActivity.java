package com.omievee.a9to5;

import android.database.Cursor;
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

import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.RECYAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "MainActivity";
    private static final int CALENDAR_LOADER = 0;

    RecyclerView mRV;
    RECYAdapter mAdapt;
    List<AbstractBaseInformationObject> mCardinfo;

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

            }
        });

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float dpWidth = metrics.widthPixels/metrics.density;

        //RecyclerView / LLM / Async Task
        mRV = (RecyclerView) findViewById(R.id.RECY);
        RecyclerView.LayoutManager manager;
        if (dpWidth < 500 ) manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        else manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRV.setLayoutManager(manager);

        mAdapt = new RECYAdapter(new ArrayList<AbstractBaseInformationObject>());
        mRV.setAdapter(mAdapt);

        getSupportLoaderManager().initLoader(CALENDAR_LOADER, null, this);
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
