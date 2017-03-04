package com.example.stockers;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Player player = (Player)getIntent().getSerializableExtra("Player");

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Info");

        alertDialog.setMessage("Player name: " + player.name + "\nPlayerID: " + player.playerID + "\nPlayer money: $" + player.value);
        alertDialog.show();
    }
}
