package com.omievee.a9to5;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.omievee.a9to5.Weather.WeatherCreate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Dave - Work on 5/3/2017.
 */

public class AlertThrower extends BroadcastReceiver {
    public static final int REQUEST = 42;

    @Override
    public void onReceive(Context context, Intent intent) {
        //this does not work maybe?
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            timeListener(context);
        } else WeatherCreate.getCityWeather(MainActivity.sCityQuery,context,true, null);
    }

    private void timeListener(Context ctx){
        //this does not work maybe?
        AlarmManager manager;
        PendingIntent intent;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.HOUR_OF_DAY,6);

        manager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        Intent inte = new Intent(ctx,AlertThrower.class);
        intent = PendingIntent.getBroadcast(ctx,REQUEST,inte,0);

        manager.setInexactRepeating(AlarmManager.RTC,cal.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,intent);
    }

    public static void setAlert(Context ctx, String title, String content){
        //this works
        //not called by weather methods
        //needs static variables

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx)
                .setSmallIcon(R.drawable.ic_cloud_black_24px)// find better cloud
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setOngoing(false)
                .setContentIntent(PendingIntent.getActivity(ctx,
                        (int)System.currentTimeMillis(),new Intent(ctx,MainActivity.class),
                        0));
        ((NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(REQUEST,builder.build());
    }

}
