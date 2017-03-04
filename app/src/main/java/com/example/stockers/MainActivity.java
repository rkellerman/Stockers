package com.example.stockers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);

    }

    public void onLogin(View view){

        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this, MainActivity.this);
            backgroundWorker.execute(type, username, password);


    }

    public void openReg(View view){
        startActivity(new Intent(this, Register.class));
    }
}
