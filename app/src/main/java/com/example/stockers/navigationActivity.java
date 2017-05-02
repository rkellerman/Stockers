package com.example.stockers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Ryan Kellerman & Namit Patel.
 */

public class navigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse {

    String type = null;
    AsyncResponse del = this;
    BackgroundWorker backgroundWorker;
    String str = "";
    //Unit Testing Variables



    //Portfolio...
    public boolean NavigationState_Portfolio_actual = false;
    public boolean NavigationState_Portfolio_expected = true;

    //Leaderboard...
    public boolean NavigationState_Leaderboard_actual = false;
    public boolean NavigationState_Leaderboard_expected = true;

    //Profile...
    public boolean NavigationState_Profile_actual = false;
    public boolean NavigationState_Profile_expected = true;

    //Trade...
    public boolean NavigationState_Trade_actual = false;
    public boolean NavigationState_Trade_expected = true;

    //Help
    public boolean NavigationState_Help_actual = false;
    public boolean NavigationState_Help_expected = true;

    //Friends
    public boolean NavigationState_Friends_actual = false;
    public boolean NavigationState_Friends_expected = true;

    //Logout...
    public boolean NavigationState_Logout_actual = false;
    public boolean NavigationState_Logout_expected = true;

    /**
     * Links XML widgets to Java variables upon creation
     * @param savedInstanceState
     * Android Studio Default Parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface numberfont = Typeface.createFromAsset(getAssets(),"Lobster 1.4.otf");
        setContentView(R.layout.activity_navigation);


        TextView toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        toolbarTitle.setTypeface(numberfont);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_portfolio);
    }

    /**
     * Closes Navigation Drawer when button is pressed.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Display's player username on Navigation Bar
     * @param menu
     * Android Studio Default Parameter
     * @return returns true when it has completed
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);

        SharedPreferences sharedPreferences = navigationActivity.this.getSharedPreferences("1", Context.MODE_PRIVATE);
        String balanceString = sharedPreferences.getString("VALUE", "-1");
        String playerText = sharedPreferences.getString("PLAYER", "-1");
        String[] array = playerText.split(" ");


        TextView email = (TextView)findViewById(R.id.usernameNav);
        Double balance = Double.parseDouble(balanceString);
        String bal = String.valueOf("$"+String.format("%.2f", balance));


        email.setText(array[3]);


        return true;
    }

    /**
     * Connects Navigation Bar Open/Close with Android back button.
     * @param item
     * Android Studio Default Parameter
     * @return
     * true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method opens other pages as requested by the user from the NavBar.
     * @param id
     * ID of Android Pages
     */
    private void displaySelectedScreen(int id){
        Fragment fragment = null;
        boolean proceed = true;



        switch (id){
            /*
            case R.id.nav_tutorial:
                NavigationState_Portfolio_actual = true;
                if(NavigationState_Portfolio_actual==NavigationState_Portfolio_expected){
                    Log.d("Navigation->Portfolio: ","True");
                }
                fragment = new tutorialActivity();
                break;
                */
            case R.id.nav_portfolio:

                NavigationState_Portfolio_actual = true;
                if(NavigationState_Portfolio_actual==NavigationState_Portfolio_expected){
                    Log.d("Navigation->Portfolio: ","True");
                }
                fragment = new portfolioActivity();
                break;
            case R.id.nav_leaderboard:

                type = "leaderboard";
                proceed = false;

                NavigationState_Leaderboard_actual = true;
                if(NavigationState_Leaderboard_actual==NavigationState_Leaderboard_expected){
                    Log.d("Navigation->Leaderbrd: ","True");
                }
                SharedPreferences sharedPreferences = navigationActivity.this.getSharedPreferences("1", Context.MODE_PRIVATE);
                String playerText = sharedPreferences.getString("PLAYER", "-1");

                String[] array = playerText.split(" ");

                backgroundWorker = new BackgroundWorker(this, navigationActivity.this);
                backgroundWorker.delegate = del;
                backgroundWorker.execute("friendLeaderboard", array[1]);

                Log.d("BOI", array[1]);


                break;
            case R.id.nav_profile:

                type = "profile";
                proceed = false;

                SharedPreferences sharedPreferences2 = navigationActivity.this.getSharedPreferences("1", Context.MODE_PRIVATE);
                String playerText2 = sharedPreferences2.getString("PLAYER", "-1");

                String[] array2 = playerText2.split(" ");

                backgroundWorker = new BackgroundWorker(this, navigationActivity.this);
                backgroundWorker.delegate = del;
                backgroundWorker.execute("profile", array2[1]);

                NavigationState_Profile_actual = true;
                if(NavigationState_Profile_actual==NavigationState_Profile_expected){
                    Log.d("Navigation->Profile: ","True");
                }
                // fragment = new profileActivity();
                break;
            case R.id.nav_trade:

                NavigationState_Trade_actual = true;
                if(NavigationState_Trade_actual==NavigationState_Trade_expected){
                    Log.d("Navigation->Trade: ","True");
                }
                fragment = new tradeActivity();
                break;
            case R.id.logout:

                NavigationState_Logout_actual = true;
                if(NavigationState_Logout_actual==NavigationState_Logout_expected){
                    Log.d("Navigation->Logout: ","True");
                }
                finish();
                break;
            case R.id.nav_chat:
                proceed = false;
                // fragment = new ChatActivity();
                type = "chat";
                backgroundWorker = new BackgroundWorker(this, navigationActivity.this);
                backgroundWorker.delegate = del;
                backgroundWorker.execute("getMessages");
                break;
            case R.id.nav_learn:
                NavigationState_Help_actual = true;
                if(NavigationState_Help_actual == NavigationState_Help_expected){
                    Log.d("Navigation->Help: ", "True");
                }
                fragment = new Help();
                break;
            case R.id.nav_friends:
                NavigationState_Friends_actual = true;
                proceed = false;
                type = "requests";
                if(NavigationState_Friends_actual == NavigationState_Friends_expected){
                    Log.d("Navigation->Friends: ", "True");
                }
                // fragment = new friendsActivity();

                SharedPreferences sharedPreferences1 = navigationActivity.this.getSharedPreferences("1", Context.MODE_PRIVATE);
                String playerText1 = sharedPreferences1.getString("PLAYER", "-1");

                String[] array1 = playerText1.split(" ");

                backgroundWorker = new BackgroundWorker(this, navigationActivity.this);
                backgroundWorker.delegate = del;
                backgroundWorker.execute("handleFriend", "show_requests", "butts", array1[1]);
                str = array1[1];
                break;

        }
        if (proceed == true) {
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_navigation, fragment);
                ft.commit();
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    /**
     * Fetches ID from XML.
     * @param item
     * @return true
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    /**
     * Default Android Function
     * @param menu
     * @return
     * false
     */
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void processFinish(String result) {

        if (type.equals("chat")){
            Fragment fragment = new ChatActivity();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_navigation, fragment);
            ft.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (type.equals("requests")){
            type = "friends";

            backgroundWorker = new BackgroundWorker(this, navigationActivity.this);
            backgroundWorker.delegate = del;
            backgroundWorker.execute("handleFriend", "show_friends", "", str);
            return;

        }
        else if (type.equals("friends")){
            Fragment fragment = new friendsActivity();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_navigation, fragment);
            ft.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (type.equals("leaderboard")){
            Fragment fragment = new leaderboardActivity();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_navigation, fragment);
            ft.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (type.equals("profile")){
            Fragment fragment = new profileActivity();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_navigation, fragment);
            ft.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}
