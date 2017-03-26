package com.example.stockers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
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
 * Created by RyanMini on 3/2/17.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Player player = null;
    boolean isLogin = true;

    private SharedPreference sharedPreference;

    private Activity activity;
    public AsyncResponse delegate = null;

    public static String login_url = "http://stockers.atwebpages.com/login.php";
    public static String register_url = "http://stockers.atwebpages.com/register.php";
    public static String viewPrices_url = "http://stockers.atwebpages.com/viewPrices.php";
    public static String purchase_url = "http://stockers.atwebpages.com/purchase.php";
    public static String sell_url = "http://stockers.atwebpages.com/sell.php";
    public static String update_url = "http://stockers.atwebpages.com/yahoostock.php";
    public static String portfolio_url = "http://stockers.atwebpages.com/portfolio.php";
    public static String leaderboard_url = "http://stockers.atwebpages.com/leaderboard.php";
    public static String price_url = "http://stockers.atwebpages.com/price.php";

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

            if (params[3].equals("false")){


                isLogin = false;

                SharedPreferences sharedPreferences = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                String result = sharedPreferences.getString("PLAYER", "");

                String[] array = result.split(" ");

                player.email = array[3];
                player.password = array[4];

            }
            else {

                // update();

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

    @Override
    protected String doInBackground(String... params){

        String type = params[0];

        String result = null;

        if (type.equals("login")){
            result = login(params);
            if (!result.equals("-1")){

                String result1 = leaderboard();
                String result2 = portfolio(params);

                /*
                sharedPreference = new SharedPreference();
                sharedPreference.save(context, result2);
                */

                if (isLogin){
                    update();
                }

                SharedPreferences sharedPref = activity.getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

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
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }





}
