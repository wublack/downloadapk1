package com.black.downloadapk;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.black.downloadapk.netdownload.ApkDownLoad;
import com.black.downloadapk.netdownload.Contant;
import com.black.downloadapk.netdownload.DownLoadThread;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private NotificationManager mNotificationManager;
    private Notification mNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        //fff
        //几个月了荒废了
        initViews();
    }

    private void initViews() {
        TextView tvStart = (TextView) findViewById(R.id.tv_start_download);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DownLoadThread(Contant.HTTPURL).start();
            }
        });


    }
}
