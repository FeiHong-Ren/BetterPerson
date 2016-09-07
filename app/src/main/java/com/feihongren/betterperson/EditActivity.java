package com.feihongren.betterperson;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by fwr50 on 2016/9/7.
 */
public class EditActivity extends AppCompatActivity {
    private Activity currentActivity = this;
    private Task currentTask;
    private DBHandler dbHandler;
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
        currentTask = dbHandler.getTask(taskName);

        TextView EditTitle = (TextView) findViewById(R.id.edit_title);


        EditTitle.setText(taskName);
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
