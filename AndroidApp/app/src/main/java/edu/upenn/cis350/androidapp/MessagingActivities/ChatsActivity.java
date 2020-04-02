package edu.upenn.cis350.androidapp.MessagingActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.upenn.cis350.androidapp.DataInteraction.Data.Chat;
import edu.upenn.cis350.androidapp.DataInteraction.Processing.MessageProcessing.ChatProcessor;
import edu.upenn.cis350.androidapp.R;

public class ChatsActivity extends AppCompatActivity {

    private long userId;
    private List<Chat> chats;
    private ChatProcessor chatProcessor;
    private ChatAdapter adapter;
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        setTitle("Chats");
        Log.d("ChatsActivity", "Set content view");

        chatProcessor = ChatProcessor.getInstance();
        Log.d("ChatsActivity", "got processor");

        userId = getIntent().getLongExtra("userId", -1);
        chats = chatProcessor.getChatsForUser(userId);
        ListView chatListView = (ListView) findViewById(R.id.chatsListView);

        adapter = new ChatAdapter(this, userId, chats);
        chatListView.setAdapter(adapter);

        chatListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        long chatId = adapter.getItem(position).getId();
                        Intent i = new Intent(ChatsActivity.this,
                                MessagesActivity.class);
                        i.putExtra("userId", userId);
                        i.putExtra("chatId", chatId);
                        startActivity(i);
                    }
                }
        );
    }


}
