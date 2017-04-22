package com.example.stockers;

import android.content.Context;
import android.content.SharedPreferences;
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

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("FRIENDS", "-1");
        String playerText = sharedPreferences.getString("PLAYER", "-1");

        if (text.equals("-1")){
            // report no friends :( and then do something
            friendNames = new String[0];
            ListAdapter adapter = new friendsListAdapter(getActivity(), friendNames);
            friendsList.setAdapter(adapter);
            return rootView;
        }

        friendNames = text.split("!!!");

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

    }

    @Override
    public void processFinish(String result) {

    }
}
