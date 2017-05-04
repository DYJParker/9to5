package com.omievee.a9to5.MTA_API;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

import static com.omievee.a9to5.R.id.mL;
import static com.omievee.a9to5.R.id.visible;

/**
 * Created by omievee on 5/2/17.
 */

public class MTA_VIewHolder extends AbstractBaseHolder{
   public TextView mS123, mS456, mS7, mSShuttle, mSL, mSACE, mSNQRW, mSBDFM, mSJZ, mSG, mCollapse;

    public MTA_VIewHolder(View itemView) {
        super(itemView);

        View content = LayoutInflater.from(itemView.getContext()).inflate(R.layout.mta_layout_alt, null);

        mS123 = (TextView) content.findViewById(R.id.status123);
        mS456 = (TextView) content.findViewById(R.id.status456);
        mSACE = (TextView) content.findViewById(R.id.statusACE);
        mSBDFM = (TextView) content.findViewById(R.id.statusBDFM);
        mSNQRW = (TextView) content.findViewById(R.id.statusNQR);
        mSJZ = (TextView) content.findViewById(R.id.statusJZ);
        mS7 = (TextView) content.findViewById(R.id.status7);
        mSShuttle = (TextView) content.findViewById(R.id.statusShuttle);
        mSL = (TextView) content.findViewById(R.id.statusL);
        mSG = (TextView) content.findViewById(R.id.statusG);



        ((CardView) itemView).addView(content, 0);


    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {
        MTA_object localdata = (MTA_object) data;
        mS123.setText(localdata.getM123());
        mS456.setText(localdata.getM456());
        mSACE.setText(localdata.getmACE());
        mSBDFM.setText(localdata.getmBDFM());
        mSNQRW.setText(localdata.getmNQR());
        mSG.setText(localdata.getmG());
        mSShuttle.setText(localdata.getmShuttle());
        mS7.setText(localdata.getM7());
        mSL.setText(localdata.getmL());
        mSJZ.setText(localdata.getmJZ());



    }
}
