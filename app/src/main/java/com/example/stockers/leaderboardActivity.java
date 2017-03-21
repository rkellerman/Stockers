package com.example.stockers;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class leaderboardActivity extends Fragment {

    ListView leaderboardList;
    int[] rank = null;
    String[] investor = null;
    double[] networth = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.leaderboard_layout, container, false);

        leaderboardList=(ListView) rootView.findViewById(R.id.leaderboardListView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("LEADERBOARD", "0!!!0");

        result = result.substring(0, result.length()-3);
        String[] array = result.split("===");

        rank = new int[array.length];
        investor = new String[array.length];
        networth = new double[array.length];

        for (int i = 0; i < array.length; i++){
            String[] entries = array[i].split("!!!");

            rank[i] = i+1;
            investor[i] = entries[0];
            networth[i] = Double.parseDouble(entries[1]);
        }


        //ADAPTER
        ListAdapter adapter = new leaderboardListAdapter(getActivity(), rank, investor, networth);
        leaderboardList.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Leaderboard");
    }


}