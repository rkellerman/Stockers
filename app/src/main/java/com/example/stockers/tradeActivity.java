package com.example.stockers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
/**
 * the tradeActivity will auto generate different variables that can be used in the code
 *
 */

public class tradeActivity extends Fragment implements AsyncResponse{
    //Unit-Test variables...

    //These keep track of purchase transactions...
    public boolean PurchaseState_Actual = false;
    public boolean PurchaseState_Expected = true;
    //These keep track of sold transactions...
    public boolean SellState_Actual = false;
    public boolean SellState_Expected = true;


    ArrayAdapter<String> adapter;
    ListView listView;
    View rootView;
    AlertDialog alertDialog;
    AsyncResponse del = this;

    boolean valid = false;
    String current = "";

    double balance;

    String prevText = null;

    /**
     * creates multiple algorithms when this activity is started up to initialize all of the buttons
     * and create the functions that they will carry out
     * @param inflater android studio default parameter
     * @param container android studio default parameter
     * @param savedInstanceState android studio default parameter
     * @return returns rootview
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.trade_layout, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String balanceString = sharedPreferences.getString("VALUE", "-1");

        balance = Double.parseDouble(balanceString);

        TextView balanceDisplay = (TextView)rootView.findViewById(R.id.balancedisplay);

        String bal = String.valueOf("$"+String.format("%.2f", balance));
        balanceDisplay.setText(bal);

        Typeface myTpeface = Typeface.createFromAsset(getContext().getAssets(),"Lobster 1.4.otf");
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");

        TextView balance = (TextView)rootView.findViewById(R.id.balance);
        TextView money = (TextView)rootView.findViewById(R.id.balance);
        TextView tit1 = (TextView)rootView.findViewById(R.id.searchField);
        TextView tit2 = (TextView)rootView.findViewById(R.id.searchButton);
        TextView tit3 = (TextView)rootView.findViewById(R.id.nameView);

        TextView tit4 = (TextView)rootView.findViewById(R.id.one);
        TextView tit5 = (TextView)rootView.findViewById(R.id.priceView);

        TextView tit6 = (TextView)rootView.findViewById(R.id.six);
        TextView tit7 = (TextView)rootView.findViewById(R.id.peView);

        TextView tit8 = (TextView)rootView.findViewById(R.id.two);
        TextView tit9 = (TextView)rootView.findViewById(R.id.weekView);

        TextView tit10 = (TextView)rootView.findViewById(R.id.seven);
        TextView tit11 = (TextView)rootView.findViewById(R.id.divView);

        TextView tit12 = (TextView)rootView.findViewById(R.id.three);
        TextView tit13 = (TextView)rootView.findViewById(R.id.openView);

        TextView tit14 = (TextView)rootView.findViewById(R.id.eight);
        TextView tit15 = (TextView)rootView.findViewById(R.id.EPSView);

        TextView tit16 = (TextView)rootView.findViewById(R.id.four);
        TextView tit17 = (TextView)rootView.findViewById(R.id.volView);

        TextView tit18 = (TextView)rootView.findViewById(R.id.nine);
        TextView tit19 = (TextView)rootView.findViewById(R.id.changeView);

        TextView tit20 = (TextView)rootView.findViewById(R.id.five);
        TextView tit21 = (TextView)rootView.findViewById(R.id.mktView);

        TextView tit22 = (TextView)rootView.findViewById(R.id.ten);
        TextView tit23 = (TextView)rootView.findViewById(R.id.instView);

        TextView tit24 = (TextView)rootView.findViewById(R.id.textView15);
        TextView tit25 = (TextView)rootView.findViewById(R.id.sharesText);
        TextView tit26 = (TextView)rootView.findViewById(R.id.buyButton);
        TextView tit27 = (TextView)rootView.findViewById(R.id.sellButton);




        balance.setTypeface(supportfont);
        money.setTypeface(supportfont);
        tit1.setTypeface(supportfont);
        tit2.setTypeface(supportfont);
        tit3.setTypeface(supportfont);
        tit4.setTypeface(supportfont);
        tit5.setTypeface(supportfont);
        tit6.setTypeface(supportfont);
        tit7.setTypeface(supportfont);
        tit8.setTypeface(supportfont);
        tit9.setTypeface(supportfont);
        tit10.setTypeface(supportfont);
        tit11.setTypeface(supportfont);
        tit12.setTypeface(supportfont);
        tit13.setTypeface(supportfont);
        tit14.setTypeface(supportfont);
        tit15.setTypeface(supportfont);
        tit16.setTypeface(supportfont);
        tit17.setTypeface(supportfont);
        tit18.setTypeface(supportfont);
        tit19.setTypeface(supportfont);
        tit20.setTypeface(supportfont);
        tit21.setTypeface(supportfont);
        tit22.setTypeface(supportfont);
        tit23.setTypeface(supportfont);
        tit24.setTypeface(supportfont);
        tit25.setTypeface(supportfont);
        tit26.setTypeface(supportfont);
        tit27.setTypeface(supportfont);




        final Button button = (Button)rootView.findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                current = "set";

                EditText text = (EditText)rootView.findViewById(R.id.searchField);

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("ID", "-1");

                String type = "price";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = del;
                backgroundWorker.execute(type, text.getText().toString().toLowerCase(), id);
            }
        });

        final Button buyButton = (Button)rootView.findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                current = "purchase";

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("ID", "-1");
                String value = sharedPreferences.getString("VALUE", "-1");

                EditText text = (EditText)rootView.findViewById(R.id.sharesText);
                if (text.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please complete all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(text.getText().toString());

                EditText ticker = (EditText)rootView.findViewById(R.id.searchField);

                if (prevText != null){
                    if (!prevText.equals(ticker.getText().toString())){
                        Toast.makeText(getActivity(), "Please enter a ticker above", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    valid = true;
                }
                if (!valid){
                    Toast.makeText(getActivity(), "Please enter a ticker above", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (num > 0){

                    String type = "purchase";

                    BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                    backgroundWorker.delegate = del;
                    backgroundWorker.execute(type, id, value, Integer.toString(num), ticker.getText().toString());
                }

            }
        });

        final Button sellButton = (Button)rootView.findViewById(R.id.sellButton);
        sellButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                current = "sell";

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("ID", "-1");
                String value = sharedPreferences.getString("VALUE", "-1");

                EditText text = (EditText)rootView.findViewById(R.id.sharesText);
                if (text.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please complete all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(text.getText().toString());

                EditText ticker = (EditText)rootView.findViewById(R.id.searchField);

                if (prevText != null){
                    if (!prevText.equals(ticker.getText().toString())){
                        Toast.makeText(getActivity(), "Please enter a ticker above", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    valid = true;
                }
                if (!valid){
                    Toast.makeText(getActivity(), "Please enter a ticker above", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (num > 0){

                    String type = "sell";

                    BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                    backgroundWorker.delegate = del;
                    backgroundWorker.execute(type, id, value, Integer.toString(num), ticker.getText().toString());
                }

            }
        });


        return rootView;
    }

    /**
     * gets the activity and sets the title to trade. Happens before OnCreateView
     * @param view android studio default parameter
     * @param savedInstanceState android studio default parameter
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Trade");
    }

    /**
     * This function talks to the database and replaces the textviews with the stock information and performs the transactions.
     * This is done by finding the id's of the textviews, and replacing them with the values retrieved
     * from the background worker.
     *
     * It also performs all of the buy and sell interaction, such as checking if the user has enough money,
     * updating the prices, sending error messages, and performing the transaction
     * @param result the stock name
     */
    @Override
    public void processFinish(String result) {

        alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("NOTICE");

        if (current.equals("set")) {
            String[] array = result.split("===");
            if (array.length > 1) {

                valid = true;
                prevText = (String)((EditText)rootView.findViewById(R.id.searchField)).getText().toString();
                // put text where it needs to go
                TextView nameText = (TextView)rootView.findViewById(R.id.nameView);
                TextView priceText = (TextView) rootView.findViewById(R.id.priceView);
                TextView volumeText = (TextView)rootView.findViewById(R.id.volView);
                TextView changeText = (TextView) rootView.findViewById(R.id.changeView);
                TextView weekText = (TextView) rootView.findViewById(R.id.weekView);
                TextView openText = (TextView) rootView.findViewById(R.id.openView);
                TextView mktText = (TextView) rootView.findViewById(R.id.mktView);
                TextView peText = (TextView) rootView.findViewById(R.id.peView);
                TextView divText = (TextView) rootView.findViewById(R.id.divView);
                TextView epsText = (TextView) rootView.findViewById(R.id.EPSView);
                TextView instText = (TextView) rootView.findViewById(R.id.instView);

                nameText.setText(array[0]);
                priceText.setText(array[2]);
                volumeText.setText(array[6]);
                changeText.setText(array[5]);
                weekText.setText(array[7]);
                openText.setText(array[8]);
                mktText.setText(array[9]);
                peText.setText(array[10]);
                divText.setText(array[11]);
                epsText.setText(array[12]);
                instText.setText(array[14]);



                double eps = Double.parseDouble(epsText.getText().toString());

                if (eps < 0){
                    alertDialog.setMessage("If owned, we recommend that you sell this stock");
                }
                else if (eps < 4 && eps >= 0){
                    alertDialog.setMessage("If owned, we recommend that you hold this stock");
                }
                else if (eps >= 4){
                    alertDialog.setMessage("We recommend that you purchase this stock");
                }
                alertDialog.show();

                // get image
                current = "graph";

                String type = "graph";

                EditText text = (EditText)rootView.findViewById(R.id.searchField);

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = del;
                backgroundWorker.execute(type, text.getText().toString().toLowerCase());



            } else {
                alertDialog.setMessage("Please enter a valid ticker...");
                alertDialog.show();
            }
        }
        else if (current.equals("graph")){


            ImageView image = (ImageView)rootView.findViewById(R.id.graphImage);
            byte[] decodedString = Base64.decode(result, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            image.setImageBitmap(decodedByte);

        }
        else if (current.equals("purchase")){
            if (Double.parseDouble(result) >= 0){
                Toast.makeText(getActivity(), "Purchase confirmed!", Toast.LENGTH_SHORT).show();

                EditText text = (EditText)rootView.findViewById(R.id.sharesText);

                int num = Integer.parseInt(text.getText().toString());
                TextView priceText = (TextView)rootView.findViewById(R.id.priceView);
                double price = Double.parseDouble(priceText.getText().toString());

                balance -= num * price;
                TextView balanceDisplay = (TextView)rootView.findViewById(R.id.balancedisplay);
                TextView instText = (TextView) rootView.findViewById(R.id.instView);
                String bal = String.valueOf("$"+String.format("%.2f", balance));
                balanceDisplay.setText(bal);
                instText.setText(Integer.toString(Integer.parseInt(instText.getText().toString()) + num));
                // TextView balanceDisp = (TextView)rootView.findViewById(R.id.balancedisp);
                // balanceDisp.setText("$ " + Double.toString(balance));

                SharedPreferences sharedPref = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("VALUE", Double.toString(balance));
                editor.commit();

                valid = false;

                String type = "login";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = this;
                backgroundWorker.execute(type, "", "", "false");

                PurchaseState_Actual = true;
                if(PurchaseState_Actual==PurchaseState_Expected){
                    Log.d("Purchase State: ","True");
                }
            }
            else {
                alertDialog.setMessage("You do not have enough money to complete the transaction...");
                alertDialog.show();
                Log.d("Purchase State: ","False");
            }

        }
        else if (current.equals("sell")){
            if (Double.parseDouble(result) > 0){
                Toast.makeText(getActivity(), "Sale confirmed!", Toast.LENGTH_SHORT).show();

                EditText text = (EditText)rootView.findViewById(R.id.sharesText);

                int num = Integer.parseInt(text.getText().toString());
                TextView priceText = (TextView)rootView.findViewById(R.id.priceView);
                double price = Double.parseDouble(priceText.getText().toString());

                balance += num * price;
                TextView balanceDisplay = (TextView)rootView.findViewById(R.id.balancedisplay);
                TextView instText = (TextView) rootView.findViewById(R.id.instView);

                String bal = String.valueOf("$"+String.format("%.2f", balance));
                balanceDisplay.setText(bal);
                instText.setText(Integer.toString(Integer.parseInt(instText.getText().toString()) - num));
                // TextView balanceDisp = (TextView)rootView.findViewById(R.id.balancedisp);
                // balanceDisp.setText("$ " + Double.toString(balance));

                SharedPreferences sharedPref = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("VALUE", Double.toString(balance));
                editor.commit();

                valid = false;
                SellState_Actual = true;
                if(SellState_Actual==SellState_Expected){
                    Log.d("Sell State: ","True");
                }
                String type = "login";

                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
                backgroundWorker.delegate = this;
                backgroundWorker.execute(type, "", "", "false");
            }
            else {
                alertDialog.setMessage("You do not own the requested number of shares...");
                alertDialog.show();
                Log.d("Sell State: ","False");
            }
        }
    }
}
