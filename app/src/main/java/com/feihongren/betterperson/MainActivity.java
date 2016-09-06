package com.feihongren.betterperson;
/*
Name: FeiHong Ren
Date: August 2016
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    //Adding variable for the button click event for drawer
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Activity mainActivity = this;
    //listview  variable for task
    ListView taskListView;
    static ArrayList<Task> taskArray;
    static Adapter arrayAdapter;

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
                int itemId = item.getItemId();
                if (itemId == R.id.calendar){
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
        DBHandler dbHandler = new DBHandler(this);
        taskArray = dbHandler.getTaskList();//get the task in the database
        arrayAdapter = new Adapter(this, R.layout.custom_listview, taskArray);

        taskListView = (ListView) findViewById(R.id.main_task_list);
        taskListView.setAdapter(arrayAdapter);



    }

    public static void addTask(Task newTask){
        taskArray.add(newTask);
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


}
