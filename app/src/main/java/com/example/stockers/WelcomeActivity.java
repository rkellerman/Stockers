package com.example.stockers;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class WelcomeActivity extends AppCompatActivity implements AsyncResponse {

    Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        player = (Player)getIntent().getSerializableExtra("Player");

        String type = "portfolio";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this, WelcomeActivity.this);
        backgroundWorker.delegate = this;
        backgroundWorker.execute(type, Integer.toString(player.playerID));

    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(String output){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
    }

}
