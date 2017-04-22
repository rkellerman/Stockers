package com.example.stockers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stockers.AsyncResponse;

import java.util.List;

/**
 * Created by RyanMini on 4/17/17.
 */

public class ChatActivity extends Fragment implements AsyncResponse, View.OnClickListener{

    View rootView;
    ListView chatList;
    /*
    here are some dummy variables. the chat would have to reload the arrays every time for all
    the messages to show up so i think we should only have the last 20 messages come up.
     */
    String[] userName = null;
    String[] userMessage = null;
    String[] messageTime = null;

    Button sendButton = null;
    String prev = "";
    String current = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chat_layout, container, false);
        chatList = (ListView) rootView.findViewById(R.id.chatListView);

        sendButton = (Button) rootView.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("CHAT", "-1");
        prev = text;

        String[] array = new String[0];

        if (text.length() > 0) {
            text = text.substring(0, text.length() - 3);
            array = text.split("!!!");
        }

        userName = new String[array.length];
        userMessage = new String[array.length];
        messageTime = new String[array.length];

        for (int i = 0; i < array.length; i++){

            String[] entries = array[i].split("===");
            Log.d("AYOOO", array[i]);
            userName[i] = entries[0].substring(1, entries[0].length());
            userMessage[i] = entries[1];
            messageTime[i] = entries[2];

        }

        //ADAPTER Function
        ListAdapter adapter = new ChatListAdapter(getActivity(), userName, userMessage, messageTime);
        chatList.setAdapter(adapter);

        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                tryUpdate();
                ha.postDelayed(this, 5000);
            }
        }, 5000);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Trade");
    }

    public void tryUpdate(){

        current = "update";
        Log.d("YOOOOOOO", "I'm doing the thing");

        if (getActivity() == null){
            return;
        }
        BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
        backgroundWorker.delegate = this;
        backgroundWorker.execute("getMessages");
    }

    @Override
    public void processFinish(String result) {

            try {
                if (getActivity() == null){
                    return;
                }
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);

                String text = sharedPreferences.getString("CHAT", "-1");
                if (current.equals("update")) {
                    if (prev != text) {

                    } else {
                        return;
                    }
                }
                prev = text;

                text = text.substring(0, text.length() - 3);
                String[] array = text.split("!!!");

                userName = new String[array.length];
                userMessage = new String[array.length];
                messageTime = new String[array.length];

                for (int i = 0; i < array.length; i++) {

                    String[] entries = array[i].split("===");
                    Log.d("AYOOO", array[i]);
                    userName[i] = entries[0].substring(1, entries[0].length());
                    userMessage[i] = entries[1];
                    messageTime[i] = entries[2];

                }

                //ADAPTER Function
                ListAdapter adapter = new ChatListAdapter(getActivity(), userName, userMessage, messageTime);
                chatList.setAdapter(adapter);
            }
            catch (Exception e){
                return;
            }


    }

    @Override
    public void onClick(View v) {

        EditText messageText = (EditText)rootView.findViewById(R.id.messageText);
        String message = messageText.getText().toString();
        messageText.setText("");

        current = "send";

        BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
        backgroundWorker.delegate = this;
        backgroundWorker.execute("sendMessage", message);


    }
}
