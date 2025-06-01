package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class chapter extends BaseAdapter {

    private Context context;
    private String[] titles;
    private int[] images;

    public chapter(Context context, String[] titles, int[] images) {
        this.context = context;
        this.titles = titles;
        this.images = images;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);

        TextView textView = view.findViewById(R.id.item_text);
        ImageView imageView = view.findViewById(R.id.item_image);

        textView.setText(titles[position]);
        imageView.setImageResource(images[position]);

        return view;
    }
}
