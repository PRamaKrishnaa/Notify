package com.insolapps.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by User2 on 6/16/2017.
 */

public class Receiver extends BroadcastReceiver {

    private int MID = 0;
    private String message = "Message";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

       /* try {
            if (intent.getStringExtra("id").equals("0")) {*/

        Log.e("my Broadcast", "works");

        ComponentName receiver = new ComponentName(context, Receiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setTicker("App Title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Message Header")
                .setContentText(message)
                .setSound(alarmSound)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true).setWhen(when);
        // .setContentIntent(pendingIntent);

        mNotifyBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
        mNotifyBuilder.build().defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
        //.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;


            /*}
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }*/

    }
}
