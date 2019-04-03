package com.example.androidlabs;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class EmptyDetails extends AppCompatActivity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_details);

        Bundle dataToPass = getIntent().getExtras(); //get the data that was passed from FragmentExample

        MessageFragment messageFragment = new MessageFragment();
        messageFragment.setArguments( dataToPass ); //pass data to the the fragment
        messageFragment.setTablet(false); //tell the Fragment that it's on a phone.
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.empty_frame, messageFragment)
                .addToBackStack("AnyName")
                .commit();



    }

}