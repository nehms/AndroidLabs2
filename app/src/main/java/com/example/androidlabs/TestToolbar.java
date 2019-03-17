package com.example.androidlabs;

import android.content.DialogInterface;
import android.content.Context;
import android.database.Cursor;
import android.inputmethodservice.KeyboardView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.util.Log;
import android.support.v7.app.AlertDialog;
import android.widget.BaseAdapter;



public class TestToolbar extends AppCompatActivity {
    android.support.v7.widget.Toolbar tBar;
    String Message="This is the initial message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

          tBar = findViewById(R.id.toolbar);

        setSupportActionBar(tBar);


    }


       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


      //  return super.onCreateOptionsMenu(menu);
           return true;

       }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

     //   super.onOptionsItemSelected(item);

        switch(item.getItemId()) {

            //first case
            case R.id.superman:
                Toast.makeText(getBaseContext(), Message, Toast.LENGTH_SHORT).show();
                break;

            //second Item
            case R.id.flash:
                alertExample();
                break;

            //third item
            case R.id.sonic:
                //what to do when the menu item is selected:
                Snackbar snackB = Snackbar.make(tBar, "Go Back?", Snackbar.LENGTH_LONG).setAction("GO BACK", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        finish();

                    }

                });

                snackB.show();

                break;


            //overflow selected
            case R.id.cloud:
                Toast.makeText(getBaseContext(), "You clicked on the Overflow menu", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

/*
public void overflowToast(View v){

    Toast.makeText(this, "You clicked on the overflow menu", Toast.LENGTH_LONG).show();


}
*/

    public void alertExample() {
        View middle = getLayoutInflater().inflate(R.layout.view_extra_stuff, null);

        //different
        EditText et = (EditText)middle.findViewById(R.id.view_edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("")
                .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // What to do on Accept
                            Message=et.getText().toString();
                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // What to do on Cancel

                    }
                }).setView(middle);

        builder.create().show();
    }
}
