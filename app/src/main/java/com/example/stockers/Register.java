package com.example.stockers;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class Register extends AppCompatActivity implements AsyncResponse{

    EditText name, surname, email, password, passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Modifications to the screen parameters need to be set before the SetContentView method...
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.et_name);
        surname = (EditText)findViewById(R.id.et_surname);
        email = (EditText)findViewById(R.id.et_email);
        password = (EditText)findViewById(R.id.et_password);
        passwordConfirm = (EditText) findViewById(R.id.et_passwordConfirm);

        Typeface titleFont = Typeface.createFromAsset(getAssets(),"Lobster 1.4.otf");
        Typeface supportfont = Typeface.createFromAsset(getAssets(),"Montserrat-UltraLight.otf");

        TextView title = (TextView)findViewById(R.id.textView12);
        TextView supporttext = (TextView)findViewById(R.id.textView6);
        //TextView buttonText = (TextView)findViewById(R.id.button4);
        TextView buttonRegText = (TextView)findViewById(R.id.btn_reg);

        title.setTypeface(titleFont);
        supporttext.setTypeface(supportfont);
        name.setTypeface(supportfont);
        surname.setTypeface(supportfont);
        email.setTypeface(supportfont);
        password.setTypeface(supportfont);
        passwordConfirm.setTypeface(supportfont);
        //buttonText.setTypeface(supportfont);
        buttonRegText.setTypeface(supportfont);
    }

    public void back(View view){
        finish();
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
