package com.omievee.a9to5.RecyclerView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.omievee.a9to5.Calendar.CalendarViewHolder;
import com.omievee.a9to5.R;
import com.omievee.a9to5.R;
import com.omievee.a9to5.Weather.Weather;
import com.omievee.a9to5.Weather.WeatherInfoObject;
import com.omievee.a9to5.Weather.WeatherViewHolder;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by omievee on 5/1/17.
 */

public class RECYAdapter extends RecyclerView.Adapter<AbstractBaseHolder> implements InterfaceSingleton.ListUpdateListener{
    private static final int CALENDAR_TYPE = 0;
    private static final int WEATHER_TYPE = 1;

    private List<AbstractBaseInformationObject> mCardList;

    public RECYAdapter(List<AbstractBaseInformationObject> list) {
        mCardList = list;
        InterfaceSingleton.getInstance().setListener(this);
    }

    @Override
    public int getItemViewType(int position) {
        /*if (mCardList.get(position) instanceof CalendarEvents) return CALENDAR_TYPE;
            //elseif
        else*/
        //What type of object

        if(mCardList.get(position) instanceof WeatherInfoObject)
            return WEATHER_TYPE;

       //add else/ifs to each Object type.

        else
        throw new RuntimeException("Invalid data!");
    }

    @Override
    public AbstractBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView item = (CardView) inflater.inflate(R.layout.base_item_cardview,parent,false);

        switch (viewType) {
            case CALENDAR_TYPE:
                return new CalendarViewHolder(new LinearLayoutCompat(parent.getContext()));
            case WEATHER_TYPE:
                Log.d(TAG, "inflating: " + 1);
                return new WeatherViewHolder(item);
            default:
                return null;
        }
        //return new RECYViewHolder(inflater.inflate(R.layout.cardview, parent, false));

        //View Holder
    }

    @Override
    public void onBindViewHolder(AbstractBaseHolder holder, int position) {
        if (mCardList.get(position) instanceof Cardinfo) {
            RECYViewHolder RECYholder = (RECYViewHolder) holder;
            Cardinfo cards = (Cardinfo) mCardList.get(position);


            RECYholder.mText1.setText(cards.getTest1());
            RECYholder.mText2.setText(cards.getTest2());
            RECYholder.mText3.setText(cards.getTest3());
        } else if (mCardList.get(position) instanceof WeatherInfoObject){
            holder.bindDataToViews(mCardList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }


    @Override
    public void updateList(AbstractBaseInformationObject obj) {
        mCardList.add(obj);
        notifyItemInserted(mCardList.size() - 1);
    }
}
