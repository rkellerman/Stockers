package com.example.stockers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class friendsActivity extends Fragment {

    ListView friendsList;
    String[] friendNames = {"Nams", "Harsh"};

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.friends_layout, container, false);

        friendsList=(ListView) rootView.findViewById(R.id.friendsListView);


        ListAdapter adapter = new friendsListAdapter(getActivity(), friendNames);
        friendsList.setAdapter(adapter);
        return rootView;
    }

}
