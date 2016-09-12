package com.feihongren.betterperson;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;
import static com.feihongren.betterperson.MainActivity.arrayAdapter;

import java.util.concurrent.TimeUnit;

/**
 * Created by fwr50 on 2016/9/3.
 */
public class CountdownClockActivity extends AppCompatActivity {
    private CountdownClockActivity currentActivity = this;
    private DBHandler dbHandler;
    private TextView titleTextview;
    private TextView countdownClockTextview;
    private ImageButton PlayPauseButton;
    private TextView PointTextview;
    private Task currentTask;
    private boolean isPaused;
    private CountdownClock countdownClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_clock_activity);
        //set up the action tool bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.countdown_clock_toolbar);
        setSupportActionBar(myToolbar);

        dbHandler = new DBHandler(this);

        titleTextview = (TextView) findViewById(R.id.countdown_clock_title);
        countdownClockTextview = (TextView) findViewById(R.id.countdown_clock_timer);
        PlayPauseButton = (ImageButton) findViewById(R.id.countdown_clock_play_pause_button);
        PointTextview = (TextView) findViewById(R.id.countdown_clock_point);

        Bundle extras = getIntent().getExtras();
        String taskName = extras.getString("EXTRA_TASK_Title");
        int taskPosition = extras.getInt("EXTRA_TASK_POSITION");
        currentTask = dbHandler.getTask(taskName);

        titleTextview.setText(currentTask.getTitle());//set the task title
        //set the point for the task
        int point = currentTask.getPoint();
        String pointString = point + " Points";
        PointTextview.setText(pointString);
        //set the clock for the task
        String hourMinuteSecondFormat = String.format("%02d:%02d:%02d", currentTask.getHourRemain(), currentTask.getMinuteRemain(), currentTask.getSecondRemain());
        countdownClockTextview.setText(hourMinuteSecondFormat);

        long totalMilliTime = TimeUnit.HOURS.toMillis(currentTask.getHourRemain()) +TimeUnit.MINUTES.toMillis(currentTask.getMinuteRemain())+TimeUnit.SECONDS.toMillis(currentTask.getSecondRemain());
        countdownClock = new CountdownClock(totalMilliTime, 1000,totalMilliTime);

        isPaused = true;

        PlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPaused == true) {
                    countdownClock.start();
                    PlayPauseButton.setImageResource(R.drawable.clock_pause_image);
                    isPaused = false;
                }
                else{
                    countdownClock.cancel();
                    long milliSecondLeft = countdownClock.getMilliSecondLeft();
                    countdownClock = new CountdownClock(milliSecondLeft, 1000,milliSecondLeft);
                    PlayPauseButton.setImageResource(R.drawable.clock_play_image);
                    isPaused = true;
                }
            }
        });
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
        if (id == R.id.countdown_clock_cancel_button) {
            countdownClock.cancel();
            dbHandler.close();
            currentActivity.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class CountdownClock extends CountDownTimer {
        private long milliSecondLeft;
        public CountdownClock(long millisInFuture, long countDownInterval, long milliSecondLeft) {
            super(millisInFuture, countDownInterval);
            this.milliSecondLeft = milliSecondLeft;
        }
        public long getMilliSecondLeft(){return milliSecondLeft;}

        @Override
        public void onTick(long l) {
            milliSecondLeft = l;
            long hourRemain = TimeUnit.MILLISECONDS.toHours(milliSecondLeft);
            long minuteRemain = TimeUnit.MILLISECONDS.toMinutes(milliSecondLeft) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSecondLeft));
            long secondRemain = TimeUnit.MILLISECONDS.toSeconds(milliSecondLeft) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSecondLeft));
            String hourMinuteSecondFormat = String.format("%02d:%02d:%02d", hourRemain,minuteRemain,secondRemain);
            countdownClockTextview.setText(hourMinuteSecondFormat);
            System.out.println(hourMinuteSecondFormat);
            dbHandler.updateTimeRemain(currentTask,hourRemain,minuteRemain,secondRemain);
        }

        @Override
        public void onFinish() {
            final MediaPlayer notifySound = MediaPlayer.create(currentActivity, R.raw.minion_ring_ring);
            countdownClockTextview.setText("00:00:00");
            dbHandler.updateTimeRemain(currentTask,0,0,0);
            PlayPauseButton.setImageResource(R.drawable.clock_play_image);
            isPaused = true;
            dbHandler.updateIsCompleted(currentTask,1);

            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);

            notifySound.start();
        }
    }

}
