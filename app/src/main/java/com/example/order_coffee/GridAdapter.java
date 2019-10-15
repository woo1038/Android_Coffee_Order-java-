package com.example.order_coffee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {


    /**
     *생성자
     * @param icons  : 그림
     * @param coffees: 이름
     **/
    private int icons;
    private ArrayList<menu> order;
    private Context context;
    private LayoutInflater inflater;

    public GridAdapter(Context context, int icons, ArrayList<menu> order)
    {
        this.context = context;
        this.icons = icons;
        this.order = order;
    }

    @Override
    public int getCount() {
        return order.size();
    }

    @Override
    public Object getItem(int position) {
        return order.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if (convertView == null)
        {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.activity_grid_adapter, null);
        }

        ImageView icon = (ImageView)gridView.findViewById(R.id.coffee);
        TextView coffee = (TextView)gridView.findViewById(R.id.name);
        TextView price = (TextView)gridView.findViewById(R.id.price);

        menu m = order.get(position);
        icon.setImageResource(m.img);
        coffee.setText(m.coffee);
        price.setText(""+m.price);

        return gridView;
    }
}

class menu{
    int img;
    String coffee ="";
    int price;

    public menu(int img, String coffee, int price){
        super();
        this.img = img;
        this.coffee = coffee;
        this.price = price;
    }
    public menu(){}
}
