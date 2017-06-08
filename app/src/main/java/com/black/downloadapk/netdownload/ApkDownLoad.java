package com.black.downloadapk.netdownload;

import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin
 * description :
 * on 2016/10/14.
 */
public class ApkDownLoad {

    private String fileName;

    private int size;

    public ApkDownLoad(String fileName, int size) {
        this.fileName = fileName;
        this.size = size;
    }

    public void downApk() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Contant.HTTPURL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(10 * 1000);
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setUseCaches(false);

                    urlConnection.connect();

                    int code = urlConnection.getResponseCode();

                    if (code == 200) {
                        int length = urlConnection.getContentLength();
                        Log.w("black", length + "");
                        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(fileName), "rw");
                        randomAccessFile.setLength(length);
                        randomAccessFile.close();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    int KB = 1024;

    int MB = 1024 * KB;

    int GB = 1024 * MB;

    private String getSize(int size) {
        if (size / GB > 0) {
            return (size / (float) GB) + "";
        } else if (size / MB > 0) {
            return (size / (float) MB) + "";
        } else {
            return size + "";
        }
    }

}
