package com.example.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    ListView chatV;
    Button btn,receive;//button send or receive
    EditText edt;
    List<Message> messages;
    DatabaseHelper databaseHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        chatV = findViewById(R.id.listChat);
        btn = findViewById(R.id.buttonSend);
        receive=findViewById(R.id.buttonReceive);
        edt = findViewById(R.id.messageInput);
        messages = new ArrayList<>();

        databaseHelp = new DatabaseHelper(this);




        final ChatAdapter messageAdapter = new ChatAdapter(messages, this);
        chatV.setAdapter(messageAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Message data = new Message(edt.getText().toString(), true);
             //  messages.add(data);

               // edt.setText("");
               // messageAdapter.notifyDataSetChanged();


                if (!edt.getText().toString().equals("")) {

                    databaseHelp.insertData(edt.getText().toString(), true);

                    edt.setText("");

                    messages.clear();

                    viewData();
                }
            }
        });

        receive.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View view) {
                // Message data = new Message(edt.getText().toString(), false);
                // messages.add(data);

                // edt.setText("");
                // messageAdapter.notifyDataSetChanged();

                 if (!edt.getText().toString().equals("")) {

                     databaseHelp.insertData(edt.getText().toString(), false);

                     edt.setText("");

                     messages.clear();

                     viewData();
                 }

             }});
        viewData();

        Log.e("ChatRoomActivity","onCreate");
    }
    private void viewData(){

        Cursor cursor = databaseHelp.viewData();

        if (cursor.getCount() != 0){

            while (cursor.moveToNext()){

                Message msg = new Message(cursor.getString(1), cursor.getInt(2) == 0);

                messages.add(msg);

                ChatAdapter chatAdapter = new ChatAdapter(messages, getApplicationContext());

                chatV.setAdapter(chatAdapter);

            }

        }

    }


    private class ChatAdapter extends BaseAdapter {
    List<Message>msg;
    Context ctx;
    LayoutInflater inflater;


        public ChatAdapter(List<Message>msg, Context ctx) {
           this.ctx=ctx;
           this.msg=msg;
           this.inflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return msg.size();
        }



        @Override
        public Message getItem(int position) {
            return  msg.get(position);
        }


        @Override
        public long getItemId(int position) {
            return (long)position;
        }


        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {



            View result = convertView ;

            if(msg.get(position).isChecker()){

                result = inflater.inflate(R.layout.activity_send,null);

            }
            else { result=inflater.inflate(R.layout.activity_receive,null);


            }


            TextView message = result.findViewById(R.id.textViewMessage);
            message.setText(msg.get(position).getMessage()); // get the string at position


            return result;

        }
    }
}