package com.example.stockers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class friendsActivity extends Fragment implements AsyncResponse, View.OnClickListener{

    ListView friendsList;
    ListView friendsRequestList;
    String[] friendNames = null;
    View rootView;
    String[] friendsRequestNames = null;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.friends_layout, container, false);

        friendsList=(ListView) rootView.findViewById(R.id.friendsListView);
        friendsRequestList = (ListView) rootView.findViewById(R.id.friendsRequestsListView);
        Button butt = (Button) rootView.findViewById(R.id.searchFriendsButton);
        butt.setOnClickListener(this);
        // lmao butt
        Typeface myTpeface = Typeface.createFromAsset(getContext().getAssets(),"Lobster 1.4.otf");
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");

        TextView tit1 = (TextView)rootView.findViewById(R.id.friendsHeader);
        TextView tit2 = (TextView)rootView.findViewById(R.id.friendSearch);
        TextView tit3 = (TextView)rootView.findViewById(R.id.searchFriendsButton);


        tit1.setTypeface(myTpeface);
        tit2.setTypeface(supportfont);
        tit3.setTypeface(supportfont);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("FRIENDS", "-1");
        String text1 = sharedPreferences.getString("REQUESTS", "-1");
        String playerText = sharedPreferences.getString("PLAYER", "-1");

        Log.d("BOOOOIIII", text);
        Log.d("BBBBBOOOOOOU", text1);

        if (text.equals("-1")){
            // report no friends :( and then do something
            friendNames = new String[0];
        }
        else {
            if (text.length() < 4){
                friendNames = new String[0];
            }
            else {
                text = text.substring(0, text.length() - 3);
                String[] array = text.split("!!!");
                friendNames = new String[array.length];
                for (int i = 0; i < array.length; i++){
                    friendNames[i] = array[i];
                }
            }

        }

        if (text1.equals("-1")){
            friendsRequestNames = new String[0];
        }
        else {
            if (text1.length() < 4){
                friendsRequestNames = new String[0];
            }
            else {
                text1 = text1.substring(0, text1.length() - 3);
                String[] array = text1.split("!!!");
                friendsRequestNames = new String[array.length];

                for (int i = 0; i < array.length; i++){
                    friendsRequestNames[i] = array[i];
                }
            }
        }


        ListAdapter adapter = new friendsListAdapter(getActivity(), friendNames);
        ListAdapter adapter1 = new friendsRequestListAdapter(getActivity(), friendsRequestNames);

        friendsList.setAdapter(adapter);
        friendsRequestList.setAdapter(adapter1);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        EditText text = (EditText) rootView.findViewById(R.id.friendSearch);
        String recipient = text.getText().toString();



        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String playerText = sharedPreferences.getString("PLAYER", "-1");

        String[] array = playerText.split(" ");

        Log.d("BRUUUUHHH", "Ayyy");


        BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
        backgroundWorker.delegate = this;
        backgroundWorker.execute("handleFriend", "add", recipient, array[1]);

        text.setText("");

    }

    @Override
    public void processFinish(String result) {

        if (result.equals("-1")){
            Toast.makeText(getActivity(), "User does not exist", Toast.LENGTH_SHORT).show();
        }
        else if (result.equals("-2")){
            Toast.makeText(getActivity(), "There is already a pending request", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Friend request sent", Toast.LENGTH_SHORT).show();
        }

    }
}
