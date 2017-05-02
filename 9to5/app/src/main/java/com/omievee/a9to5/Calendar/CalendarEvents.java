package com.omievee.a9to5.Calendar;

import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dave - Work on 5/1/2017.
 */

public class CalendarEvents extends AbstractBaseInformationObject {
    private List<CalendarEventInstance> mList;

    public CalendarEvents() {
        mList = new ArrayList<>();
    }

    public List<CalendarEventInstance> getList() {
        return mList;
    }

    public void setList(List<CalendarEventInstance> list) {
        mList = list;
    }

    public void addInstance(long ID, int color, String description, long begin, long end) {
        mList.add(new CalendarEventInstance(ID, color, description,begin, end));
    }

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
}
