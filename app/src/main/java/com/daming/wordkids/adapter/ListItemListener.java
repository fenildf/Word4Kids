package com.daming.wordkids.adapter;

import android.view.View;

/**
 * Created by dashu on 2016/12/22.
 */

public abstract class ListItemListener<T> {
    public void onClick(View view) {
        T t = (T) view.getTag();
        onItemClicked(t);
    }

    public abstract void onItemClicked(T t);
}
