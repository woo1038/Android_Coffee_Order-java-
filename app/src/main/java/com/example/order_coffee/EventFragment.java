package com.example.order_coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class EventFragment extends Fragment {

    ListView listView;
    ListAdapter adapter;

    ArrayList<event> events = new ArrayList<event>();

    public static EventFragment newInstance()
    {
        EventFragment eventFragment  = new EventFragment();
        return eventFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        //커피 레이아웃
        events.add(new event(R.mipmap.f));
        events.add(new event(R.mipmap.g));
        events.add(new event(R.mipmap.h));
        events.add(new event(R.mipmap.i));


        //GridView 어뎁터
        listView = (ListView) rootView.findViewById(R.id.list);
        adapter = new ListAdapter(getActivity(), R.layout.activity_list_adapter, events);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Event_pick.class);

                intent.putExtra("picture", events.get(position).event_picture);

                startActivity(intent);
            }
        });


        return rootView;
    }
}
