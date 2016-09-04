package com.feihongren.betterperson;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by fwr50 on 2016/9/3.
 */
public class CountdownClockActivity extends AppCompatActivity {
    private CountdownClockActivity currentActivity = this;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_clock_activity);
        //set up the action tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.countdown_clock_toolbar);
        setSupportActionBar(myToolbar);

        dbHandler = new DBHandler(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.countdown_clock_activity_bar, menu);
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

    public class CountdownClock extends CountDownTimer {
        public CountdownClock(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            long milliSecondLeft = l;
            String hourMinuteSecondFormat = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(milliSecondLeft),
                    TimeUnit.MICROSECONDS.toMinutes(milliSecondLeft) - TimeUnit.HOURS.toMinutes(TimeUnit.MICROSECONDS.toHours(milliSecondLeft)),
                    TimeUnit.MICROSECONDS.toSeconds(milliSecondLeft) - TimeUnit.MINUTES.toSeconds(TimeUnit.MICROSECONDS.toMinutes(milliSecondLeft)));
            TextView countdownTextview = (TextView) findViewById(R.id.countdown_clock_timer);
            countdownTextview.setText(hourMinuteSecondFormat);
        }

        @Override
        public void onFinish() {

        }
    }

}
