package com.daming.wordkids.adapter;

/**
 * Created by dashu on 2016/12/22.
 */

public abstract class ListItem {
    public abstract int getDataVariableId();
    public abstract int getListenerVariableId();
    public int getLayoutId(){
        return android.R.layout.simple_list_item_1;
    }
}
