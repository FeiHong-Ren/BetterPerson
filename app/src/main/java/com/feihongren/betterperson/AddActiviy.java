package com.feihongren.betterperson;

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



public class AddActiviy extends AppCompatActivity {
    private String title = "";
    private int hour = 0;
    private int minute = 0;
    private int point = 0;
    private boolean mondayOn = true;
    private boolean tuesdayOn = true;
    private boolean wednesdayOn = true;
    private boolean thursdayOn = true;
    private boolean fridayOn = true;
    private boolean saturdayOn = true;
    private boolean sundayOn = true;
    private String description = "";


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
                EditText descriptionEdit = (EditText) findViewById(R.id.add_description);

                String titleString = (String) titleEdit.getText().toString();
                int hourInteger = Integer.parseInt(hourEdit.getText().toString());
                int minInteger = Integer.parseInt(minuteEdit.getText().toString());
                int pointInteger = Integer.parseInt(pointEdit.getText().toString());
                String descriptionString = (String) descriptionEdit.getText().toString();

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
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
