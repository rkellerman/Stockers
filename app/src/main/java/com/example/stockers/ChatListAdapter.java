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
 * Created by Namit Patel on 4/21/2017.
 */

public class ChatListAdapter extends ArrayAdapter<String>{

    String[] userName={};
    String[] userMessage={};
    String[] messageTime={};
    Context c;
    LayoutInflater inflater;


    /**
     * This is used to initialize the chat log for the ChatActivity.
     * @param context
     * Android Studio Default Parameter
     * @param userName
     * Array contains usernames of all players
     * @param userMessage
     * Array contains messages from all players
     * @param messageTime
     * Array contains times of each message
     */

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

    /**
     * Sets all values in XML file to display on app.
     * @param position
     * Current position of an element in the ListView widget
     * @param convertView
     * Android Studio Default Parameter
     * @param parent
     * Android Studio Default Parameter
     * @return convertView
     */
    @NonNull
    @Override
    // get view
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.chat_row_model, null);
        }

        //Typeface supportfont = Typeface.createFromAsset(getAssets(),"Montserrat-UltraLight.otf");
        Typeface titleFont = Typeface.createFromAsset(getContext().getAssets(),"Lobster 1.4.otf");
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");

        // VIEWHOLDER OBJECT
        final ChatListAdapter.ViewHolder holder = new ChatListAdapter.ViewHolder();

        //INITIALIZE VIEWS
        holder.userName= (TextView) convertView.findViewById(R.id.userNameRow);
        holder.userMessage= (TextView) convertView.findViewById(R.id.userMessageRow);
        //holder.messageTime= (TextView) convertView.findViewById(R.id.messageTimeRow);

        //ASSIGN VIEWS
        holder.userName.setText(String.valueOf(userName[position]));
        holder.userMessage.setText(userMessage[position]);
        //holder.messageTime.setText(messageTime[position]);

        //ASSIGN FONTS...
        holder.userName.setTypeface(supportfont);
        holder.userMessage.setTypeface(supportfont);

        //return super.getView(position, convertView, parent);
        return convertView;
    }

}
