package com.example.stockers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

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

/**
 * Created by RyanMini on 3/2/17.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Player player = null;
    boolean success = false;
    boolean ready = false;

    private Activity activity;

    public static String login_url = "http://10.0.2.2:8888/login.php";
    public static String register_url = "http://10.0.2.2:8888/register.php";
    public static String viewPrices_url = "http://localhost:8888/viewPrices.php";
    public static String purchase_url = "http://localhost:8888/purchase.php";
    public static String sell_url = "http://localhost:8888/sell.php";
    public static String update_url = "http://localhost:8888/yahoostock.php";


    Context context;
    AlertDialog alertDialog;
    String action;

    public BackgroundWorker (Context context, Activity activity){
        this.context = context;
        this.ready = false;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params){

        String type = params[0];

        if (type.equals("login")){
            try {

                this.action = "login";

                player = new Player();

                URL url = new URL(login_url);

                player.email = params[1];

                player.password = params[2];

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

                    player.playerID = Integer.parseInt(array[1]);
                    player.value = Double.parseDouble(array[2]);
                    player.name = array[0];

                    return "Success\nWelcome " + player.name + "!";
                }
                else {

                    return "Login Unsuccessful";
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("register")){
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

                    // go back to MainActivity


                    return "Registration successful";

                }
                else {

                    return "Registration unsuccessful";
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();

        if (this.action.equals("login")){
            if (!result.equals("Login Unsuccessful")){  // jump to welcome activity
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e){}

                Intent intent = new Intent(this.context, WelcomeActivity.class);
                intent.putExtra("Player", (Serializable) player);

                this.context.startActivity(intent);
            }
            else {}
        }
        else if (this.action.equals("register")){
            if (result.equals("Registration successful")){
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e){}
                this.activity.finish();
            }
            else {}
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
