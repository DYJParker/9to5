package com.omievee.a9to5.MTA_API;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

import static android.view.View.GONE;
import static com.omievee.a9to5.R.id.mL;
import static com.omievee.a9to5.R.id.visible;

/**
 * Created by omievee on 5/2/17.
 */

public class MTA_VIewHolder extends AbstractBaseHolder{
   public TextView mS123, mS456, mS7, mSShuttle, mSL, mSACE, mSNQRW, mSBDFM, mSJZ, mSG;

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

        itemView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if((oldRight-oldLeft) - (right-left)!=0) {
                    //GridLayout top = (GridLayout) ((MTA_VIewHolder)holder).mS123.getParent();
                    float density = v.getContext().getResources().getDisplayMetrics().density;
                    GridLayout grid = (GridLayout) ((ViewGroup) v).getChildAt(0);
                    boolean gridDuplex = ((right - left) < (300 * density));
                    int count = grid.getChildCount();
                    for (int i = 0; i < count; i++) {
                        TextView child = (TextView) grid.getChildAt(i);
                        if(gridDuplex) child.setTextScaleX(1);
                        else child.setTextScaleX(.8f);
                        GridLayout.LayoutParams lp = (GridLayout.LayoutParams) child.getLayoutParams();
                        if(i%2==1) {
                            lp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED,1f);
                        } else lp.columnSpec = (gridDuplex)
                                ?GridLayout.spec(GridLayout.UNDEFINED, 1f)
                                :GridLayout.spec(GridLayout.UNDEFINED, 0.01f);
                        child.setLayoutParams(lp);
                    }
                    if (gridDuplex) grid.setColumnCount(2);
                    else grid.setColumnCount(4);

                    //GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    //layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, item.getRowSpan());
                    //layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, item.getColumnSpan());
                }
            }
        });
    }

    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {

       MTA_object localdata = (MTA_object) data;
        if (!localdata.getFailed()) {
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
        }else {
            TextView failure = new TextView(mS123.getContext());
            failure.setText("MTA cannot be reached ");
            int padding = itemView.getResources().getDimensionPixelOffset(R.dimen.typ_margin);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            lp.setMargins(padding,padding,padding,padding);
            failure.setLayoutParams(lp);
            failure.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            ((CardView)mS123.getParent().getParent()).addView(failure);
            ((View)mS123.getParent()).setVisibility(GONE);

        }

    }
}
