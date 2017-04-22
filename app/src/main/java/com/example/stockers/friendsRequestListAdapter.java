package com.example.stockers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by namit on 4/22/2017.
 */

public class friendsRequestListAdapter extends ArrayAdapter {

    String[] friendsRequest={};
    Context c;
    LayoutInflater inflater;

    public friendsRequestListAdapter(Context context, String[] friendsRequest) {
        super(context, R.layout.friends_request_row_model, friendsRequest);

        this.c=context;
        this.friendsRequest=friendsRequest;
    }

    public class ViewHolder
    {
        TextView friendsRequest;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.friends_request_row_model, null);
        }
        // VIEWHOLDER OBJECT
        final friendsRequestListAdapter.ViewHolder holder = new friendsRequestListAdapter.ViewHolder();

        //INITIALIZE VIEWS
        holder.friendsRequest = (TextView) convertView.findViewById(R.id.list_friends_request);

        //ASSIGN VIEWS
        holder.friendsRequest.setText(String.valueOf(friendsRequest[position]));

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
