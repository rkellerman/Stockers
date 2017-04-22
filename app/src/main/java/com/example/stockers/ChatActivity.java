package com.example.stockers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chat_layout, container, false);
        chatList = (ListView) rootView.findViewById(R.id.chatListView);

        sendButton = (Button) rootView.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("CHAT", "-1");
        prev = text;


        text = text.substring(0, text.length()-3);
        String[] array = text.split("!!!");

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

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Trade");
    }



    @Override
    public void processFinish(String result) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("1", Context.MODE_PRIVATE);
        String text = sharedPreferences.getString("CHAT", "-1");
        prev = text;

        text = text.substring(0, text.length()-3);
        String[] array = text.split("!!!");

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

    }

    @Override
    public void onClick(View v) {

        EditText messageText = (EditText)rootView.findViewById(R.id.messageText);
        String message = messageText.getText().toString();
        messageText.setText("");

        BackgroundWorker backgroundWorker = new BackgroundWorker(getContext(), getActivity());
        backgroundWorker.delegate = this;
        backgroundWorker.execute("sendMessage", message);

    }
}
