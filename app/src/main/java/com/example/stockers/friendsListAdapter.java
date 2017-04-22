package com.example.stockers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by namit on 4/22/2017.
 */

public class friendsListAdapter extends ArrayAdapter {

    String[] friends={};
    Context c;
    LayoutInflater inflater;

    public friendsListAdapter(Context context, String[] friends) {
        super(context, R.layout.friends_row_model, friends);

        this.c=context;
        this.friends=friends;
    }

    public class ViewHolder
    {
        TextView friends;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.friends_row_model, null);
        }
        // VIEWHOLDER OBJECT
        final friendsListAdapter.ViewHolder holder = new friendsListAdapter.ViewHolder();

        //INITIALIZE VIEWS
        holder.friends = (TextView) convertView.findViewById(R.id.list_friends);

        //ASSIGN VIEWS
        holder.friends.setText(String.valueOf(friends[position]));

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
