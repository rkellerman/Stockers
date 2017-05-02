package com.example.stockers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Namit Patel on 4/22/2017.
 */

public class friendsListAdapter extends ArrayAdapter implements AsyncResponse, View.OnClickListener{

    String[] friends={};
    Context c;
    LayoutInflater inflater;
    View convertView;

    /**
     * This is used to initialize the friends list in the friendsActivity.
     * @param context
     * Android Studio Default Parameter
     * @param friends
     * Array contains usernames of all friends.
     *
     *
     */
    public friendsListAdapter(Context context, String[] friends) {
        super(context, R.layout.friends_row_model, friends);

        this.c=context;
        this.friends=friends;
    }

    @Override
    public void processFinish(String result) {

    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder
    {
        TextView friends;
        TextView title;
        TextView tit1;
        TextView tit2;

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
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");
        holder.friends.setTypeface(supportfont);
        //ASSIGN VIEWS
        holder.friends.setText(String.valueOf(friends[position]));

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
