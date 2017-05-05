package com.omievee.a9to5.Calendar;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.omievee.a9to5.RecyclerView.InterfaceSingleton;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.provider.BaseColumns._ID;
import static android.provider.CalendarContract.Instances.*;

/**
 * Created by Dave - Work on 5/1/2017.
 */

public class CalendarCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int CAL_ENTRIES = 3;
    private Context mContext;

    public CalendarCallbacks(Context context) {
        mContext = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Date now = new Date();
        Uri query = ContentUris.withAppendedId(CONTENT_URI, now.getTime());
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = cal.getTime();
        query = ContentUris.withAppendedId(query, tomorrow.getTime());

        return new CursorLoader(mContext,
                query,
                new String[]{_ID, BEGIN, END, TITLE, DISPLAY_COLOR, VISIBLE, ALL_DAY},
                null,
                null,
                BEGIN + " asc");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        CalendarEvents events = new CalendarEvents();
        if (data != null && data.moveToFirst()) {
            for (int i = 0; i < CAL_ENTRIES; ) {
                if (data.getInt(data.getColumnIndex(VISIBLE)) != 0) {
                    boolean allDay = data.getInt(data.getColumnIndex(ALL_DAY)) > 0;
                    Calendar cal = GregorianCalendar.getInstance();
                    long start, end;
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    if ((start = data.getLong(data.getColumnIndex(BEGIN))) < cal.getTimeInMillis())
                        start = 0;
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    if ((end = data.getLong(data.getColumnIndex(END))) > cal.getTimeInMillis())
                        end = 0;
                    events.addInstance(
                            data.getLong(data.getColumnIndex(_ID)),
                            data.getInt(data.getColumnIndex(DISPLAY_COLOR)),
                            data.getString(data.getColumnIndex(TITLE)),
                            (allDay) ? -1 : start,
                            (allDay) ? -1 : end
                    );
                    i++;
                }
                data.moveToNext();
                if (data.isAfterLast()) break;
            }
        }
        InterfaceSingleton.getInstance().updateList(events);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
