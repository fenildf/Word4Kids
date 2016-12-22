package com.daming.wordkids.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.daming.wordkids.R;
import com.daming.wordkids.adapter.ListItem;
import com.squareup.picasso.Picasso;

/**
 * Created by dashu on 2016/12/21.
 */

public class Card extends ListItem {
    public int id;
    public String definition;
    public String word;
    public String image;
    public String pronunciation;
    public String audio;

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String image) {
        Picasso.with(view.getContext())
                .load(image)
                .placeholder(R.mipmap.placeholder)
                .into(view);
    }

    @Override
    public int getDataVariableId() {
        return 0;
    }

    @Override
    public int getListenerVariableId() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return android.R.layout.simple_list_item_1;
    }
}
