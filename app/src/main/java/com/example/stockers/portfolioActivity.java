package com.example.stockers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.stockers.SharedPreference.*;

public class portfolioActivity extends Fragment {

    ListView portfolioList;
    String[] stockTicker = null;
    double[] stockPrice = null;
    int[] shares = null;
    String[] percentChange = null;

    private SharedPreference sharedPreference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*
        sharedPreference = new SharedPreference();
        String result = sharedPreference.getValue(getContext());
        */

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("PORTFOLIO", "0!!!0!!!0!!!0===");


        /*
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("NOTICE");
        alertDialog.setMessage(result);
        alertDialog.show();
        */

        Log.d("BOOOOOIIIII", result);


        if (result.length() > 2) {
            result = result.substring(0, result.length() - 3);

            String[] array = result.split("===");

            stockTicker = new String[array.length];
            stockPrice = new double[array.length];
            shares = new int[array.length];
            percentChange = new String[array.length];


            for (int i = 0; i < array.length; i++) {

                try {
                    String[] entries = array[i].split("!!!");
                    stockTicker[i] = entries[0];
                    stockPrice[i] = Double.parseDouble(entries[1]);
                    shares[i] = Integer.parseInt(entries[2]);
                    percentChange[i] = entries[3];
                } catch (Exception e) {
                    stockTicker[i] = "bet";
                    stockPrice[i] = 69.69;
                    shares[i] = 420;
                    percentChange[i] = "%4.20";
                }




            }

            View rootView = inflater.inflate(R.layout.portfolio_layout, container, false);

            portfolioList = (ListView) rootView.findViewById(R.id.portfolioListView);
            //ADAPTER
            ListAdapter adapter = new portfolioListAdapter(getActivity(), stockTicker, stockPrice, shares, percentChange);
            portfolioList.setAdapter(adapter);

            return rootView;
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("NOTICE");
            alertDialog.setMessage("No activity to show...");
            alertDialog.show();

            return null;
        }




    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Portfolio");
    }
}
