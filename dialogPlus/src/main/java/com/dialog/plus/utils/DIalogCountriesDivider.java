package com.dialog.plus.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dialog.plus.R;

/**
 * Created by Ali on 01,October,2019
 * aligamal.dev@gmail.com
 */
public class DIalogCountriesDivider extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public DIalogCountriesDivider(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.dialog_countries_devider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//divider padding give some padding whatever u want or disable
        int left = parent.getPaddingLeft() + 80;
        int right = parent.getWidth() - parent.getPaddingRight() - 30;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
