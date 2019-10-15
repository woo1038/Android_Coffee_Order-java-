package com.example.order_coffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {


    private int picture;
    private ArrayList<event> events;
    private Context context;
    private LayoutInflater inflater;

    public ListAdapter(Context context, int picture, ArrayList<event> events)
    {
        this.context = context;
        this.picture = picture;
        this.events = events;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;

        if (convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            listView = inflater.inflate(R.layout.activity_list_adapter, null);
        }

        ImageView picture = (ImageView)listView.findViewById(R.id.event_picture);

        event e = events.get(position);
        picture.setImageResource(e.event_picture);

        return listView;
    }
}

class event{
    int event_picture;

    public event(int event_picture){
        super();
        this.event_picture = event_picture;
    }
    public event(){}
}
