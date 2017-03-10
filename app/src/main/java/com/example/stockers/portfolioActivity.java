package com.example.stockers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


public class portfolioActivity extends Fragment {

    ListView portfolioList;
    String[] stockTicker={"AAPL", "GOOG", "MSFT"};
    double[] stockPrice={138.96, 830.63, 64.01};
    int[] shares={5, 2, 10};
    double[] percentChange={0.59, 0.55, 1.43};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.portfolio_layout, container, false);

        portfolioList = (ListView) rootView.findViewById(R.id.portfolioListView);
        //ADAPTER
        ListAdapter adapter = new portfolioListAdapter(getActivity(), stockTicker, stockPrice, shares, percentChange);
        portfolioList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Portfolio");
    }
}
