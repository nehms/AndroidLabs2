package com.example.androidlabs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



class HttpUtils {

    public static Bitmap getImage(URL url) {

        HttpURLConnection connection = null;

        try {

            connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                return BitmapFactory.decodeStream(connection.getInputStream());

            } else

                return null;

        } catch (Exception e) {

            return null;

        } finally {

            if (connection != null) {

                connection.disconnect();

            }

        }

    }

    public static Bitmap getImage(String urlString) {

        try {

            URL url = new URL(urlString);

            return getImage(url);

        } catch (MalformedURLException e) {

            return null;

        }

    }



}