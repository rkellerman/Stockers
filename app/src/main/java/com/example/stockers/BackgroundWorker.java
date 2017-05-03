package com.example.stockers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

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

import com.example.stockers.SharedPreference.*;

/**
 * Backgroundworker class that runs all the important functions and calls for the app
 * Created by Ryan Kellerman
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Player player = null;
    boolean isLogin = true;
    private SharedPreference sharedPreference;
    private Activity activity;
    public AsyncResponse delegate = null;

    SharedPreferences sharedPref;

    public static String login_url = "http://stockers.atwebpages.com/login.php";
    public static String register_url = "http://stockers.atwebpages.com/register.php";
    public static String viewPrices_url = "http://stockers.atwebpages.com/viewPrices.php";
    public static String purchase_url = "http://stockers.atwebpages.com/purchase.php";
    public static String sell_url = "http://stockers.atwebpages.com/sell.php";
    public static String update_url = "http://stockers.atwebpages.com/yahoostock.php";
    public static String portfolio_url = "http://stockers.atwebpages.com/portfolio.php";
    public static String leaderboard_url = "http://stockers.atwebpages.com/leaderboard.php";
    public static String price_url = "http://stockers.atwebpages.com/price.php";
    public static String graph_url = "http://stockers.atwebpages.com/graph.php";
    public static String getMessages_url = "http://stockers.atwebpages.com/getMessages.php";
    public static String sendMessage_url = "http://stockers.atwebpages.com/sendMessage.php";
    public static String friend_url = "http://stockers.atwebpages.com/friend.php";
    public static String friendLeaderboard_url = "http://stockers.atwebpages.com/friendLeaderboard.php";
    public static String activity_url = "http://stockers.atwebpages.com/activity.php";

    Context context;
    AlertDialog alertDialog;
    String action;

    /**
     * sets the current context and activity to the activity that the user is in.
     * @param context the current state of the application
     * @param activity the current view that the user is in
     */
    public BackgroundWorker (Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }

    /**
     * used to check if login is correct and finds the user in the database. Returns the appropriate values
     * based on if it was correct login information or not.
     * @param params multiple parameters such as email and password
     * @return returns a success message if the login is successful. A -1 if it is an incorrect login
     */
    public String login(String... params){



        try {

            this.action = "login";

            player = new Player();

            if (params[3].equals("false")){


                isLogin = false;

                SharedPreferences sharedPreferences = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                String result = sharedPreferences.getString("PLAYER", "");

                String[] array = result.split(" ");

                player.email = array[3];
                player.password = array[4];

            }
            else {
                player.email = params[1];
                player.password = params[2];
            }



            URL url = new URL(login_url);



            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(player.email, "UTF-8") + "&" +
                    URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(player.password, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();



            String array[] = result.split(" ");



            if (array.length > 1){

                player.name = array[0];
                player.playerID = Integer.parseInt(array[1]);
                player.value = Double.parseDouble(array[2]);

                String netWorth = array[3];

                // result = result + " " + player.email + " " + player.password;

                result = array[0] + " " + array[1] + " " + array[2] + " " + player.email + " " + player.password + " " + array[3];

                return result;
            }
            else {

                return "-1";
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    /**
     * allows a user to register on the app and create an account.
     * @param params parameters such as name, surname, email, and password
     * @return returns the player email and name if successful and a -1 if it was not
     */
    public String register(String... params){
        try {
            this.action = "register";

            URL url = new URL(register_url);

            player = new Player();
            player.name = params[1];
            player.surname = params[2];
            player.email = params[3];
            player.password = params[4];

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(player.name, "UTF-8") + "&" +
                    URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(player.surname, "UTF-8") + "&" +
                    URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(player.email, "UTF-8") + "&" +
                    URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(player.password, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            if (Integer.parseInt(result) == 1){
                return player.email + " " + player.name;
            }
            else {
                return "-1";
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    /**
     *  retrieves information about the user's portfolio and returns a string of the portfolio information
     * @param params user email
     * @return returns a string consisting of the portfolio information or -1 if there is none available
     */
    public String portfolio(String... params){

        try {

            if (player == null) {
                this.action = "portfolio";
                player = new Player();

                player.playerID = Integer.parseInt(params[1]);
            }



            URL url = new URL(portfolio_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("playerID", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(player.playerID), "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    public String friendLeaderboard(String... params){
        try {
            this.action = "friendLeaderboard";

            URL url = new URL(friendLeaderboard_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("person", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    public String get_activity(String... params){
        try {
            this.action = "activity";

            URL url = new URL(activity_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("person", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    /**
     * gives an update call to the database to update all of its stocks
     */
    public void update(){

        try {
            URL url = new URL(update_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            Log.d("BOI", result);

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  retrieves leaderboard information which is sorted by user networth
     * @return returns a string consisting of the username and networth
     */
    public String leaderboard(){

        try {


            URL url = new URL(leaderboard_url);


            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     *  retrieves the prices of stocks
     * @param params a string of parameters that is passed
     * @return a string containing the prices. -1 if there is an error
     */
    public String price(String... params){

        // update();

        try {

            this.action = "price";

            URL url = new URL(price_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("ticker", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                    URLEncoder.encode("playerID", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    public String graph(String... params){
        // update();

        try {

            this.action = "graph";

            URL url = new URL(graph_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("ticker", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    /**
     * makes purchase based on the given parameters
     * @param params a string of parameters that are passed
     * @return string consisting of ticker, shares, playerID, and money
     */
    public String makePurchase(String... params){

        try {
            this.action = "purchase";
            URL url = new URL(purchase_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("ticker", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                    URLEncoder.encode("shares", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                    URLEncoder.encode("playerID", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                    URLEncoder.encode("money", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            double temp = Double.parseDouble(result);

            if (temp == -1.0){
                return "-1";
            }
            else if (temp == -2.0){
                return "-2";
            }
            else {
                return result;
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public String sendMessage(String... params){

        try {

            this.action = "sendMessage";
            player = new Player();

            URL url = new URL(sendMessage_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            String player_string;

            try {
                SharedPreferences sharedPreferences = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                player_string = sharedPreferences.getString("PLAYER", "-1");
            }
            catch (Exception e){
                return "-1";
            }

            String[] player_array = player_string.split(" ");

            player.email = player_array[3];

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(player.email, "UTF-8") + "&" +
                    URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";

    }

    public String getMessages(String... params){

        try {

            this.action = "getMessages";
            player = new Player();

            URL url = new URL(getMessages_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            String player_string = "";

            try {

                SharedPreferences sharedPreferences = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                player_string = sharedPreferences.getString("PLAYER", "-1");
                Log.d("HEY", player_string);
            }
            catch (Exception e){
                return "-1";
            }

            String[] player_array = player_string.split(" ");

            player.email = player_array[3];

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(player.email, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    /**
     * sells stock based on the parameters given
     * @param params string of parameters for selling stock
     * @return returns string of Ticker, shares, playerID, and money
     */
    public String sell(String... params){

        try {
            this.action = "sell";
            URL url = new URL(sell_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("ticker", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                    URLEncoder.encode("shares", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                    URLEncoder.encode("playerID", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                    URLEncoder.encode("money", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            double temp = Double.parseDouble(result);

            if (temp == -1.0){
                return "-1";
            }
            else if (temp == -2.0){
                return "-2";
            }
            else {
                return result;
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public String handleFriend(String... params){

        try{

            this.action = "handleFriend";
            Log.d("NIIIIGGGG", "nig");
            URL url = new URL(friend_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                    URLEncoder.encode("recipient", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                    URLEncoder.encode("sender", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            Log.d("FUCK", params[1]);
            if (params[1].equals("show_requests")){
                sharedPref = context.getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("REQUESTS", result);
                editor.commit();
                Log.d("KJSHDFKJHSDLKJHF", result);
            }
            else if (params[1].equals("show_friends")){
                sharedPref = context.getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("FRIENDS", result);
                editor.commit();
                Log.d("FLAACCCKKKKAAA", result);
            }
            else if (params[1].equals("accept")){
                result = "boi";
            }

            Log.d("NIIIIG", result);
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }

    /**
     * background worker that performs multiple functions based on its parameters
     * @param params string of parameters that contain the user information
     * @return returns a string that contains information based on what function was called inside it
     */
    @Override
    protected String doInBackground(String... params){


        String type = params[0];

        String result = null;

        if (type.equals("login")){
            result = login(params);
            if (!result.equals("-1")){


                if (isLogin){
                    // Looper.prepare();

                    try {
                        activity.runOnUiThread(new Runnable(){

                            @Override
                            public void run() {
                                Toast.makeText(activity, "Login Success!  Preparing Content...", Toast.LENGTH_LONG).show();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String[] array = result.split(" ");

                    player.email = array[3];
                    player.password = array[4];

                    update();

                    result = login("butts", player.email, player.password, "true");

                }

                sharedPref = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                String result1 = leaderboard();
                String result2 = portfolio(params);

                /*
                sharedPreference = new SharedPreference();
                sharedPreference.save(context, result2);
                */


                editor.putString("LEADERBOARD", result1);
                editor.putString("PORTFOLIO", result2);

                editor.putString("PLAYER", result);

                editor.putString("ID", Integer.toString(player.playerID));
                editor.putString("VALUE", Double.toString(player.value));

                editor.commit();
            }

        }
        else if (type.equals("register")){
            result = register(params);
        }
        else if (type.equals("portfolio")){
            result = portfolio(params);
        }
        else if (type.equals("price")){
            result = price(params);
        }
        else if (type.equals("purchase")){
            result = makePurchase(params);
        }
        else if (type.equals("sell")){
            result = sell(params);
        }
        else if (type.equals("graph")){
            result = graph(params);
        }
        else if (type.equals("sendMessage")){
            try {
                result = sendMessage(params);
                result = getMessages(params);

                sharedPref = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("CHAT", result);
                editor.commit();
            }
            catch (Exception e){
                return "-1";
            }


        }
        else if (type.equals("getMessages")){
            try {
                result = getMessages(params);

                sharedPref = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("CHAT", result);
                editor.commit();
            }
            catch(Exception e){
                return "-1";
            }
        }
        else if (type.equals("handleFriend")){
            result = handleFriend(params);


        }
        else if (type.equals("friendLeaderboard")){
            result = friendLeaderboard(params);

            sharedPref = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString("FRIENDLEADERBOARD", result);
            editor.commit();
        }
        else if (type.equals("profile")){
            result = get_activity(params);
            Log.d("S:LDKFJ", result);
            sharedPref = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString("ACTIVITY", result);
            editor.commit();

        }
        return result;
    }

    /**
     * creates an alert dialog that has a title of "Notice"
     */
    @Override
    protected void onPreExecute() {

        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("NOTICE");
    }

    /**
     *  delegates process finishes and displays alert boxes
     * @param result string containing information on what process to perform
     */
    @Override
    protected void onPostExecute(String result) {


        if (this.action.equals("login")){
            if (isLogin == false){
                return;
            }
            if (!result.equals("-1")){  // jump to welcome activity

                delegate.processFinish(result);

            }
            else {
                Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
        else if (this.action.equals("register")){
            if (!result.equals("-1")){

                Thread thread=new Thread(){
                    @Override
                    public void run() {

                        GMailSender sender = new GMailSender("123absentsnake321@gmail.com","ryanryan");
                        try {
                            sender.sendMail("Welcome to Stockers!", "<b>" + player.name + ",</b><br/>Welcome to the premier trading platform!  We are happy to have you.", "welcome@stockers.com", player.email, "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();


                delegate.processFinish(result);
            }
            else {}
        }
        else if (this.action.equals("portfolio")){
            alertDialog.setMessage(result);
            alertDialog.show();

            delegate.processFinish(result);
        }
        else if (this.action.equals("price")){


            delegate.processFinish(result);
        }
        else if (this.action.equals("purchase")){
            delegate.processFinish(result);
        }
        else if (this.action.equals("sell")){
            delegate.processFinish(result);
        }
        else if (this.action.equals("graph")){
            delegate.processFinish(result);
        }
        else if (this.action.equals("sendMessage")){
            // should not reach here
        }
        else if (this.action.equals("getMessages")){
            delegate.processFinish(result);
        }
        else if (this.action.equals("handleFriend")){
            if (result.equals("basdfadf")){
                return;
            }
            delegate.processFinish(result);
            Log.d("hello", result);
        }
        else if (this.action.equals("friendLeaderboard")){
            delegate.processFinish(result);
        }
        else if (this.action.equals("activity")){
            delegate.processFinish(result);
        }
    }

    /**
     * default Android studio function
     * @param values default Android studio parameter
     */
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }





}
