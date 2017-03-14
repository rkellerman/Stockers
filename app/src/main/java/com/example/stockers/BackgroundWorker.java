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

import com.example.stockers.SharedPreference.*;

/**
 * Created by RyanMini on 3/2/17.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Player player = null;

    private SharedPreference sharedPreference;

    private Activity activity;
    public AsyncResponse delegate = null;

    public static String login_url = "http://stockers.atwebpages.com/login.php";
    public static String register_url = "http://stockers.atwebpages.com/register.php";
    public String viewPrices_url = "http://stockers.atwebpages.com/viewPrices.php";
    public String purchase_url = "http://stockers.atwebpages.com/purchase.php";
    public String sell_url = "http://stockers.atwebpages.com/sell.php";
    public String update_url = "http://stockers.atwebpages.com/yahoostock.php";
    public static String portfolio_url = "http://stockers.atwebpages.com/portfolio.php";

    Context context;
    AlertDialog alertDialog;
    String action;

    public BackgroundWorker (Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }

    public String login(String... params){
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

                player.name = array[0];
                player.playerID = Integer.parseInt(array[1]);
                player.value = Double.parseDouble(array[2]);

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
                return "0";
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

    public String portfolio(String... params){

        update();

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

    public void update(){

        try {
            URL url = new URL(update_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

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
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params){

        String type = params[0];

        String result = null;

        if (type.equals("login")){
            result = login(params);
            if (!result.equals("-1")){
                String result2 = portfolio(params);
                sharedPreference = new SharedPreference();
                sharedPreference.save(context, result2);
            }

        }
        else if (type.equals("register")){
            result = register(params);
        }
        else if (type.equals("portfolio")){
            result = portfolio(params);
        }
        return result;
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("NOTICE");
    }

    @Override
    protected void onPostExecute(String result) {


        if (this.action.equals("login")){
            if (!result.equals("-1")){  // jump to welcome activity

                delegate.processFinish(result);

            }
            else {
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }
        else if (this.action.equals("register")){
            if (result.equals("0")){
                delegate.processFinish(result);
            }
            else {}
        }
        else if (this.action.equals("portfolio")){
            alertDialog.setMessage(result);
            alertDialog.show();

            delegate.processFinish(result);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
