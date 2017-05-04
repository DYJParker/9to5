package com.omievee.a9to5.Calendar;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dave - Work on 5/1/2017.
 */

public class CalendarViewHolder extends AbstractBaseHolder {
    private List<TextView> mTitles, mStartEnds;
    private List<ImageView> mIcon;

    public CalendarViewHolder(View itemView) {
        super(itemView);
        mTitles = new ArrayList<>();
        mStartEnds = new ArrayList<>();
        mIcon = new ArrayList<>();
        LinearLayoutCompat content = new LinearLayoutCompat(itemView.getContext());
        content.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
        int padding = itemView.getResources().getDimensionPixelOffset(R.dimen.typ_margin);
        int bottomPadding = padding /*+ itemView.getResources().getDimensionPixelOffset(R.dimen.default_cardview_bottom_margin)*/;
        content.setPadding(padding, padding, padding, bottomPadding);
        content.setOrientation(LinearLayoutCompat.VERTICAL);
        for (int i = 0; i < CalendarCallbacks.CAL_ENTRIES; i++) {
            View subchild = LayoutInflater.from(itemView.getContext())
                    .inflate(R.layout.calendar_list_instance, null);
            content.addView(subchild);
            mTitles.add((TextView) subchild.findViewById(R.id.title));
            mStartEnds.add((TextView) subchild.findViewById(R.id.timing));
            mIcon.add((ImageView) subchild.findViewById(R.id.icon));
        }
        ((CardView) itemView).addView(content,0);
    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {
        final CalendarEvents calData = (CalendarEvents) data;
        final SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        for (int i = 0; i < CalendarCallbacks.CAL_ENTRIES; i++) {
            if(i < calData.getList().size()) {
                mTitles.get(i).setText(calData.getList().get(i).mDescription);
                mStartEnds.get(i).setText(String.format(
                        "%s - %s",
                        sdf.format(calData.getList().get(i).mBegin),
                        sdf.format(calData.getList().get(i).mEnd)
                ));
                mIcon.get(i).getBackground().setTint(calData.getList().get(i).mColor);
                final int finalI = i;
                mIcon.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent inte = new Intent();
                        inte.setAction(Intent.ACTION_SEND);
                        inte.putExtra(Intent.EXTRA_TEXT, String.format(
                                "I have %s today, starting at %s",
                                calData.getList().get(finalI).mDescription,
                                sdf.format(calData.getList().get(finalI).mBegin)
                        ));
                        inte.setType("text/plain");
                        v.getContext().startActivity(Intent.createChooser(inte,"Where do you want to send your event?"));
                    }
                });
            } else {
                ((View)mTitles.get(i).getParent()).setVisibility(View.GONE);
            }

        }
    }

}
