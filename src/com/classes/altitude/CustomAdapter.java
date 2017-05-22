package com.classes.altitude;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
private static ArrayList<ListModel> listNews;

private LayoutInflater mInflater;

public CustomAdapter(Context photosFragment, ArrayList<ListModel> results){
    CustomAdapter.listNews = results;
    mInflater = LayoutInflater.from(photosFragment);
}

@Override
public int getCount() {
    // TODO Auto-generated method stub
    return listNews.size();
}

@Override
public Object getItem(int arg0) {
    // TODO Auto-generated method stub
    return listNews.get(arg0);
}

@Override
public long getItemId(int arg0) {
    // TODO Auto-generated method stub
    return arg0;
}


@Override
public View getView(int position, View convertView, ViewGroup parent) {
    // TODO Auto-generated method stub
    ViewHolder holder;
    if(convertView == null){
        convertView = mInflater.inflate(R.layout.news_test, null);
        holder = new ViewHolder();
        holder.headline = (TextView) convertView.findViewById(R.id.tv1);          
        holder.location = (TextView) convertView.findViewById(R.id.tv2);
        holder.date = (TextView) convertView.findViewById(R.id.tv3);   

        convertView.setTag(holder);
    } else {
        holder = (ViewHolder) convertView.getTag();
    }

    holder.headline.setText(listNews.get(position).getHeadlines());
    holder.location.setText(listNews.get(position).getLocation());
    holder.date.setText(listNews.get(position).getDate());
    Typeface tp=Typeface.createFromAsset(parent.getContext().getAssets(),"TitilliumWebRegular.ttf");
    Typeface tb=Typeface.createFromAsset(parent.getContext().getAssets(),"TitilliumWebSemiBold.ttf");
    holder.headline.setTypeface(tp);
    holder.location.setTypeface(tb);

    holder.date.setTypeface(tb);
    
    return convertView;
}

static class ViewHolder{
    TextView headline, location,date;
}
}