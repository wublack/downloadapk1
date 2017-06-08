package com.black.downloadapk.netdownload;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin
 * description :
 * on 2016/10/14.
 */
public class DownLoadThread extends Thread {

    private String httpUrl;


    public DownLoadThread(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL(httpUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoInput(true);

            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod("POST");

            urlConnection.connect();

            int code = urlConnection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) {
                InputStream is = urlConnection.getInputStream();

                Log.w("black", is == null ? "kong" : "" + is.read());

            }


        } catch (Exception e) {

        }

    }
}
