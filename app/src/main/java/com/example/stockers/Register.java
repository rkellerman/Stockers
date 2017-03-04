package com.example.stockers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

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
        backgroundWorker.execute(type, str_name, str_surname, str_email, str_password);






    }
}
