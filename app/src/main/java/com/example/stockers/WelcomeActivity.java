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
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * initilizes the player information and their portfolio
 */
public class WelcomeActivity extends AppCompatActivity implements AsyncResponse {

    Player player;

    /**
     * when the view is created it initilizes user portfolio
     * @param savedInstanceState Default android studio parameter
     */
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


    /**
     * performs an action when the process is finished
     * @param result input of a android studio result
     */
    @Override
    public void processFinish(String result) {

    }
}
