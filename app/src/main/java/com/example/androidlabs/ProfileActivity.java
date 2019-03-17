package com.example.androidlabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class ProfileActivity extends AppCompatActivity {
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton mImageButton;
    SharedPreferences mPreferences;
    String sharedPrefFile = "com.example.android.hellosharedprefs";
    String emailKey="1";
    String emailAddress;EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        //Log.e(ACTIVITY_NAME, “In function:"+"onCreate");
        Log.e(ACTIVITY_NAME, "In Function: onCreate");
        mImageButton= findViewById(R.id.imageButton2);
        EditText emailText= findViewById(R.id.nextEmail);
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        emailText.setText(String.format("%s",email));

    }
    public void onClickProfile(View view){
        dispatchTakePictureIntent();

    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

    public void onClick(View view){
        Intent intent=new Intent(view.getContext(), ChatRoomActivity.class);

        startActivity(intent);
    }

    public void onClickToolbar(View view){
        Intent intent=new Intent(view.getContext(), TestToolbar.class);

        startActivity(intent);
    }

    public void onClickWeather(View view){
        Intent intent=new Intent(view.getContext(), WeatherForecast.class);

        startActivity(intent);
    }



}
