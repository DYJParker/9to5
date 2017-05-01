package com.omievee.a9to5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.MultiTapKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.omievee.a9to5.MTA_API.MTA_Interface;
import com.omievee.a9to5.MTA_API.MTA_POJO;
import com.omievee.a9to5.RecyclerView.Cardinfo;
import com.omievee.a9to5.RecyclerView.RECYAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRV;
    RECYAdapter mAdapt;
    List<Cardinfo> mCardinfo;
    public static final String URL = "http://web.mta.info/status";

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


        //RecyclerView / LLM / Async Task
        mCardinfo = new ArrayList<>();
        mCardinfo.add(new Cardinfo("Test", "Test", "Test"));
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


    //AsyncTask loading Card info
    private AsyncTask mTask = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {

            return params;
        }
    };

    protected void getMTAStatus() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Retrofit builder = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();

            MTA_Interface serviceStatus = builder.create(MTA_Interface.class);
            Call<MTA_POJO> checkStatus = serviceStatus.getService();

            checkStatus.enqueue(new Callback<MTA_POJO>() {
                @Override
                public void onResponse(Call<MTA_POJO> call, Response<MTA_POJO> response) {
                    MTA_POJO status = response.body();
                    if(status == null) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }else {



                    }
                }

                @Override
                public void onFailure(Call<MTA_POJO> call, Throwable t) {

                }
            });

        }


    }
}
