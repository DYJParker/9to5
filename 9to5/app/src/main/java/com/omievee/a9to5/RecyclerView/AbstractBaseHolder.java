package com.omievee.a9to5.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dave - Work on 5/1/2017.
 */

public abstract class AbstractBaseHolder extends RecyclerView.ViewHolder {
    public AbstractBaseHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindDataToViews(AbstractBaseInformationObject data);
}
