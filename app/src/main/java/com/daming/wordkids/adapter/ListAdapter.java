package com.daming.wordkids.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dashu on 2016/12/22.
 */

public class ListAdapter<T extends ListItem> extends BaseAdapter {

    private List<T> list;
    private Context context;
    public ListItemListener listener;

    public ListAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void setList(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void appendList(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewDataBinding binding = null;
        T item = (T) getItem(position);
        if (view == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(this.context), item.getLayoutId(), parent, false);
        } else {
            binding = DataBindingUtil.getBinding(view);
        }
        binding.setVariable(item.getDataVariableId(), item);
        binding.setVariable(item.getListenerVariableId(), this.listener);
        binding.getRoot().setTag(item);
        return binding.getRoot();
    }

    public void setListener(ListItemListener listener) {
        this.listener = listener;
    }
}
