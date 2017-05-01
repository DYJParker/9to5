package com.omievee.a9to5.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.omievee.a9to5.R;

import java.util.List;

/**
 * Created by omievee on 5/1/17.
 */

public class RECYAdapter extends RecyclerView.Adapter<RECYViewHolder> {
    private List<AbstractBaseInformationObject> mCardList;

    public RECYAdapter(List<AbstractBaseInformationObject> list) {
        mCardList = list;
    }


    @Override
    public RECYViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RECYViewHolder(inflater.inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(RECYViewHolder holder, int position) {
        if(mCardList.get(position) instanceof Cardinfo) {
            Cardinfo cards = (Cardinfo) mCardList.get(position);

            holder.mText1.setText(cards.getTest1());
            holder.mText2.setText(cards.getTest2());
            holder.mText3.setText(cards.getTest3());
        }
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    public void addToList(AbstractBaseInformationObject obj){
        mCardList.add(obj);
        notifyItemInserted(mCardList.size()-1);
    }
}
