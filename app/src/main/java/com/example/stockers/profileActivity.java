package com.example.stockers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import static android.R.attr.onClick;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Ryan Kellerman
 */

public class profileActivity extends Fragment implements View.OnClickListener, AsyncResponse{
    public boolean ProfileState_actual = false;
    public boolean ProfileState_expected = true;

    ImageView profileImageView;

    Button shareButton;
    /**
     * Creates page upon opening of Profile Page. Defines variables and tabs.
     * @param inflater
     * Android Studio Default Parameter
     * @param container
     * Android Studio Default Parameter
     * @param savedInstanceState
     * Android Studio Default Parameter
     * @return
     * rootView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Unit testing...
        ProfileState_actual = true;
        if(ProfileState_actual == ProfileState_expected) {
            Log.d("Profile State: ", "True");
        }
        else{
            Log.d("Profile State: ", "False");
        }


        View rootView = inflater.inflate(R.layout.profile_layout, container, false);
        Typeface titlefont = Typeface.createFromAsset(getContext().getAssets(),"Lobster 1.4.otf");
        Typeface supportfont = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-UltraLight.otf");

        TextView tit = (TextView)rootView.findViewById(R.id.My_Profile_Title);
        TextView tit1 = (TextView)rootView.findViewById(R.id.usernameProf);
        TextView tit2 = (TextView)rootView.findViewById(R.id.networth_text);
        TextView tit3 = (TextView)rootView.findViewById(R.id.tab_settings_username);
        TextView tit4 = (TextView)rootView.findViewById(R.id.tab_settings_email);
        TextView tit5 = (TextView)rootView.findViewById(R.id.tab_settings_password);
        TextView tit6 = (TextView)rootView.findViewById(R.id.tab_settings_retypepassword);
        TextView tit7 = (TextView)rootView.findViewById(R.id.sharebutton);


        tit.setTypeface(titlefont);
        tit1.setTypeface(supportfont);
        tit2.setTypeface(supportfont);
        tit3.setTypeface(supportfont);
        tit4.setTypeface(supportfont);
        tit5.setTypeface(supportfont);
        tit6.setTypeface(supportfont);
        tit7.setTypeface(supportfont);


        // share button
        shareButton = (Button)rootView.findViewById(R.id.sharebutton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent myIntent =new Intent(Intent.ACTION_SEND);
                myIntent.setType("Text/plain");
                String shareBody = "Let your friends know about the app!";
                String shareSub = "Download STOCKERS and learn about the stock market right now!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share via"));
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String playerText = sharedPreferences.getString("PLAYER", "-1");
        String activityText = sharedPreferences.getString("ACTIVITY", "-1");

        String[] array = playerText.split(" ");

        String email = array[3];
        String netWorth = array[5];

        TextView emailView = (TextView)rootView.findViewById(R.id.usernameProf);
        TextView netWorthView = (TextView)rootView.findViewById(R.id.networth_text);

        emailView.setText("Email:  " + email);

        String bal = String.valueOf("$"+String.format("%.2f", Double.parseDouble(netWorth)));
        netWorthView.setText("Net:  " + bal);

        TabHost tabHost = (TabHost) rootView.findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Settings");
        tabSpec.setContent(R.id.Settings);
        tabSpec.setIndicator("Settings");
        tabHost.addTab(tabSpec);



        //tabSpec = tabHost.newTabSpec("News");
        //tabSpec.setContent(R.id.News);
        //tabSpec.setIndicator("News");
        //tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("Activity");
        tabSpec.setContent(R.id.Activity);
        tabSpec.setIndicator("Activity");
        tabHost.addTab(tabSpec);

        ListView listView = (ListView)rootView.findViewById(R.id.activityListView);
        String[] items;
        if (activityText.equals(-1)){
            items = new String[1];
            items[0] = "No activity to show...";
        }
        else {
            activityText = activityText.substring(0, activityText.length()-3);
            items = activityText.split("!!!");
        }

        ListAdapter adapter = new activityListAdapter(getActivity(), items);
        listView.setAdapter(adapter);



        profileImageView = (ImageView) rootView.findViewById(R.id.imageToUpload);
        profileImageView.setOnClickListener(this);
        return rootView;
    }

    /**
     * Sets title for page.
     * @param view
     * Android Studio Default Parameter
     * @param savedInstanceState
     * Android Studio Default Parameter
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Profile");
    }

    /**
     * Helper function to set player image.
     * @param requestCode
     * Used to verify if image is already stored
     * @param resultCode
     * Variable used for verification purposes
     * @param data
     * Input Image
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == 1){
                profileImageView.setImageURI(data.getData());
            }
        }
    }

    /**
     * When button pressed, calls options to set Player image.
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
    }

    /**
     * Function to be used later when connecting database.
     * @param result
     */
    @Override
    public void processFinish(String result) {

    }
}
