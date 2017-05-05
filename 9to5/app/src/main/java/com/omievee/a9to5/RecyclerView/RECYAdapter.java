package com.omievee.a9to5.RecyclerView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.omievee.a9to5.Calendar.CalendarEvents;
import com.omievee.a9to5.Calendar.CalendarViewHolder;
import com.omievee.a9to5.MTA_API.MTA_VIewHolder;
import com.omievee.a9to5.MTA_API.MTA_object;
import com.omievee.a9to5.News.News_Object;
import com.omievee.a9to5.News.News_ViewHolder;
import com.omievee.a9to5.R;
import com.omievee.a9to5.Weather.WeatherInfoObject;
import com.omievee.a9to5.Weather.WeatherViewHolder;

import java.util.List;

/**
 * Created by omievee on 5/1/17.
 */

public class RECYAdapter extends RecyclerView.Adapter<AbstractBaseHolder> implements InterfaceSingleton.ListUpdateListener {
    private static final int CALENDAR_TYPE = 0;
    private static final int WEATHER_TYPE = 1;
    private static final int MTA_TYPE = 2;
    private static final int NETWORK_FAILURE = 3;
    public static final int NEWS_TYPE = 4;

    private static final String TAG = "RECYAdapter";


    private List<AbstractBaseInformationObject> mCardList;

    public RECYAdapter(List<AbstractBaseInformationObject> list) {
        mCardList = list;
        InterfaceSingleton.getInstance().setListener(this);
    }

    @Override
    public int getItemViewType(int position) {

        if(mCardList.get(position) instanceof NetworkFailureObject) return NETWORK_FAILURE;

        else if (mCardList.get(position) instanceof MTA_object) return MTA_TYPE;

        else if (mCardList.get(position) instanceof WeatherInfoObject) return WEATHER_TYPE;

        else if (mCardList.get(position) instanceof CalendarEvents) return CALENDAR_TYPE;

        else if(mCardList.get(position) instanceof News_Object) return NEWS_TYPE;

        else throw new RuntimeException("Invalid data!");
    }

    @Override
    public AbstractBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView item = (CardView) inflater.inflate(R.layout.base_item_cardview, parent, false);

        switch (viewType) {
            case NETWORK_FAILURE:
                return new NetworkFailureImageHolder(item);
            case CALENDAR_TYPE:
                return new CalendarViewHolder(item);
            case MTA_TYPE:
                return new MTA_VIewHolder(item);
            case WEATHER_TYPE:
                return new WeatherViewHolder(item);
            case NEWS_TYPE:
                return new News_ViewHolder(item);
            default:
                return null;
        }
        //return new RECYViewHolder(inflater.inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(AbstractBaseHolder holder, int position) {
        if (mCardList.get(position) instanceof Cardinfo) {
            RECYViewHolder RECYholder = (RECYViewHolder) holder;
            Cardinfo cards = (Cardinfo) mCardList.get(position);

            RECYholder.mText1.setText(cards.getTest1());
            RECYholder.mText2.setText(cards.getTest2());
            RECYholder.mText3.setText(cards.getTest3());
        } else if (holder != null) {
            holder.bindDataToViews(mCardList.get(position));
        }
        /*else if (mCardList.get(position) instanceof CalendarEvents){

            holder.bindDataToViews(mCardList.get(position));
        }*/
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    //public void addToList(AbstractBaseInformationObject obj) {
    //    //for(AbstractBaseInformationObject listObj : mCardList){
    //    //    if (obj.getClass().getCanonicalName() == listObj.getClass().getCanonicalName()){
    //    //        listObj = obj;
    //    //        notifyItemChanged(mCardList.indexOf(listObj));
    //    //        return;
    //    //    }
    //    //}
    //    mCardList.add(obj);
    //    notifyItemInserted(mCardList.size() - 1);
    //}

    @Override
    public void updateList(AbstractBaseInformationObject obj) {
        int size = mCardList.size();
        for (int i = 0; i < size; i++) {
            if(mCardList.get(i).getClass().getCanonicalName().equals(obj.getClass().getCanonicalName())){
                mCardList.set(i,obj);
                notifyItemChanged(i);
                return;
            }
        }
        //for(AbstractBaseInformationObject listObj : mCardList){
        //    if (obj.getClass().getCanonicalName().equals(listObj.getClass().getCanonicalName())) {
        //        int position = mCardList.indexOf(listObj)
        //        listObj. = obj;
        //        notifyItemChanged(position);
        //        //notifyDataSetChanged();
        //        return;
        //    }
        //}
        if(obj instanceof News_Object || obj instanceof MTA_object){
            int loc = mCardList.size()%2;
            mCardList.add(loc,obj);
            notifyItemInserted(loc);
        } else {
            mCardList.add(obj);
            notifyItemInserted(mCardList.size() - 1);
        }
    }
}
