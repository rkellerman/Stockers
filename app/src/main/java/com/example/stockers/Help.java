package com.example.stockers;

import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Help extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_help, container, false);

        //UI Enhancements...
        Typeface myTpeface = Typeface.createFromAsset(getContext().getAssets(),"Lobster 1.4.otf");
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");

        TextView title1 = (TextView)rootView.findViewById(R.id.help_heading);
        TextView title2 = (TextView)rootView.findViewById(R.id.fifty_two);
        TextView title3 = (TextView)rootView.findViewById(R.id.Div_Yield);
        TextView title4 = (TextView)rootView.findViewById(R.id.Networth);
        TextView title5 = (TextView)rootView.findViewById(R.id.Open);
        TextView title6 = (TextView)rootView.findViewById(R.id.Price_Earnings);
        TextView title7 = (TextView)rootView.findViewById(R.id.Share_Number);
        TextView title8 = (TextView)rootView.findViewById(R.id.Share_Price);
        TextView title9 = (TextView)rootView.findViewById(R.id.Change);
        TextView title10 = (TextView)rootView.findViewById(R.id.EMA);
        TextView title11 = (TextView)rootView.findViewById(R.id.EPS);
        TextView title12 = (TextView)rootView.findViewById(R.id.Market_Cap);
        TextView title13 = (TextView)rootView.findViewById(R.id.Volume);


        TextView def2 = (TextView)rootView.findViewById(R.id.fifty_two_definition);
        TextView def3 = (TextView)rootView.findViewById(R.id.Div_Yield_definition);
        TextView def4 = (TextView)rootView.findViewById(R.id.Networth_definition);
        TextView def5 = (TextView)rootView.findViewById(R.id.Open_definition);
        TextView def6 = (TextView)rootView.findViewById(R.id.Price_Earnings_definition);
        TextView def7 = (TextView)rootView.findViewById(R.id.Share_Number_definition);
        TextView def8 = (TextView)rootView.findViewById(R.id.Share_Price_definition);
        TextView def9 = (TextView)rootView.findViewById(R.id.Change_definition);
        TextView def10 = (TextView)rootView.findViewById(R.id.EMA_definition);
        TextView def11 = (TextView)rootView.findViewById(R.id.EPS_definition);
        TextView def12 = (TextView)rootView.findViewById(R.id.Market_Cap_definition);
        TextView def13 = (TextView)rootView.findViewById(R.id.Volume_definition);

        title1.setTypeface(myTpeface);
        title2.setTypeface(myTpeface);
        title3.setTypeface(myTpeface);
        title4.setTypeface(myTpeface);
        title5.setTypeface(myTpeface);
        title6.setTypeface(myTpeface);
        title7.setTypeface(myTpeface);
        title8.setTypeface(myTpeface);
        title9.setTypeface(myTpeface);
        title10.setTypeface(myTpeface);
        title11.setTypeface(myTpeface);
        title12.setTypeface(myTpeface);
        title13.setTypeface(myTpeface);

        def2.setTypeface(supportfont);
        def3.setTypeface(supportfont);
        def4.setTypeface(supportfont);
        def5.setTypeface(supportfont);
        def6.setTypeface(supportfont);
        def7.setTypeface(supportfont);
        def8.setTypeface(supportfont);
        def9.setTypeface(supportfont);
        def10.setTypeface(supportfont);
        def11.setTypeface(supportfont);
        def12.setTypeface(supportfont);
        def12.setTypeface(supportfont);
        def13.setTypeface(supportfont);



        return rootView;
    }
}
