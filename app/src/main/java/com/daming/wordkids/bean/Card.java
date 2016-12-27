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

public class Card {
    private static final String TAG = "Dashu";
    public int id;
    public String definition;
    public String word;
    public String image;
    public String pronunciation;
    public String audio;
}
