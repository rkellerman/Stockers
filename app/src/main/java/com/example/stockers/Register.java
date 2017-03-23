package com.example.stockers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class Register extends AppCompatActivity implements AsyncResponse{

    EditText name, surname, email, password, passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.et_name);
        surname = (EditText)findViewById(R.id.et_surname);
        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        passwordConfirm = (EditText) findViewById(R.id.et_passwordConfirm);
    }

    public void onReg(View view){

        String str_name = name.getText().toString();
        String str_surname = surname.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String str_passwordConfirm = passwordConfirm.getText().toString();

        if (str_name.isEmpty())
        {
            Toast.makeText(Register.this, "First Name Field Is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (str_surname.isEmpty())
        {
            Toast.makeText(Register.this, "Last Name Field Is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (str_email.isEmpty())
        {
            Toast.makeText(Register.this, "Email Field Is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (str_password.isEmpty())
        {
            Toast.makeText(Register.this, "Password Field Is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (str_passwordConfirm.isEmpty())
        {
            Toast.makeText(Register.this, "Confirm Password Field Is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!str_password.equals(str_passwordConfirm))
        {
            Toast.makeText(Register.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(Register.this, "Registration Success", Toast.LENGTH_SHORT).show();

        String type = "register";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this, Register.this);
        backgroundWorker.delegate = this;
        backgroundWorker.execute(type, str_name, str_surname, str_email, str_password);

        return;

    }

    @Override
    public void processFinish(String result){

        finish();
    }
}
