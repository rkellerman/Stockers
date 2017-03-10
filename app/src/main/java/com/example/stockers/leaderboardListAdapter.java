package com.example.stockers;

import android.content.Context;
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

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.leaderboard_row_model, null);
        }

        // OUR VIEWHOLDER OBJECT
        final ViewHolder holder = new ViewHolder();

        //INITIALIZE VIEWS
        holder.rankLeader= (TextView) convertView.findViewById(R.id.list_leaderboard_rank);
        holder.investorLeader= (TextView) convertView.findViewById(R.id.list_leaderboard_investor);
        holder.networthLeader= (TextView) convertView.findViewById(R.id.list_leaderboard_networth);

        //ASSIGN VIEWS
        holder.rankLeader.setText(String.valueOf(rankLeader[position]));
        holder.investorLeader.setText(investorLeader[position]);
        holder.networthLeader.setText(String.valueOf("$" +networthLeader[position]));

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}

