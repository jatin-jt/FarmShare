package com.noobsquad.farmshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class GenericAdapter<T> extends BaseAdapter {

    LayoutInflater layoutInflater;
    Context context;
    private ArrayList<T> arrayList;

    public GenericAdapter(Context context, ArrayList<T> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(arrayList == null)
            return 0;
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public abstract View getMyView(int position, View convertView, ViewGroup parent,T t);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getMyView(position, convertView, parent, arrayList.get(position));
    }
}

//lv.setAdapter(new GenericAdapter<POJO>(getContext(), arrayList) {
//@Override
//public View getMyView(int position, View convertView, ViewGroup parent, POJO pojo) {
//    TextView textView = (TextView) convertView;
//    if (textView == null) {
//    textView = (TextView) getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
//    textView.setTextColor(getContext().getResources().getColor(R.color.black));
//    }
//    textView.setText(institutions.getName());
//    return textView;
//    }
//    });
