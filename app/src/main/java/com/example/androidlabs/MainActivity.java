package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Button;
import android.view.View;

import static android.content.Context.MODE_PRIVATE;


public class MainActivity extends AppCompatActivity {

     SharedPreferences mPreferences;
     //String sharedPrefFile = "com.example.android.hellosharedprefs";
     // String emailKey="1";
   // String emailAddress;
    EditText emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);
        mPreferences = getSharedPreferences("email", MODE_PRIVATE);
        emailText= findViewById(R.id.email);


      //emailAddress=(EditText)this.getDelegate().findViewById(R.id.nextEmail);
     // sharedPrefFile=getSharedPreferences("email",Context.MODE_PRIVATE);
        String savedEmail= mPreferences.getString("ReserveName","");
           emailText.setText(savedEmail);

      /*  if (savedInstanceState != null) {
            emailAddress = savedInstanceState.getString(emailKey);
            if ( emailAddress != null ||  emailAddress!= "") {
                emailText.setText(String.format("%s",emailAddress));
            }
        }*/

    }
    public void onClick(View view){
        Intent intent=new Intent(view.getContext(), ProfileActivity.class);
        intent.putExtra("email",
                emailText.getText().toString());
        startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        EditText email= findViewById(R.id.email);

        String emailAddress = emailText.getText().toString();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("ReserveName",emailAddress);
        preferencesEditor.commit();



    }













}
