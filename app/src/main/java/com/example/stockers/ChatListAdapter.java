package com.example.stockers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by namit on 4/21/2017.
 */

public class ChatListAdapter extends ArrayAdapter<String>{

    String[] userName={};
    String[] userMessage={};
    String[] messageTime={};
    Context c;
    LayoutInflater inflater;

    public ChatListAdapter(Context context, String[] userName, String[] userMessage, String[] messageTime){
        super(context, R.layout.chat_row_model, userName);

        this.c=context;
        this.userName=userName;
        this.userMessage=userMessage;
        this.messageTime=messageTime;
    }

    public class ViewHolder
    {
        TextView userName;
        TextView userMessage;
        TextView messageTime;
    }

    @NonNull
    @Override
    // get view
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.chat_row_model, null);
        }

        // VIEWHOLDER OBJECT
        final ChatListAdapter.ViewHolder holder = new ChatListAdapter.ViewHolder();

        //INITIALIZE VIEWS
        holder.userName= (TextView) convertView.findViewById(R.id.userNameRow);
        holder.userMessage= (TextView) convertView.findViewById(R.id.userMessageRow);
        holder.messageTime= (TextView) convertView.findViewById(R.id.messageTimeRow);

        //ASSIGN VIEWS
        holder.userName.setText(String.valueOf(userName[position]));
        holder.userMessage.setText(userMessage[position]);
        holder.messageTime.setText(messageTime[position]);

        //return super.getView(position, convertView, parent);
        return convertView;
    }

}
