package com.insolapps.notify;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    PendingIntent pendingIntent1, pendingIntent2;
    private AlarmManager alarmManager, alarmManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Date dat = new Date();//initializes to now
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY, 12);//set the alarm time
        cal_alarm.set(Calendar.MINUTE, 22);
        cal_alarm.set(Calendar.SECOND, 0);
        if (cal_alarm.before(cal_now)) {//if its in the past increment
            cal_alarm.add(Calendar.DATE, 1);
        }


        Intent myIntent = new Intent(MainActivity.this, Receiver.class);
        // myIntent.putExtra("id", "0");
        pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 0,
                myIntent, 0);

        alarmManager.setRepeating(AlarmManager.RTC, cal_alarm.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                pendingIntent1);


    }
}
