package com.omievee.a9to5.RecyclerView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.omievee.a9to5.R;

/**
 * Created by omievee on 5/1/17.
 */

public class RECYViewHolder extends AbstractBaseHolder {

    public View mRootview;
    public TextView mText1, mText2, mText3;
    public CardView mCardView;



    public RECYViewHolder(View itemView) {
        super(itemView);

        mRootview = itemView;
//        mText1 = (TextView) itemView.findViewById(R.id.testtext1);
//        mText2 = (TextView) itemView.findViewById(R.id.testtext2);
//        mText3 = (TextView) itemView.findViewById(R.id.testtext3);
        mCardView = (CardView) itemView.findViewById(R.id.CardView);
    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {

    }
}
