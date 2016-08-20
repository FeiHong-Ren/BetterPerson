package com.feihongren.betterperson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class AddActiviy extends AppCompatActivity {

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
