package com.example.stockers;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class tradeActivity extends Fragment implements AsyncResponse{

    ArrayAdapter<String> adapter;
    ListView listView;
    View rootView;
    AlertDialog alertDialog;
    AsyncResponse del = this;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.trade_layout, container, false);

        final Button button = (Button)rootView.findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                EditText text = (EditText)rootView.findViewById(R.id.searchField);

                String type = "price";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = del;
                backgroundWorker.execute(type, text.getText().toString());
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
        alertDialog.setMessage(result);
        alertDialog.show();

    }
}
