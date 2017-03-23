package com.example.stockers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class Register extends AppCompatActivity implements AsyncResponse{

    EditText name, surname, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.et_name);
        surname = (EditText)findViewById(R.id.et_surname);
        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
    }

    public void onReg(View view){

        String str_name = name.getText().toString();
        String str_surname = surname.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();

        String type = "register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this, Register.this);
        backgroundWorker.delegate = this;
        backgroundWorker.execute(type, str_name, str_surname, str_email, str_password);


    }

    @Override
    public void processFinish(String result){

        String[] array = result.split(" ");
        Player player = new Player();
        player.email = array[0];
        player.name = array[1];

        // sendEmail(player);


        finish();
    }

    protected void sendEmail(Player player) {
        Log.i("Send email", "");

        String[] TO = {player.email};
        // String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        // emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Welcome to Stockers!");

        String body = player.name + ",\nWelcome to the most popular trading platform!";

        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Register.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


}
