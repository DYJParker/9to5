package com.omievee.a9to5.MTA_API;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.omievee.a9to5.MainActivity;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;
import com.omievee.a9to5.RecyclerView.InterfaceSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Url;

/**
 * Created by omievee on 5/2/17.
 */

public class MTA_GetStatus  extends AbstractBaseInformationObject{

    public static final String URL = "http://web.mta.info/status/";
    private static final String TAG = "MTA_GetStatus";

    public static void getMTAStatus(final Context context) {

            Retrofit builder = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();

            MTA_Interface servicestatus = builder.create(MTA_Interface.class);
            retrofit2.Call<Service> checkstatus = servicestatus.getService();
            checkstatus.enqueue(new retrofit2.Callback<Service>() {
                @Override
                public void onResponse(retrofit2.Call<Service> call, retrofit2.Response<Service> response) {
                    Service pojo = response.body();

                    if (pojo == null) {
                        Toast.makeText(context, "No lines found", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "onResponse: " + pojo.getSubway().get(3).getStatus());
                        MTA_object dataObject = new MTA_object(
                                pojo.getSubway().get(0).getStatus(),
                                pojo.getSubway().get(1).getStatus(),
                                pojo.getSubway().get(2).getStatus(),
                                pojo.getSubway().get(3).getStatus(),
                                pojo.getSubway().get(4).getStatus(),
                                pojo.getSubway().get(5).getStatus(),
                                pojo.getSubway().get(6).getStatus(),
                                pojo.getSubway().get(7).getStatus(),
                                pojo.getSubway().get(8).getStatus(),
                                pojo.getSubway().get(9).getStatus()
                        );
                        InterfaceSingleton.getInstance().updateList(dataObject);

                    }
                }

                @Override
                public void onFailure(retrofit2.Call<Service> call, Throwable t) {
                    InterfaceSingleton.getInstance().updateList(new MTA_object(true));

                    Toast.makeText(context, "Connection Failed", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });

    }

}

