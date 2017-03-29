package com.example.stockers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class leaderboardListAdapter extends ArrayAdapter<String> {

    //DECLARATIONS
    int[] rankLeader={};
    String[] investorLeader={};
    double[] networthLeader={};
    Context c;
    LayoutInflater inflater;

    /**
     * This is used to initialize various portfolio variables to display to the ListView widget.
     * @param context
     * Android Studio Default Parameter
     * @param rankLeader
     * Array contains ordered ranks of all players
     * @param investorLeader
     * Array contains all player names
     * @param networthLeader
     * Array contains each players networth
     */

    public leaderboardListAdapter(Context context, int[] rankLeader, String[] investorLeader,
                                  double[] networthLeader) {
        super(context, R.layout.leaderboard_row_model, investorLeader);

        this.c=context;
        this.rankLeader=rankLeader;
        this.investorLeader=investorLeader;
        this.networthLeader=networthLeader;
    }

    public class ViewHolder
    {
        TextView rankLeader;
        TextView investorLeader;
        TextView networthLeader;
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
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.leaderboard_row_model, null);
        }
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");
        // VIEWHOLDER OBJECT
        final ViewHolder holder = new ViewHolder();

        //INITIALIZE VIEWS
        holder.rankLeader= (TextView) convertView.findViewById(R.id.list_leaderboard_rank);
        holder.rankLeader.setTypeface(supportfont);
        holder.investorLeader= (TextView) convertView.findViewById(R.id.list_leaderboard_investor);
        holder.investorLeader.setTypeface(supportfont);
        holder.networthLeader= (TextView) convertView.findViewById(R.id.list_leaderboard_networth);
        holder.networthLeader.setTypeface(supportfont);

        //ASSIGN VIEWS
        holder.rankLeader.setText(String.valueOf(rankLeader[position]));
        holder.investorLeader.setText(investorLeader[position]);
        holder.networthLeader.setText(String.valueOf("$" + String.format("%.2f", networthLeader[position])));

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}

