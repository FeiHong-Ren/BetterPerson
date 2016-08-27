package com.feihongren.betterperson;
/*
Name: FeiHong Ren
Date: August 2016
*/

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //Adding variable for the button click event for drawer
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    //listview  variable for task
    ListView taskListView;
    ArrayAdapter<String> arrayAdapter;
    String[] taskName = {"task","test task"};

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

        //set up the main task list
        taskListView = (ListView) findViewById(R.id.main_task_list);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,taskName);
        taskListView.setAdapter(arrayAdapter);
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
