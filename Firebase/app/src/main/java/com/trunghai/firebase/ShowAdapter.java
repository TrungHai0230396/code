package com.trunghai.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 11/05/2017.
 */

public class ShowAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<LayDuLieuListView> myList;

    public ShowAdapter(Context myContext, int myLayout, List<LayDuLieuListView> myList) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.myList = myList;
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=inflater.inflate(myLayout,null);

        TextView txtTen= (TextView) convertView.findViewById(R.id.tvTen);
        txtTen.setText(myList.get(position).Ten);
        ImageView image= (ImageView) convertView.findViewById(R.id.imageView3);
        Picasso.with(myContext).load(myList.get(position).Hinh).into(image);
        return convertView;
    }
}
