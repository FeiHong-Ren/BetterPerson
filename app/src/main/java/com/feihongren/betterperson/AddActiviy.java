package com.feihongren.betterperson;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;


public class AddActiviy extends AppCompatActivity {
    private String title = "";
    private float hour = 0;
    private float minute = 0;
    private int point = 0;
    private int mondayOn = 1;
    private int tuesdayOn = 1;
    private int wednesdayOn = 1;
    private int thursdayOn = 1;
    private int fridayOn = 1;
    private int saturdayOn = 1;
    private int sundayOn = 1;
    private String description = "";
    private Context context = this;
    private AddActiviy currentActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activiy);
        //set up the action tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.add_toolbar);
        setSupportActionBar(myToolbar);

        //set the limit for add activity hour range
        EditText hourLimit = (EditText) findViewById(R.id.add_time_hour);
        hourLimit.setFilters(new InputFilter[]{ new InputFilterMinMax(0, 23)});

        //set the limit for add activity minute range
        EditText minuteLimit = (EditText) findViewById(R.id.add_time_minute);
        minuteLimit.setFilters(new InputFilter[]{ new InputFilterMinMax(0, 59)});

        //set the button action listener for complete button
        Button complete_button = (Button) findViewById(R.id.add_complete_button);
        complete_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText titleEdit = (EditText) findViewById(R.id.add_title);
                EditText hourEdit = (EditText) findViewById(R.id.add_time_hour);
                EditText minuteEdit = (EditText) findViewById(R.id.add_time_minute);
                EditText pointEdit = (EditText) findViewById(R.id.add_point);
                ToggleButton mondayToggle = (ToggleButton) findViewById(R.id.add_monday_button);
                ToggleButton tuesdayToggle = (ToggleButton) findViewById(R.id.add_tuesday_button);
                ToggleButton wednesdayToggle = (ToggleButton) findViewById(R.id.add_wednesday_button);
                ToggleButton thursdayToggle = (ToggleButton) findViewById(R.id.add_thursday_button);
                ToggleButton fridayToggle = (ToggleButton) findViewById(R.id.add_friday_button);
                ToggleButton saturdayToggle = (ToggleButton) findViewById(R.id.add_saturday_button);
                ToggleButton sundayToggle = (ToggleButton) findViewById(R.id.add_sunday_button);
                EditText descriptionEdit = (EditText) findViewById(R.id.add_description);

                DBHandler dbHandler = new DBHandler(context);

                String title = (String) titleEdit.getText().toString();
                float hour = Float.valueOf(hourEdit.getText().toString());
                float minute = Float.valueOf(minuteEdit.getText().toString());
                int point = Integer.parseInt(pointEdit.getText().toString());
                if(mondayToggle.isChecked()){
                    mondayOn = 1;
                }
                else{
                    mondayOn = 0;
                }
                if(tuesdayToggle.isChecked()){
                    tuesdayOn = 1;
                }
                else{
                    tuesdayOn = 0;
                }
                if(wednesdayToggle.isChecked()){
                    wednesdayOn = 1;
                }
                else{
                    wednesdayOn = 0;
                }
                if(thursdayToggle.isChecked()){
                    thursdayOn = 1;
                }
                else{
                    thursdayOn = 0;
                }
                if(fridayToggle.isChecked()){
                    fridayOn = 1;
                }
                else{
                    fridayOn = 0;
                }
                if(saturdayToggle.isChecked()){
                    saturdayOn = 1;
                }
                else{
                    saturdayOn = 0;
                }
                if(sundayToggle.isChecked()){
                    sundayOn = 1;
                }
                else{
                    sundayOn = 0;
                }
                String description = (String) descriptionEdit.getText().toString();

                dbHandler.addTask(title, hour, minute, point, mondayOn,tuesdayOn, wednesdayOn, thursdayOn, fridayOn, saturdayOn, sundayOn, description);
                currentActivity.finish();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_activity_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.cancel_button) {
            currentActivity.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
