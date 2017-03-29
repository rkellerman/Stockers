package com.example.stockers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stockers.SharedPreference.*;

public class portfolioActivity extends Fragment {

    ListView portfolioList;
    String[] stockTicker = null;
    double[] stockPrice = null;
    int[] shares = null;
    String[] percentChange = null;
    private static TextView totalNetworth;

    private SharedPreference sharedPreference;

    /**
     *
     * This function initializes all variables
     * when view is created,  to values
     * obtained from our database.
     *
     * @param inflater
     * Android Studio Default Parameter
     * @param container
     * Android Studio Default Parameter
     * @param savedInstanceState
     * Android Studio Default Parameter
     * @return rootView
     *
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.portfolio_layout, container, false);
        /*
        sharedPreference = new SharedPreference();
        String result = sharedPreference.getValue(getContext());
        */

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("PORTFOLIO", "0!!!0!!!0!!!0");
        String playerText = sharedPreferences.getString("PLAYER", "-1");
        String[] arr = playerText.split(" ");
        /*
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("NOTICE");
        alertDialog.setMessage(result);
        alertDialog.show();
        */

        //Here we are going to handle UI modifications (Frank)...

        //Instantiate our font...
        Typeface number = Typeface.createFromAsset(getActivity().getAssets(),"din.ttf");

        //Access the textView we want to modify...
        TextView networthNumber = (TextView) rootView.findViewById(R.id.total_networth);
        TextView networthTitle  = (TextView) rootView.findViewById(R.id.portfolioValuedAt);
        TextView networthpriceheader  = (TextView) rootView.findViewById(R.id.dispprice);
        TextView networthnameheader  = (TextView) rootView.findViewById(R.id.dispstock);
        TextView Stocknamehead = (TextView)rootView.findViewById(R.id.dispshares);
        TextView Stockpricehead = (TextView)rootView.findViewById(R.id.dispvalue);
        TextView helper = (TextView)rootView.findViewById(R.id.dispchange);


        //Modify the component...
        networthNumber.setTypeface(number);
        networthTitle.setTypeface(number);
        networthpriceheader.setTypeface(number);
        networthnameheader.setTypeface(number);
        Stocknamehead.setTypeface(number);
        Stockpricehead.setTypeface(number);
        helper.setTypeface(number);

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

                    String percent = entries[3].substring(1, entries[3].length());
                    percent = percent.substring(percent.indexOf('-') + 2, percent.length());

                    percentChange[i] = percent;
                } catch (Exception e) {
                    stockTicker[i] = "bet";
                    stockPrice[i] = 69.69;
                    shares[i] = 420;
                    percentChange[i] = "%4.20";
                }

            }

            totalNetworth = (TextView) rootView.findViewById(R.id.total_networth);

            double sum = 0.0;
            for (int i = 0; i < array.length; i++)
            {
                sum = sum + (stockPrice[i]*shares[i]);
            }
            String sumString = String.valueOf("$"+String.format("%.2f", Double.parseDouble(arr[5])));
            totalNetworth.setText(sumString);
            if (Double.parseDouble(arr[5]) > 10000)
            {
                totalNetworth.setTextColor(Color.parseColor("#41f479"));
            }
            else
            {
                totalNetworth.setTextColor(Color.parseColor("#f44141"));
            }



            portfolioList = (ListView) rootView.findViewById(R.id.portfolioListView);
            //ADAPTER
            ListAdapter adapter = new portfolioListAdapter(getActivity(), stockTicker, stockPrice, shares, percentChange);
            portfolioList.setAdapter(adapter);

            return rootView;
        }
        else {
            Toast.makeText(getActivity(), "No activity to show...", Toast.LENGTH_SHORT).show();


            stockTicker = new String[0];
            stockPrice = new double[0];
            shares = new int[0];
            percentChange = new String[0];

            totalNetworth = (TextView) rootView.findViewById(R.id.total_networth);

            String sumString = String.valueOf("$"+String.format("%.2f", Double.parseDouble(arr[5])));
            totalNetworth.setText(sumString);


            portfolioList = (ListView) rootView.findViewById(R.id.portfolioListView);
            //ADAPTER
            ListAdapter adapter = new portfolioListAdapter(getActivity(), stockTicker, stockPrice, shares, percentChange);
            portfolioList.setAdapter(adapter);



            return rootView;
        }






    }

    /**
     * Sets the Title in the Header. This function is called before onCreateView.
     * @param view
     * Android Studio Default Parameter
     * @param savedInstanceState
     * Android Studio Default Parameter
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Portfolio");
    }
}
