package com.example.subodh.vcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Subodh on 6/19/2016.
 */
public class cu extends ArrayAdapter
{
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listitem,parent,false);
        ImageView imageview = (ImageView) row.findViewById(R.id.image1);
        TextView textview = (TextView) row.findViewById(R.id.tv1);
        imageview.setImageResource(images[position]);
        textview.setText(headings[position]);
        return row;
    }

    Context context;
    int[] images;
    String[] headings;
    public cu(Context context, int[] i, String[] head) {
        super(context,R.layout.listitem,R.id.tv1,head);
        this.context= context;
        this.headings=head;
        this.images= i;
    }
}