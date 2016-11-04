package com.feihongren.betterperson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fwr50 on 2016/9/6.
 */
public class CalendarHistoryActivity extends AppCompatActivity {
    Activity currentActivity = this;
    DBHandler dbHandler;
    ArrayList<Task> taskArrayList;
    int month;
    int day;
    int year;
    CalendarHistoryAdapter calendarHistoryArrayAdapter;
    ListView calendarHistoryTaskListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_history_activity);

        //set up the action tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.calendar_history_toolbar);
        setSupportActionBar(myToolbar);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            month = extras.getInt("MONTH_EXTRA");
            day = extras.getInt("DAY_EXTRA");
            year = extras.getInt("YEAR_EXTRA");
        }


        dbHandler = new DBHandler(this);
        taskArrayList = dbHandler.getSelectedDayHistory(month, day, year);

        TextView calendarHistoryTitle = (TextView) findViewById(R.id.calendar_history_title);
        calendarHistoryTitle.setText(month + "/" + day + "/" + year);


        calendarHistoryArrayAdapter = new CalendarHistoryAdapter(this, R.layout.calendar_history_custom_listview, taskArrayList);

        calendarHistoryTaskListView = (ListView) findViewById(R.id.calendar_history_listview);
        calendarHistoryTaskListView.setAdapter(calendarHistoryArrayAdapter);


        //Init selected day's point
        TextView selectedDaysPoint = (TextView) findViewById(R.id.history_total_point);

        int selectedDaysPoints = 0;
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task currentTask = taskArrayList.get(i);
            if (currentTask.getIsCompleted() == 1) {
                selectedDaysPoints += currentTask.getPoint();
            }
            /*
            else {
                selectedDaysPoints -= currentTask.getPoint();
            }
            */
        }
        String textString = "Point Total: ";
        String selectedDaysPointString = Integer.toString(selectedDaysPoints);
        selectedDaysPoint.setText(textString + selectedDaysPointString);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calendar_history_activity_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.calendar_history_cancel_button) {
            dbHandler.close();
            currentActivity.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
