package com.omievee.a9to5.RecyclerView;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omievee.a9to5.Calendar.CalendarEvents;
import com.omievee.a9to5.Calendar.CalendarViewHolder;
import com.omievee.a9to5.R;

import java.util.List;

/**
 * Created by omievee on 5/1/17.
 */

public class RECYAdapter extends RecyclerView.Adapter<AbstractBaseHolder> {
    private static final int CALENDAR_TYPE = 0;

    private List<AbstractBaseInformationObject> mCardList;

    public RECYAdapter(List<AbstractBaseInformationObject> list) {
        mCardList = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (mCardList.get(position) instanceof CalendarEvents) return CALENDAR_TYPE;
            //elseif
        else throw new RuntimeException("Invalid data!");
    }

    @Override
    public AbstractBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //TODO consider creating a standard, XML-defined CardView, and having each data type inflate and insert its own content
        switch (viewType) {
            case CALENDAR_TYPE:
                //TODO convert this over to a CardView
                return new CalendarViewHolder(new LinearLayoutCompat(parent.getContext()));
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
        } else if (mCardList.get(position) instanceof CalendarEvents){
            holder.bindDataToViews(mCardList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    public void addToList(AbstractBaseInformationObject obj) {
        //for(AbstractBaseInformationObject listObj : mCardList){
        //    if (obj.getClass().getCanonicalName() == listObj.getClass().getCanonicalName()){
        //        listObj = obj;
        //        notifyItemChanged(mCardList.indexOf(listObj));
        //        return;
        //    }
        //}
        mCardList.add(obj);
        notifyItemInserted(mCardList.size() - 1);
    }
}
