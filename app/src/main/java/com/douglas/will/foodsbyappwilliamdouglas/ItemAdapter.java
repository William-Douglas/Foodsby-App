package com.douglas.will.foodsbyappwilliamdouglas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by William on 3/11/2018.
 */




public class ItemAdapter extends BaseAdapter {

    ListView myListView;
    String[] items;
    String[] prices;
    String[] dec;
    LayoutInflater mInflater;



    public ItemAdapter(Context c,String[] i,String[] p,String[] d){
        items=i;
        prices =p;
        dec=d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=mInflater.inflate(R.layout.my_listview_detail,null);
        TextView nameTextView= (TextView) v.findViewById(R.id.nameTextView);
        TextView descriptionTextView= (TextView) v.findViewById(R.id.descriptionTextView);
        TextView priceTextView= (TextView) v.findViewById(R.id.priceTextView);
        ImageView imageView2=(ImageView) v.findViewById(R.id.imageView2);


        String name =items[i];
        String decs = dec[i];
        String cost = prices[i];

        nameTextView.setText(name);
        descriptionTextView.setText(decs);
        priceTextView.setText(cost);


        return v;
    }
}
