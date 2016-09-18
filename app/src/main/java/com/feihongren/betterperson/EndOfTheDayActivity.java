package com.feihongren.betterperson;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;

import static com.feihongren.betterperson.MainActivity.mainActivityDBHandler;
import static com.feihongren.betterperson.MainActivity.arrayAdapter;
import static com.feihongren.betterperson.MainActivity.todaysTaskArray;

/**
 * Created by fwr50 on 2016/9/18.
 */
public class EndOfTheDayActivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Add today's task to database
        ArrayList<Task> todaysTask = mainActivityDBHandler.getTodayTaskList();
        mainActivityDBHandler.addTodayTask(todaysTask);
        System.out.println("today is completed");

        //update the listview to tomorrow's task
        mainActivityDBHandler.resetAllTask(mainActivityDBHandler.getAllTaskList());
        todaysTaskArray.clear();
        todaysTaskArray.addAll(mainActivityDBHandler.getTomorrowTaskList()) ;
        arrayAdapter.notifyDataSetChanged();


        //Ask the user if they become a better person
        NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(context);
        myBuilder.setSmallIcon(R.mipmap.ic_launcher);
        myBuilder.setContentTitle("Better Person");
        //set the vibration
        long[] vibratePattern = {250, 250, 250, 250};
        myBuilder.setVibrate(vibratePattern);
        //set the sound
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        myBuilder.setSound(alarmSound);

        ArrayList<Task> todaysTaskList = mainActivityDBHandler.getTodayTaskList();
        int todaysPoints = 0;
        for (int i = 0; i < todaysTaskList.size(); i++) {
            Task currentTask = todaysTaskList.get(i);
            if (currentTask.getIsCompleted() == 1) {
                todaysPoints += currentTask.getPoint();
            } else {
                todaysPoints -= currentTask.getPoint();
            }
        }

        String askString = "Are you a better person today? ";
        String pointString;
        if (todaysPoints < 0) {
            pointString = "-" + todaysPoints;
        } else {
            pointString = Integer.toString(todaysPoints);
        }

        myBuilder.setContentText(askString + pointString + " points");

        Intent myMainIntent = new Intent(context, MainActivity.class);
        myMainIntent.setAction(Intent.ACTION_MAIN);
        myMainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myMainIntent, 0);
        myBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, myBuilder.build());



    }
}