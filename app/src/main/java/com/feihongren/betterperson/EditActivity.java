package com.feihongren.betterperson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


import static com.feihongren.betterperson.MainActivity.removeTask;

/**
 * Created by fwr50 on 2016/9/7.
 */
public class EditActivity extends AppCompatActivity {
    private Activity currentActivity = this;
    private Task currentTask;
    private DBHandler dbHandler;

    private int hour = 0;
    private int minute = 0;
    private int point = 0;
    private int mondayOn = 1;
    private int tuesdayOn = 1;
    private int wednesdayOn = 1;
    private int thursdayOn = 1;
    private int fridayOn = 1;
    private int saturdayOn = 1;
    private int sundayOn = 1;
    private String description = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);
        //set up the action tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.edit_toolbar);
        setSupportActionBar(myToolbar);

        dbHandler = new DBHandler(this);
        Bundle extras = getIntent().getExtras();
        String taskName = extras.getString("EXTRA_EDIT_TITLE");
        final int position = extras.getInt("EXTRA_EDIT_INDEX");
        currentTask = dbHandler.getTask(taskName);

        TextView editTitle = (TextView) findViewById(R.id.edit_title);
        final EditText hourEdit =  (EditText) findViewById(R.id.edit_time_hour);
        final EditText minuteEdit = (EditText) findViewById(R.id.edit_time_minute);
        final EditText pointEdit = (EditText) findViewById(R.id.edit_point);
        final ToggleButton mondayToggle = (ToggleButton) findViewById(R.id.edit_monday_button);
        final ToggleButton tuesdayToggle = (ToggleButton) findViewById(R.id.edit_tuesday_button);
        final ToggleButton wednesdayToggle = (ToggleButton) findViewById(R.id.edit_wednesday_button);
        final ToggleButton thursdayToggle = (ToggleButton) findViewById(R.id.edit_thursday_button);
        final ToggleButton fridayToggle = (ToggleButton) findViewById(R.id.edit_friday_button);
        final ToggleButton saturdayToggle = (ToggleButton) findViewById(R.id.edit_saturday_button);
        final ToggleButton sundayToggle = (ToggleButton) findViewById(R.id.edit_sunday_button);
        final EditText descriptionEdit = (EditText) findViewById(R.id.edit_description);
        Button completeButton = (Button) findViewById(R.id.edit_complete_button);


        //set the task information in the edit activity UI
        editTitle.setText(taskName);
        hourEdit.setText(Integer.toString(currentTask.getHourTotal()));
        minuteEdit.setText(Integer.toString(currentTask.getMinuteTotal()));
        pointEdit.setText(Integer.toString(currentTask.getPoint()));
        if(currentTask.getMondayOn() == 1){
            mondayToggle.setChecked(true);
        }
        else{
            mondayToggle.setChecked(false);
        }
        if(currentTask.getTuesdayOn() == 1){
            tuesdayToggle.setChecked(true);
        }
        else{
            tuesdayToggle.setChecked(false);
        }
        if(currentTask.getWednesdayOn() == 1){
            wednesdayToggle.setChecked(true);
        }
        else{
            wednesdayToggle.setChecked(false);
        }
        if(currentTask.getThursdayOn() == 1){
            thursdayToggle.setChecked(true);
        }
        else{
            thursdayToggle.setChecked(false);
        }
        if(currentTask.getFridayOn() == 1){
            fridayToggle.setChecked(true);
        }
        else{
            fridayToggle.setChecked(false);
        }
        if(currentTask.getSaturdayOn() == 1){
            saturdayToggle.setChecked(true);
        }
        else{
            saturdayToggle.setChecked(false);
        }
        if(currentTask.getSundayOn() == 1){
            sundayToggle.setChecked(true);
        }
        else{
            sundayToggle.setChecked(false);
        }
        descriptionEdit.setText(currentTask.getDescription());

        //update the value when click complete button
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView editTitleOnclick = (TextView) findViewById(R.id.edit_title);
                EditText hourEditOnclick =  (EditText) findViewById(R.id.edit_time_hour);
                EditText minuteEditOnclick = (EditText) findViewById(R.id.edit_time_minute);
                EditText pointEditOnclick = (EditText) findViewById(R.id.edit_point);
                ToggleButton mondayToggleOnclick = (ToggleButton) findViewById(R.id.edit_monday_button);
                ToggleButton tuesdayToggleOnclick = (ToggleButton) findViewById(R.id.edit_tuesday_button);
                ToggleButton wednesdayToggleOnclick = (ToggleButton) findViewById(R.id.edit_wednesday_button);
                ToggleButton thursdayToggleOnclick = (ToggleButton) findViewById(R.id.edit_thursday_button);
                ToggleButton fridayToggleOnclick = (ToggleButton) findViewById(R.id.edit_friday_button);
                ToggleButton saturdayToggleOnclick = (ToggleButton) findViewById(R.id.edit_saturday_button);
                ToggleButton sundayToggleOnclick = (ToggleButton) findViewById(R.id.edit_sunday_button);
                EditText descriptionEditOnclick = (EditText) findViewById(R.id.edit_description);


                hour = Integer.parseInt(hourEditOnclick.getText().toString());
                minute = Integer.parseInt(minuteEditOnclick.getText().toString());
                point = Integer.parseInt(pointEditOnclick.getText().toString());
                if(mondayToggleOnclick.isChecked()){
                    mondayOn = 1;
                }
                else{
                    mondayOn = 0;
                }
                if(tuesdayToggleOnclick.isChecked()){
                    tuesdayOn = 1;
                }
                else{
                    tuesdayOn = 0;
                }
                if(wednesdayToggleOnclick.isChecked()){
                    wednesdayOn = 1;
                }
                else{
                    wednesdayOn = 0;
                }
                if(thursdayToggleOnclick.isChecked()){
                    thursdayOn = 1;
                }
                else{
                    thursdayOn = 0;
                }
                if(fridayToggleOnclick.isChecked()){
                    fridayOn = 1;
                }
                else{
                    fridayOn = 0;
                }
                if(saturdayToggleOnclick.isChecked()){
                    saturdayOn = 1;
                }
                else{
                    saturdayOn = 0;
                }
                if(sundayToggleOnclick.isChecked()){
                    sundayOn = 1;
                }
                else{
                    sundayOn = 0;
                }
                description = (String) descriptionEditOnclick.getText().toString();

                //if time is not modify
                if(hour == currentTask.getHourTotal() && minute == currentTask.getMinuteTotal()){
                    dbHandler.updateTask(currentTask.getID(),hour,minute,currentTask.getHourRemain(),currentTask.getMinuteRemain(),currentTask.getSecondRemain(), point,mondayOn,tuesdayOn,wednesdayOn,thursdayOn,fridayOn,saturdayOn,sundayOn,description,currentTask.getIsCompleted());
                }
                else{
                    dbHandler.updateTask(currentTask.getID(),hour,minute,hour,minute,0, point,mondayOn,tuesdayOn,wednesdayOn,thursdayOn,fridayOn,saturdayOn,sundayOn,description,0);
                }

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                
                currentActivity.finish();

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_activity_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_cancel_button) {
            currentActivity.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
