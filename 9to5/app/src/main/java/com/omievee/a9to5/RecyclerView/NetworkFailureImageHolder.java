package com.omievee.a9to5.RecyclerView;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.omievee.a9to5.R;

/**
 * Created by justinstanger on 5/4/17.
 */

public class NetworkFailureImageHolder extends AbstractBaseHolder {

    public NetworkFailureImageHolder(View itemView) {
        super(itemView);
        ((CardView)itemView).addView(
                LayoutInflater.from(itemView.getContext()).inflate(R.layout.network_failure_cardview,(ViewGroup)itemView,false)
        );
    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {

    }
}