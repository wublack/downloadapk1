package com.black.downloadapk.netdownload;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.black.downloadapk.MainActivity;
import com.black.downloadapk.R;

/**
 * Created by admin
 * description :
 * on 2016/10/14.
 */
public class MyNotification {

    private NotificationManager mNotificationManager;
    private Notification notification;

    public void showNotification(Context mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);//点击进度条，进入程序
        PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
//                mNotificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
//                mNotification = new Notification();
//                mNotification.icon = R.mipmap.ic_launcher;
//                mNotification.tickerText = "开始下载";
//                mNotification.contentView = new RemoteViews(getPackageName(), R.layout.notication_layout);//通知栏中进度布局
//                mNotification.contentIntent = pIntent;
//                mNotificationManager.notify(0, mNotification);

        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(mContext);
        RemoteViews mRemoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notication_layout);
        builder.setContent(mRemoteViews);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pIntent);

        notification = builder.getNotification();
        mNotificationManager.notify(1, notification);

    }

    /**
     * 更改自定义布局文件中的进度条的值
     *
     * @param p 进度值(0~100)
     */
    public void changeProgressStatus(int p, int downStatus) {
        if (notification.contentView != null) {
            if (downStatus == Contant.DOWNLOADERROR)
                notification.contentView.setTextViewText(R.id.tv_state, "下载失败！ ");
            else if (downStatus == Contant.DOWNLOADCOMPLETE)
                notification.contentView.setTextViewText(R.id.tv_state, "下载完成，请点击安装");
            else
                notification.contentView.setTextViewText(R.id.tv_state, "进度(" + p + "%) : ");
            notification.contentView.setProgressBar(R.id.progressbar, 100, p, false);
        }
        mNotificationManager.notify(1, notification);
    }

}
