package com.example.stockers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Namit Patel on 4/22/2017.
 */

public class friendsRequestListAdapter extends ArrayAdapter implements View.OnClickListener {

    String[] friendsRequest={};
    Context c;
    LayoutInflater inflater;
    View rootView;
    String text = "";
    Activity activity;
    String current = "";
    String str;
    AsyncResponse del = new AsyncResponse() {
        @Override
        public void processFinish(String result) {
            if (current.equals("accept")) {
                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), activity);
                backgroundWorker.delegate = del;
                backgroundWorker.execute("handleFriend", "show_requests", "butts", str);
                current = "requests";
            }
            else if (current.equals("requests")){
                current = "friends";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), activity);
                backgroundWorker.delegate = del;
                backgroundWorker.execute("handleFriend", "show_friends", "", str);
            }
            else if (current.equals("friends")){

                if (getContext() instanceof FragmentActivity){

                    Fragment fragment = new friendsActivity();
                    FragmentActivity act = (FragmentActivity)(getContext());
                    FragmentTransaction t = act.getSupportFragmentManager().beginTransaction();
                    t.replace(R.id.content_navigation, fragment);
                    t.commit();



                }

            }

        }
    };

    /**
     * This is used to initialize the friend request list in the friendActivity.
     * @param context
     * Android Studio Default Parameter
     * @param friendsRequest
     * Array contains usernames of all potential friends
     */
    public friendsRequestListAdapter(Context context, String[] friendsRequest) {
        super(context, R.layout.friends_request_row_model, friendsRequest);

        this.c=context;
        this.friendsRequest=friendsRequest;
    }

    /**
     * Listening function that waits for certain button to be pressed.
     *
     * @param v
     * Android Studio Default Parameter
     */
    @Override
    public void onClick(View v) {
        Log.d("AAAAA", "hello");

        Activity activity = new Activity();
        SharedPreferences sharedPreferences = c.getSharedPreferences("1", Context.MODE_PRIVATE);
        String playerText = sharedPreferences.getString("PLAYER", "-1");

        String[] array = playerText.split(" ");

        BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), activity);
        backgroundWorker.delegate = del;
        backgroundWorker.execute("handleFriend", "accept", text, array[1]);
        str = array[1];
        current = "accept";
    }

    public class ViewHolder
    {
        TextView friendsRequest;
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
            convertView=inflater.inflate(R.layout.friends_request_row_model, null);

        }
        rootView = convertView;
        // VIEWHOLDER OBJECT
        final friendsRequestListAdapter.ViewHolder holder = new friendsRequestListAdapter.ViewHolder();

        //INITIALIZE VIEWS
        holder.friendsRequest = (TextView) convertView.findViewById(R.id.list_friends_request);

        //ASSIGN VIEWS
        holder.friendsRequest.setText(String.valueOf(friendsRequest[position]));

        text = String.valueOf(friendsRequest[position]);

        Button acceptButton = (Button)convertView.findViewById(R.id.accept_button);
        acceptButton.setOnClickListener(this);

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
