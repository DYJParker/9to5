package com.omievee.a9to5.Calendar;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.provider.BaseColumns._ID;
import static android.provider.CalendarContract.Instances.*;

/**
 * Created by Dave - Work on 5/1/2017.
 */

public class CalendarCallbacks {
    public static final int CAL_ENTRIES = 3;
    public static CalendarEvents onCalendarLoadFinished(Cursor data){
        CalendarEvents events = new CalendarEvents();
        for (int i = 0; i < CAL_ENTRIES; i++) {
            //TODO add check for item visibility, decrement if it's not visible
            events.addInstance(
                    data.getLong(data.getColumnIndex(_ID)),
                    data.getInt(data.getColumnIndex(DISPLAY_COLOR)),
                    data.getString(data.getColumnIndex(TITLE)),
                    data.getLong(data.getColumnIndex(BEGIN)),
                    data.getLong(data.getColumnIndex(END)
                    ));
            data.moveToNext();
            if (data.isAfterLast()) break;
        }
        return events;
    }

    public static CursorLoader onCreateCalendarLoader(Context context){
        Date now = new Date();
        Uri query = ContentUris.withAppendedId(CONTENT_URI, now.getTime());
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = cal.getTime();
        query = ContentUris.withAppendedId(query, tomorrow.getTime());
        return new CursorLoader(context,
                query,
                //TODO get calendar item visibility
                new String[]{_ID, BEGIN, END, TITLE, DISPLAY_COLOR},
                null,
                null,
                BEGIN + " asc");
    }
}
