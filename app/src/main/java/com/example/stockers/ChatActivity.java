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

public class ChatActivity extends Fragment implements AsyncResponse {

    View rootView;
    ListView chatList;
    /*
    here are some dummy variables. the chat would have to reload the arrays every time for all
    the messages to show up so i think we should only have the last 20 messages come up.
     */
    String[] userName = {"Namit", "Harsh", "Namit"};
    String[] userMessage = {"Knock Knock", "Who's there", "Go fuck yourself"};
    String[] messageTime = {"3:27 PM", "3:28 PM", "3:29 PM"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chat_layout, container, false);
        chatList = (ListView) rootView.findViewById(R.id.chatListView);

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

    }
}
