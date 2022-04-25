package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "HTTPURLCONNECTION test";
    private static String Authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn_onclick(View view) throws IOException {
        String url = "https://api.yshyqxx.com/GET/api/v1/aggTrades?symbol=BTCUSDT";
        String data = "";

        data = GET(url);
        Log.d(TAG, data);
    }

    private String GET(String APIUrl) throws IOException {
        String result = "";
        HttpsURLConnection connection;
        try {
            URL url = new URL(APIUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("authentication", MainActivity.Authentication);
            connection.setDoInput(true);

            InputStream inputStream = connection.getInputStream();
            int status = connection.getResponseCode();
            Log.d(TAG, String.valueOf(status));
            if(inputStream != null){
                InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader in = new BufferedReader(reader);

                String line="";
                while ((line = in.readLine()) != null) {
                    result += (line+"\n");
                }
            } else{
                result = "Did not work!";
            }
            return  result;
        } catch (MalformedURLException e) {
            Log.d("ATask InputStream", e.getLocalizedMessage());
            e.printStackTrace();
            return result;
        }
    }
}