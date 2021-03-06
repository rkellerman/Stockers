package com.example.stockers;

import android.app.AlertDialog;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import java.io.Serializable;

/**
 * Created by Ryan Kellerman
 */

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    public boolean LoginState_actual = false;
    public boolean LoginState_expected = true;

    EditText Username, Password;
    private static final String TAG = "MainActivity";

    private AdView mAdView;

    /**
     * Creates the login page upon opening.
     * @param savedInstanceState
     * Android Studio Default Parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoginState_actual = true;
        if(LoginState_actual == LoginState_expected){
            Log.d("LoginState","True");
        }
        else{
            Log.d("LoginState","False");
        }
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Modifications to the screen parameters need to be set before the SetContentView method...
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Username = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //Here we are going to handle the initialization of our fonts...
        Typeface myTpeface = Typeface.createFromAsset(getAssets(),"Lobster 1.4.otf");
        //Here we will reference the text we want to modify...
        TextView myTextview = (TextView)findViewById(R.id.textView2);
        //Change the font of the title
        myTextview.setTypeface(myTpeface);

        Typeface supportfont = Typeface.createFromAsset(getAssets(),"Montserrat-UltraLight.otf");
        TextView supportText1 = (TextView)findViewById(R.id.textView3);
        TextView usernameText = (TextView)findViewById(R.id.etUserName);
        TextView passwordText = (TextView)findViewById(R.id.etPassword);
        TextView regbuttonText   = (TextView)findViewById(R.id.btn_reg);
        TextView forgotbuttonText   = (TextView)findViewById(R.id.button2);
        TextView logbuttonText   = (TextView)findViewById(R.id.btnLogin);

        supportText1.setTypeface(supportfont);
        usernameText.setTypeface(supportfont);
        passwordText.setTypeface(supportfont);
        regbuttonText.setTypeface(supportfont);
        logbuttonText.setTypeface(supportfont);
        forgotbuttonText.setTypeface(supportfont);

        Intent intent = new Intent(this,IntroActivity.class);
        startActivity(intent);

    }

    /**
     * Upon login, this method will verify user credentials.
     * @param view
     * Android Studio Default Parameter
     */
    public void onLogin(View view){

        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String type = "login";
        portfolioActivity.PortfolioState_Actual = true;
        BackgroundWorker backgroundWorker = new BackgroundWorker(this, MainActivity.this);
        backgroundWorker.delegate = this;
        backgroundWorker.execute(type, username, password, "true");


    }

    /**
     * Takes user to registration page.
     * @param view
     * Android Studio Default Parameter
     */
    public void openReg(View view){

        startActivity(new Intent(this, Register.class));

    }


    /**
     * Places data in Navigation Bar.
     * @param result
     * Parameter from database to get values for database
     */
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
