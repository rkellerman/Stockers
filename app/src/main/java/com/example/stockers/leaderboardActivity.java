package com.example.stockers;


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
    int[] rank= {1,2,3,4,5,6,7};
    String[] investor={"eevee", "espeon", "flareon", "glaceon", "leafeon", "umbreon", "vaporeon"};
    double[] networth={8000, 7400, 7000, 6800, 6700, 5500, 4200};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.leaderboard_layout, container, false);

        leaderboardList=(ListView) rootView.findViewById(R.id.leaderboardListView);

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
