package com.daming.wordkids.bean;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.daming.wordkids.BR;
import com.daming.wordkids.R;
import com.daming.wordkids.adapter.ListItem;
import com.squareup.picasso.Picasso;

/**
 * Created by dashu on 2016/12/21.
 */

public class Category extends ListItem {
    public int id;
    public String name;
    public String image;

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String image) {
        Picasso.with(view.getContext())
                .load(image)
                .placeholder(R.mipmap.placeholder)
                .into(view);
    }

    @Override
    public int getDataVariableId() {
        return BR.category;
    }

    @Override
    public int getListenerVariableId() {
        return BR.menu_listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.menu_item;
    }
}
