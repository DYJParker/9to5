package com.omievee.a9to5.Calendar;

import java.util.Date;

/**
 * Created by Dave - Work on 5/1/2017.
 */

public class CalendarEventInstance {
    Date mBegin, mEnd;
    long mID;
    int mColor;
    String mDescription;

    public CalendarEventInstance(long ID, int color, String description, long begin, long end) {
        mID = ID;
        mColor = color;
        mDescription = description;
        mBegin = new Date(begin);
        mEnd = new Date(end);
    }
}
