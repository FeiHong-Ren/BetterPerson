package com.feihongren.betterperson;
/*
Name: FeiHong Ren
Date: August 2016
*/

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity{
    //Adding variable for the button click event for drawer
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Activity mainActivity = this;
    //listview  variable for task
    static ListView taskListView;
    static ArrayList<Task> todaysTaskArray;
    static ArrayList<Task> allTaskArray;
    static Adapter arrayAdapter;
    static DBHandler mainActivityDBHandler;

    private boolean isInTodaysTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up the action tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.task_toolbar);
        setSupportActionBar(myToolbar);
        //set up the button click event for drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,myToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        //set up the actionlistener when click the navigation drawer item
        NavigationView drawerNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                TextView taskTitle = (TextView) findViewById(R.id.task_title);
                int itemId = item.getItemId();
                if (itemId == R.id.todays_task){
                    todaysTaskArray.clear();
                    todaysTaskArray = mainActivityDBHandler.getTodayTaskList();
                    arrayAdapter.clear();
                    arrayAdapter = new Adapter(mainActivity, R.layout.custom_listview, todaysTaskArray);
                    taskListView = (ListView) findViewById(R.id.main_task_list);
                    taskListView.setAdapter(arrayAdapter);
                    taskTitle.setText("Today's Task");
                    drawerLayout.closeDrawer(Gravity.LEFT);//hide the navigation drawer
                    isInTodaysTask = true;
                }
                else if (itemId == R.id.all_task){
                    allTaskArray.clear();
                    allTaskArray = mainActivityDBHandler.getAllTaskList();
                    arrayAdapter.clear();
                    arrayAdapter = new Adapter(mainActivity, R.layout.custom_listview, allTaskArray);
                    taskListView = (ListView) findViewById(R.id.main_task_list);
                    taskListView.setAdapter(arrayAdapter);
                    taskTitle.setText("All Task");


                    drawerLayout.closeDrawer(Gravity.LEFT);//hide the navigation drawer
                    isInTodaysTask = false;
                }
                else if (itemId == R.id.calendar){
                    Intent startCalendarActivity = new Intent(mainActivity, CalendarActivity.class);
                    startActivity(startCalendarActivity);

                    drawerLayout.closeDrawer(Gravity.LEFT);//hide the navigation drawer
                }
                else if(itemId == R.id.statistic){
                    Intent startAddActivity = new Intent(mainActivity, AddActiviy.class);
                    startActivity(startAddActivity);

                    drawerLayout.closeDrawer(Gravity.LEFT);//hide the navigation drawer
                }

                return false;
            }

        });


        //set up the main task list
        mainActivityDBHandler = new DBHandler(this);
        todaysTaskArray = mainActivityDBHandler.getTodayTaskList();//get the task in the database
        allTaskArray = mainActivityDBHandler.getAllTaskList();
        arrayAdapter = new Adapter(this, R.layout.custom_listview, todaysTaskArray);


        taskListView = (ListView) findViewById(R.id.main_task_list);
        taskListView.setAdapter(arrayAdapter);


        //add today's task into database during end of the day
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 23);
        calendar1.set(Calendar.MINUTE, 59);
        calendar1.set(Calendar.SECOND, 55);
        Timer timer1 = new Timer();
        timer1.schedule(new dayCompleted(), calendar1.getTime());


        //change the task list's task when it is another day
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        Timer timer2 = new Timer();
        timer2.schedule(new newDay(), calendar2.getTime());


        //change the task list's task when it is another day
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.HOUR_OF_DAY, 23);
        calendar3.set(Calendar.MINUTE, 59);
        calendar3.set(Calendar.SECOND, 0);
        Timer timer3 = new Timer();
        timer3.schedule(new endOfTheDayNotification(), calendar3.getTime());



        isInTodaysTask = true;

    }

    private class dayCompleted extends TimerTask
    {
        public void run()
        {
            mainActivityDBHandler.addTodayTask(todaysTaskArray);
            System.out.println("Today is completed!!!");

        }
    }

    private class newDay extends TimerTask
    {
        public void run()
        {
            //reset all task
            mainActivityDBHandler.resetAllTask(mainActivityDBHandler.getAllTaskList());


            todaysTaskArray.clear();
            todaysTaskArray = mainActivityDBHandler.getTodayTaskList();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    arrayAdapter.clear();
                    arrayAdapter = new Adapter(mainActivity, R.layout.custom_listview, todaysTaskArray);
                    taskListView = (ListView) findViewById(R.id.main_task_list);
                    taskListView.setAdapter(arrayAdapter);
                }
            });

            System.out.println("New Task List!!!!");

        }
    }

    private class endOfTheDayNotification extends TimerTask
    {
        public void run()
        {
            NotificationCompat.Builder myBuilder = new NotificationCompat.Builder(mainActivity);
            myBuilder.setSmallIcon(R.mipmap.ic_launcher);
            myBuilder.setContentTitle("Better Person");

            ArrayList<Task> todaysTaskList = mainActivityDBHandler.getTodayTaskList();
            int todaysPoints = 0;
            for(int i=0;i<todaysTaskList.size();i++){
                Task currentTask = todaysTaskList.get(i);
                if(currentTask.getIsCompleted() == 1){
                    todaysPoints += currentTask.getPoint();
                }
                else{
                    todaysPoints -= currentTask.getPoint();
                }
            }

            String askString = "Are you a better person today? ";
            String pointString;
            if(todaysPoints<0){
                pointString = "-"+todaysPoints;
            }
            else{
                pointString = Integer.toString(todaysPoints);
            }

            myBuilder.setContentText(askString+pointString+ " points");

            Intent myMainIntent = new Intent(mainActivity, MainActivity.class);
            myMainIntent.setAction(Intent.ACTION_MAIN);
            myMainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            PendingIntent pendingIntent = PendingIntent.getActivity(mainActivity, 0, myMainIntent, 0);
            myBuilder.setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,myBuilder.build());

        }
    }


    public static void addTask(Task newTask){
        todaysTaskArray.add(newTask);
        arrayAdapter.notifyDataSetChanged();
    }

    public static void removeTask(int position){
        todaysTaskArray.remove(position);
        arrayAdapter.notifyDataSetChanged();

    }

    //drawer syn post create
    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_activity_bar, menu);
        return true;
    }

    //override the method when main custom toolbar item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_button) {
            //When click button, start new activity
            Intent startAddActivity = new Intent(this, AddActiviy.class);
            startActivity(startAddActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //execute this code when edit activity finish
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                if (isInTodaysTask){
                    todaysTaskArray.clear();
                    todaysTaskArray = mainActivityDBHandler.getTodayTaskList();
                    arrayAdapter.clear();
                    arrayAdapter = new Adapter(mainActivity, R.layout.custom_listview, todaysTaskArray);
                }
                else{
                    allTaskArray.clear();
                    allTaskArray = mainActivityDBHandler.getAllTaskList();
                    arrayAdapter.clear();
                    arrayAdapter = new Adapter(mainActivity, R.layout.custom_listview, allTaskArray);
                }

                taskListView = (ListView) findViewById(R.id.main_task_list);
                taskListView.setAdapter(arrayAdapter);
            }
        }
        else if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                if (isInTodaysTask){
                    todaysTaskArray.clear();
                    todaysTaskArray = mainActivityDBHandler.getTodayTaskList();
                    arrayAdapter.clear();
                    arrayAdapter = new Adapter(mainActivity, R.layout.custom_listview, todaysTaskArray);
                }
                else{
                    allTaskArray.clear();
                    allTaskArray = mainActivityDBHandler.getAllTaskList();
                    arrayAdapter.clear();
                    arrayAdapter = new Adapter(mainActivity, R.layout.custom_listview, allTaskArray);
                }

                taskListView = (ListView) findViewById(R.id.main_task_list);
                taskListView.setAdapter(arrayAdapter);
            }
        }
    }




}
