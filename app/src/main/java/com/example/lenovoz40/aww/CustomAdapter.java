package com.example.lenovoz40.aww;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo Z40 on 4/26/2016.
 * adapted from: https://www.caveofprogramming.com/guest-posts/custom-listview-with-imageview-and-textview-in-android.html
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<CuteImage> cuteImages;
    private static LayoutInflater inflater = null;
    public CustomAdapter(Activity activity, ArrayList<CuteImage> cuteImages) {
        context = activity;
        this.cuteImages = cuteImages;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cuteImages.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.image_list_item, null);
        holder.tv = (TextView)rowView.findViewById(R.id.tvItem);
        holder.img = (ImageView)rowView.findViewById(R.id.ivItem);
        holder.tv.setText(cuteImages.get(position).getTitle());
        holder.img.setImageBitmap(cuteImages.get(position).getBitmap());
        return rowView;
    }
}
