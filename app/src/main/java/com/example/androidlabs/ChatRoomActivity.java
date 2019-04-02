package com.example.androidlabs;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class ChatRoomActivity extends AppCompatActivity {

    private ListView chatV;
    private Button btn, receive;//button send or receive
    private EditText edt;
    private List<Message> messages = new ArrayList<>();
    private DatabaseHelper databaseHelp;
    private ChatAdapter messageAdapter;
    public static final String ITEM_ID = "id";
    public static final String ITEM_POSITION = "position";
    public static final String ITEM_MSG = "msg";
    public static final String ITEM_TYPE = "type";

    public static final int EMPTY_ACTIVITY = 345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        chatV = findViewById(R.id.listChat);
        btn = findViewById(R.id.buttonSend);
        receive = findViewById(R.id.buttonReceive);
        edt = findViewById(R.id.messageInput);
        databaseHelp = new DatabaseHelper(this);

        boolean isTablet = findViewById(R.id.empty_frame) != null; //check if the FrameLayout is loaded --- fragment

        Cursor cursor = databaseHelp.viewData();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {

                Message model = new Message(cursor.getLong(0), cursor.getString(1), cursor.getInt(2) == 0);
                messages.add(model);
                messageAdapter = new ChatAdapter(messages, getApplicationContext());
                chatV.setAdapter(messageAdapter);
            }
        } else {
            messageAdapter = new ChatAdapter(messages, getApplicationContext());
            chatV.setAdapter(messageAdapter);
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sendMessage = edt.getText().toString();
                updateMessageIntoList(sendMessage, true);
            }
        });

        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendMessage = edt.getText().toString();
                updateMessageIntoList(sendMessage, false);
            }
        });

        Log.e("ChatRoomActivity", "onCreate");

        chatV.setOnItemClickListener((parent, view, position, id) -> {


            Message message = (Message) messageAdapter.getItem(position);

            Bundle dataToPass = new Bundle();
            dataToPass.putLong(ITEM_ID, message.getId());
            dataToPass.putInt(ITEM_POSITION, position);
            // dataToPass.putLong(ITEM_ID, id);
            dataToPass.putString(ITEM_MSG, message.getMessage());
            dataToPass.putBoolean(ITEM_TYPE, message.isChecker());
            Log.e("Bundle data id", ITEM_ID);
            Log.e("position", ITEM_POSITION);
            Log.e("positionGet", Integer.toString(dataToPass.getInt(ITEM_POSITION)));
            Log.e("msg", ITEM_MSG);
            Log.e("item_type==", ITEM_TYPE);

            if (isTablet) {
                MessageFragment messageFragment = new MessageFragment();//add a DetailFragment  ---Message Fragment
                messageFragment.setArguments(dataToPass); //pass it a bundle for information
                messageFragment.setTablet(true);  //tell the fragment if it's running on a tablet or not
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.empty_frame, messageFragment) //Add the fragment in FrameLayout
                        .addToBackStack("AnyName") //make the back button undo the transaction
                        .commit(); //actually load the fragment.
            } else //isPhone
            {
                Intent nextActivity = new Intent(ChatRoomActivity.this, EmptyDetails.class);
                nextActivity.putExtras(dataToPass); //send data to next activity

                Log.e("phone intent pos", Integer.toString(dataToPass.getInt(ITEM_POSITION)));
                startActivityForResult(nextActivity, EMPTY_ACTIVITY); //make the transition
            }

            // Display the selected item text on TextView
            //tv.setText("Your favorite : " + selectedItem);
            //}
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == EMPTY_ACTIVITY) {
            if (resultCode == RESULT_OK) //if you hit the delete button instead of back button
            {
                long id = data.getLongExtra(ITEM_ID, 0);
                int position = data.getIntExtra(ITEM_POSITION, 0);
                deleteMessageId(id, position);
            }
        }
    }

   public void deleteMessageId(long id, long position) {

        Log.i("Delete this message:", " id=" + id);
        int result = databaseHelp.deleteData(id);

        if (result == 1) {
             messages.remove(position);
            messageAdapter.notifyDataSetChanged();
        }
    }
  /*  public void deleteMessageId(long id) {

        Log.i("Delete this message:", " id=" + id);
        int result = databaseHelp.deleteData(id);

       messages.remove(id);
            messageAdapter.notifyDataSetChanged();

    }*/

    private void updateMessageIntoList(final String msg, final boolean isSend) {
        final Message model = new Message(msg, isSend);
        long id = databaseHelp.insertData(msg, isSend);
        if (id > -1) {
            model.setId(id);
            messages.add(model);
            messageAdapter.notifyDataSetChanged();
            edt.setText("");
        }
        //viewFullData();
    }


    private void viewFData() {

        Cursor cursor = databaseHelp.viewData();

        if (cursor.getCount() != 0) {

            while (cursor.moveToNext()) {

                Message msg = new Message(cursor.getString(1), cursor.getInt(2) == 0);

                messages.add(msg);

                ChatAdapter chatAdapter = new ChatAdapter(messages, getApplicationContext());

                chatV.setAdapter(chatAdapter);

            }

        }

    }


     class ChatAdapter extends BaseAdapter {
         private List<Message> msg;
         private Context ctx;
         private LayoutInflater inflater;


         public ChatAdapter(List<Message> msg, Context ctx) {
             this.ctx = ctx;
             this.msg = msg;

         }

         @Override
         public int getCount() {
             return msg.size();
         }

         @Override
         public Object getItem(int position) {
             return msg.get(position);
         }


         @Override
         public long getItemId(int position) {
             return msg.get(position).getId();
         }


         @Override
         public View getView(int position, View convertView, ViewGroup parent) {


             View result = convertView;

             if (msg.get(position).isChecker()) {

                 result = LayoutInflater.from(ctx).inflate(R.layout.activity_send, null);

             } else {
                 result =  LayoutInflater.from(ctx).inflate(R.layout.activity_receive, null);


             }

             TextView message = (TextView) result.findViewById(R.id.textViewMessage);
             message.setText(msg.get(position).getMessage()); // get the string at position


             return result;

         }

     }
}