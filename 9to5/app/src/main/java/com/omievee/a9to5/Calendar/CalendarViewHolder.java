package com.omievee.a9to5.Calendar;

import android.view.View;

import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

/**
 * Created by Dave - Work on 5/1/2017.
 */

public class CalendarViewHolder extends AbstractBaseHolder {
    //TODO add member data

    public CalendarViewHolder(View itemView) {
        super(itemView);
        //VIEWTYPE content = LayoutInflater.from(itemView.getContext()).inflate(R.layout.YOUR_XML, null);
        int padding = itemView.getResources().getDimensionPixelOffset(R.dimen.typ_margin);
        int bottomPadding = itemView.getResources().getDimensionPixelOffset(R.dimen.default_cardview_bottom_margin);
        //content.setPadding(padding, padding, padding, bottomPadding);

        //TODO assign stuff to your member variable views here

        //((CardView) itemView).addView(content,0);
    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {

    }

}
