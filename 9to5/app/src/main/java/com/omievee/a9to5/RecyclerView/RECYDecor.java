package com.omievee.a9to5.RecyclerView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by omievee on 5/1/17.
 */

public class RECYDecor extends RecyclerView.ItemDecoration {
    private int mSpace;

    public RECYDecor(int space) {
        this.mSpace = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        outRect.top = mSpace;

        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = mSpace;
    }
}

