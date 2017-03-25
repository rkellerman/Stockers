package com.example.stockers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class tradeActivity extends Fragment implements AsyncResponse{

    ArrayAdapter<String> adapter;
    ListView listView;
    View rootView;
    AlertDialog alertDialog;
    AsyncResponse del = this;

    boolean valid = false;
    String current = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.trade_layout, container, false);

        final Button button = (Button)rootView.findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                current = "set";

                EditText text = (EditText)rootView.findViewById(R.id.searchField);

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("ID", "-1");

                String type = "price";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = del;
                backgroundWorker.execute(type, text.getText().toString(), id);
            }
        });

        final Button buyButton = (Button)rootView.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                current = "purchase";

                if (!valid){
                    // you fucked up
                    return;
                }

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("ID", "-1");
                String value = sharedPreferences.getString("VALUE", "-1");

                EditText text = (EditText)rootView.findViewById(R.id.sharesText);
                int num = Integer.parseInt(text.getText().toString());

                EditText ticker = (EditText)rootView.findViewById(R.id.searchField);


                if (num > 0){

                    String type = "purchase";

                    BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                    backgroundWorker.delegate = del;
                    backgroundWorker.execute(type, id, value, Integer.toString(num), ticker.getText().toString());
                }

            }
        });

        final Button sellButton = (Button)rootView.findViewById(R.id.sellButton);
        sellButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                current = "sell";

                if (!valid){
                    // you fucked up
                    return;
                }

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("ID", "-1");
                String value = sharedPreferences.getString("VALUE", "-1");

                EditText text = (EditText)rootView.findViewById(R.id.sharesText);
                int num = Integer.parseInt(text.getText().toString());

                EditText ticker = (EditText)rootView.findViewById(R.id.searchField);


                if (num > 0){

                    String type = "sell";

                    BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                    backgroundWorker.delegate = del;
                    backgroundWorker.execute(type, id, value, Integer.toString(num), ticker.getText().toString());
                }

            }
        });


        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Trade");
    }

    @Override
    public void processFinish(String result) {

        alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("NOTICE");

        if (current.equals("set")) {
            String[] array = result.split("===");
            if (array.length > 1) {
                valid = true;
                // put text where it needs to go
                TextView priceText = (TextView) rootView.findViewById(R.id.priceView);
                TextView volumeText = (TextView)rootView.findViewById(R.id.volumeView);
                TextView changeText = (TextView) rootView.findViewById(R.id.changeView);

                priceText.setText(array[2]);
                volumeText.setText(array[6]);
                changeText.setText(array[5]);


            } else {
                alertDialog.setMessage("Not a valid ticker or company name");
                alertDialog.show();
            }
        }
        else if (current.equals("purchase")){
            if (Double.parseDouble(result) >= 0){
                alertDialog.setMessage("Purchase complete!");
                alertDialog.show();

                String type = "login";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = this;
                backgroundWorker.execute(type, "", "", "false");
            }
            else {
                alertDialog.setMessage("You do not have enough money to complete the transaction...");
                alertDialog.show();
            }

        }
        else if (current.equals("sell")){
            if (Double.parseDouble(result) > 0){
                alertDialog.setMessage("Sale complete!");
                alertDialog.show();

                String type = "login";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = this;
                backgroundWorker.execute(type, "", "", "false");
            }
            else {
                alertDialog.setMessage("You do not own the requested number of shares...");
                alertDialog.show();
            }
        }



    }
}
