package com.dialog.plus.ui.list_dialog;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by fawzy on 8/31/18.
 */

public class BaseHolder extends RecyclerView.ViewHolder {
    public View foregroundView;

    public BaseHolder(View itemView) {
        super(itemView);
    }

    public void setForegroundView(View foregroundView) {
        this.foregroundView = foregroundView;
    }

    public void bind(int position) {

    }
}
