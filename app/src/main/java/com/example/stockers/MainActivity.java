package com.example.stockers;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    EditText Username, Password;
    private static final String TAG = "MainActivity";

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void onLogin(View view){

        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this, MainActivity.this);
        backgroundWorker.delegate = this;
        backgroundWorker.execute(type, username, password, "true");


    }

    public void openReg(View view){

        startActivity(new Intent(this, Register.class));

    }

    @Override
    public void processFinish(String result){

        Player player = new Player();
        String array[] = result.split(" ");

        player.playerID = Integer.parseInt(array[1]);
        player.value = Double.parseDouble(array[2]);
        player.name = array[0];

        Intent intent = new Intent(this, navigationActivity.class);
        intent.putExtra("Player", (Serializable)player);
        startActivity(intent);
    }


}
