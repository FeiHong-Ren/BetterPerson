package com.feihongren.betterperson;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by fwr50 on 2016/11/3.
 */
public class SettingActivity extends AppCompatActivity{
    Activity currentActivity = this;
    DBHandler dbHandler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_activity);
        //set up the action tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(myToolbar);
        dbHandler = new DBHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_activity_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.setting_cancel_button) {
            dbHandler.close();
            currentActivity.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
