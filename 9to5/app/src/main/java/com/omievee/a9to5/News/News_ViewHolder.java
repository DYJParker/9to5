package com.omievee.a9to5.News;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.omievee.a9to5.R;
import com.omievee.a9to5.RecyclerView.AbstractBaseHolder;
import com.omievee.a9to5.RecyclerView.AbstractBaseInformationObject;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

/**
 * Created by omievee on 5/5/17.
 */

public class News_ViewHolder extends AbstractBaseHolder {
    List<TextView> mHEADLINE, mDESCRIP;


    public News_ViewHolder(View itemView) {
        super(itemView);

        mHEADLINE = new ArrayList<>();
        mDESCRIP = new ArrayList<>();
//        mSOURCE = new ArrayList<>();

        LinearLayoutCompat content = new LinearLayoutCompat(itemView.getContext());
        content.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
        int padding = itemView.getResources().getDimensionPixelOffset(R.dimen.typ_margin);
        int bottomPadding = padding;
        content.setPadding(padding, padding, padding, bottomPadding);
        content.setOrientation(LinearLayoutCompat.VERTICAL);
        for (int i = 0; i < 3; i++) {
            View subchild = LayoutInflater.from(itemView.getContext())
                    .inflate(R.layout.news_layout, null);
            if (i==0) subchild.findViewById(R.id.divider).setVisibility(GONE);
            content.addView(subchild);
            mHEADLINE.add((TextView) subchild.findViewById(R.id.TITLE));
            mDESCRIP.add((TextView) subchild.findViewById(R.id.Descrip));
//            mSOURCE.add((ImageView) subchild.findViewById(R.id.ImageSource));
        }
        ((CardView) itemView).addView(content,0);
    }


    @Override
    public void bindDataToViews(AbstractBaseInformationObject data) {

        News_Object local = (News_Object) data;
        for (int i = 0; i < mHEADLINE.size() ; i++) {
            mHEADLINE.get(i).setText(local.getArticles().get(i)[0]);
            mDESCRIP.get(i).setText(local.getArticles().get(i)[1]);

        }

    }
}
