package com.example.stockers;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ryan Kellerman & Namit Patel.
 */

public class leaderboardActivity extends Fragment {
    public boolean LeaderboardState_actual = false;
    public boolean Leaderboard_expected = true;

    ListView leaderboardList;
    int[] rank = null;
    String[] investor = null;
    int netldboard=0;
    int netpfile=0;
    double[] networth = null;

    /**
     * This function initializes all variables
     * when view is created,  to values
     * obtained from our database.
     * @param inflater
     * Android Studio Default Parameter
     * @param container
     * Android Studio Default Parameter
     * @param savedInstanceState
     * Android Studio Default Parameter
     * @return rootView
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.leaderboard_layout, container, false);

        leaderboardList=(ListView) rootView.findViewById(R.id.leaderboardListView);
        Typeface myTpeface = Typeface.createFromAsset(getContext().getAssets(),"Lobster 1.4.otf");
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");

        TextView title = (TextView)rootView.findViewById(R.id.textView);

        TextView ranktitle = (TextView)rootView.findViewById(R.id.leaderboard_rank);
        TextView name = (TextView)rootView.findViewById(R.id.leaderboard_investor);
        TextView net = (TextView)rootView.findViewById(R.id.leaderboard_networth);
        TextView supporttext = (TextView)rootView.findViewById(R.id.filter_text);
        TextView sorttext1 = (TextView)rootView.findViewById(R.id.filter_top_investor);
        TextView sorttext2 = (TextView)rootView.findViewById(R.id.filter_my_friends);

        title.setTypeface(myTpeface);
        name.setTypeface(myTpeface);
        ranktitle.setTypeface(myTpeface);
        net.setTypeface(myTpeface);
        supporttext.setTypeface(myTpeface);
        sorttext1.setTypeface(supportfont);
        sorttext2.setTypeface(supportfont);



        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(netldboard==netpfile){
            Log.d("V","Net worth on Profile equal to Net worth on Leader Board: True");
        }
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String result = sharedPreferences.getString("LEADERBOARD", "0!!!0");

        result = result.substring(0, result.length()-3);
        String[] array = result.split("===");

        rank = new int[array.length];
        investor = new String[array.length];
        networth = new double[array.length];

        for (int i = 0; i < array.length; i++){
            String[] entries = array[i].split("!!!");

            rank[i] = i+1;
            investor[i] = entries[0];
            networth[i] = Double.parseDouble(entries[1]);

            if(rank[i]==array.length-1){
                LeaderboardState_actual = true;
            }
        }
        if(Leaderboard_expected==LeaderboardState_actual){
            Log.d("Leaderboard State: ","True");
        }

        //ADAPTER Function
        ListAdapter adapter = new leaderboardListAdapter(getActivity(), rank, investor, networth);
        leaderboardList.setAdapter(adapter);

        final Button defaultButton = (Button)rootView.findViewById(R.id.filter_top_investor);
        defaultButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String result = sharedPreferences.getString("LEADERBOARD", "0!!!0");

                result = result.substring(0, result.length()-3);
                String[] array = result.split("===");

                rank = new int[array.length];
                investor = new String[array.length];
                networth = new double[array.length];

                for (int i = 0; i < array.length; i++){
                    String[] entries = array[i].split("!!!");

                    rank[i] = i+1;
                    investor[i] = entries[0];
                    networth[i] = Double.parseDouble(entries[1]);

                }

                //ADAPTER Function
                ListAdapter adapter = new leaderboardListAdapter(getActivity(), rank, investor, networth);
                leaderboardList.setAdapter(adapter);


            }
        });

        final Button friendButton = (Button)rootView.findViewById(R.id.filter_my_friends);
        friendButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
                String result = sharedPreferences.getString("FRIENDLEADERBOARD", "0!!!0");

                Log.d("AYY", result);

                if (result.equals("-1")){

                    rank = new int[0];
                    investor = new String[0];
                    networth = new double[0];

                    ListAdapter adapter = new leaderboardListAdapter(getActivity(), rank, investor, networth);
                    leaderboardList.setAdapter(adapter);
                }
                else {
                    result = result.substring(0, result.length()-3);
                    String[] array = result.split("===");

                    rank = new int[array.length];
                    investor = new String[array.length];
                    networth = new double[array.length];

                    for (int i = 0; i < array.length; i++){
                        String[] entries = array[i].split("!!!");

                        rank[i] = i+1;
                        investor[i] = entries[0];
                        networth[i] = Double.parseDouble(entries[1]);

                        if(rank[i]==array.length-1){
                            LeaderboardState_actual = true;
                        }
                    }
                    ListAdapter adapter = new leaderboardListAdapter(getActivity(), rank, investor, networth);
                    leaderboardList.setAdapter(adapter);

                }


            }
        });

        return rootView;
    }

    /**
     * Sets the Title in the Header. This function is called before onCreateView.
     * @param view
     * Android Studio Default Parameter
     * @param savedInstanceState
     * Android Studio Default Parameter
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Leaderboard");
    }


}
