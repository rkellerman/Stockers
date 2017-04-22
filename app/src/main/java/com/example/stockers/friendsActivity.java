package com.example.stockers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class friendsActivity extends Fragment implements AsyncResponse, View.OnClickListener{

    ListView friendsList;
    String[] friendNames = null;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.friends_layout, container, false);

        friendsList=(ListView) rootView.findViewById(R.id.friendsListView);
        Button butt = (Button) rootView.findViewById(R.id.searchFriendsButton);
        butt.setOnClickListener(this);
        // lmao butt

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("FRIENDS", "-1");
        if (text.equals("-1")){
            // report no friends :( and then do something
            friendNames = new String[0];
            ListAdapter adapter = new friendsListAdapter(getActivity(), friendNames);
            friendsList.setAdapter(adapter);
            return rootView;
        }

        friendNames = text.split("!!!");

        ListAdapter adapter = new friendsListAdapter(getActivity(), friendNames);
        friendsList.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        
    }

    @Override
    public void processFinish(String result) {

    }
}
