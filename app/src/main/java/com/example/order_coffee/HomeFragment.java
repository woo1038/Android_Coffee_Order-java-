package com.example.order_coffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    GridView gridView;
    GridAdapter adapter;
    Button btn_call, btn_touch;

    ArrayList<menu> order = new ArrayList<menu>();

    public static HomeFragment newInstance()
    {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //커피 레이아웃
        order.add(new menu(R.mipmap.a,"에소프레소",3000));
        order.add(new menu(R.mipmap.b,"아메리카노",3000));
        order.add(new menu(R.mipmap.c,"카페라테",4000));
        order.add(new menu(R.mipmap.d,"카푸치노",4000));
        order.add(new menu(R.mipmap.e,"카페모카",4500));

        //GridView 어뎁터
        gridView = (GridView) rootView.findViewById(R.id.grid);
        adapter = new GridAdapter(getActivity(), R.layout.activity_grid_adapter, order);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Select Coffee : " + order.get(position).coffee, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), Coffee_pick.class);

                intent.putExtra("img", order.get(position).img);
                intent.putExtra("name", order.get(position).coffee);
                intent.putExtra("price", order.get(position).price);

                startActivity(intent);
            }
        });

        //전화주문
        btn_call = (Button)rootView.findViewById(R.id.btn_call);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_call = new Intent();
                intent_call.setAction(Intent.ACTION_DIAL);
                intent_call.setData(Uri.parse("tel:12345"));
                startActivity(intent_call);
            }
        });

        return rootView;
    }
}
