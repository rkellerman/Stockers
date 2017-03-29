package com.example.stockers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class portfolioListAdapter extends ArrayAdapter<String> {

    //DECLARATIONS
    String[] stockTicker={};
    double[] stockPrice={};
    int[] shares={};
    String[] percentChange={};
    Context c;
    LayoutInflater inflater;

    /**
     * This is used to initialize various portfolio variables to display to the ListView widget.
     * @param context
     * Android Studio Default Parameter
     * @param stockTicker
     * Shorthand stock ticker name for company stock
     * @param stockPrice
     * Current price of stock
     * @param shares
     * Number of shares owned
     * @param percentChange
     * Daily percent change of stock price
     */

    public portfolioListAdapter(Context context, String[] stockTicker,
                                double[] stockPrice, int[] shares, String[] percentChange) {

        super(context, R.layout.portfolio_row_model, stockTicker);

        this.c=context;
        this.stockTicker=stockTicker;
        this.stockPrice=stockPrice;
        this.shares=shares;
        this.percentChange=percentChange;
    }

    public class ViewHolder
    {
        TextView stockTicker;
        TextView stockPrice;
        TextView shares;
        TextView totalValue;
        TextView percentChange;
    }

    /**
     * Sets all values in XML file to display on app.
     * @param position
     * Current position of an element in the ListView widget
     * @param convertView
     * Android Studio Default Parameter
     * @param parent
     * Android Studio Default Parameter
     * @return convertView
     *
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.portfolio_row_model, null);
        }

        // OUR VIEWHOLDER OBJECT
        final ViewHolder holder = new ViewHolder();

        //INITIALIZE VIEWS
        holder.stockTicker= (TextView) convertView.findViewById(R.id.list_portfolio_ticker);
        holder.stockPrice= (TextView) convertView.findViewById(R.id.list_portfolio_price);
        holder.shares= (TextView) convertView.findViewById(R.id.list_portfolio_shares);
        holder.totalValue= (TextView) convertView.findViewById(R.id.list_portfolio_value);
        holder.percentChange= (TextView) convertView.findViewById(R.id.list_portfolio_change);


        //ASSIGN VIEWS
        holder.stockTicker.setText(stockTicker[position].toUpperCase());
        holder.stockPrice.setText(String.valueOf("$"+stockPrice[position]));
        if (shares[position] == 1){
            holder.shares.setText(String.valueOf(shares[position] + " Share"));
        }else {
            holder.shares.setText(String.valueOf(shares[position] + " Shares"));
        }

        holder.totalValue.setText(String.valueOf("$"+(String.format("%.2f", stockPrice[position]*shares[position]))));
        holder.percentChange.setText(String.valueOf(percentChange[position]));

        //Here we are going to implement UI modifications (Frank)...

        //Instantiate our font...
        Typeface numberfont = Typeface.createFromAsset(getContext().getAssets(),"din.ttf");

        //Reference the text object...
        TextView StockName = (TextView)convertView.findViewById(R.id.list_portfolio_ticker);
        TextView StockPrice = (TextView)convertView.findViewById(R.id.list_portfolio_price);
        //TextView Stocknamehead = (TextView)convertView.findViewById(R.id.dispshares);
        //TextView Stockpricehead = (TextView)convertView.findViewById(R.id.dispshares);


        //Just do it...
        StockName.setTypeface(numberfont);
        StockPrice.setTypeface(numberfont);
        //Stocknamehead.setTypeface(numberfont);
        //Stockpricehead.setTypeface(numberfont);





        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
