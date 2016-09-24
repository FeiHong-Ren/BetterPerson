package com.feihongren.betterperson;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by fwr50 on 2016/9/23.
 */
public class StatisticActivity extends AppCompatActivity {
    private Activity currentActivity = this;
    private DBHandler dbHandler;
    private LineGraphSeries<DataPoint> series;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private ArrayAdapter<String> yearAdpater;
    private ArrayAdapter<String> monthAdpater;
    private ArrayList<String> yearList;
    private ArrayList<String> monthList;
    private GraphView graphView;
    private ArrayList<Integer> monthPointArray;
    private int selectedYear;
    private int selectedMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.statistic_toolbar);
        setSupportActionBar(myToolbar);

        dbHandler = new DBHandler(this);

        Calendar c = Calendar.getInstance();
        selectedMonth = c.get(Calendar.MONTH)+ 1;
        selectedYear = c.get(Calendar.YEAR);
        //Calculate the how many days in the month
        Calendar mycal = new GregorianCalendar(selectedYear, selectedMonth-1, 1);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        yearSpinner = (Spinner) findViewById(R.id.statistic_year_spinner);
        monthSpinner = (Spinner) findViewById(R.id.statistic_month_spinner);
        yearList = new ArrayList<>();
        monthList = new ArrayList<>();
        for (int i = 2016; i <= selectedYear; i++) {
            yearList.add(Integer.toString(i));
        }

        monthList.add("Jan");
        monthList.add("Feb");
        monthList.add("Mar");
        monthList.add("Apr");
        monthList.add("May");
        monthList.add("Jun");
        monthList.add("Jul");
        monthList.add("Aug");
        monthList.add("Sep");
        monthList.add("Oct");
        monthList.add("Nov");
        monthList.add("Dec");

        yearAdpater = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,yearList);
        monthAdpater = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,monthList);
        yearSpinner.setAdapter(yearAdpater);
        monthSpinner.setAdapter(monthAdpater);
        yearSpinner.setSelection(selectedYear-2016);
        monthSpinner.setSelection(selectedMonth-1);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMonth = i+1;
                monthPointArray.clear();
                monthPointArray=dbHandler.getSelectedMonthPoint(i+1,selectedYear);
                graphView.removeAllSeries();
                LineGraphSeries<DataPoint> newSeries= new LineGraphSeries<>();
                Calendar myCalendar = new GregorianCalendar(selectedYear, selectedMonth-1, 1);
                int daysInMonth = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                for(int j=1;j<=daysInMonth;j++){
                    newSeries.appendData(new DataPoint(j,monthPointArray.get(j-1)),true,31);
                }
                graphView.addSeries(newSeries);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedYear = 2016+i;
                monthPointArray.clear();
                monthPointArray=dbHandler.getSelectedMonthPoint(selectedMonth,2016+i);
                graphView.removeAllSeries();
                LineGraphSeries<DataPoint> newSeries= new LineGraphSeries<>();
                Calendar myCalendar = new GregorianCalendar(selectedYear, selectedMonth-1, 1);
                int daysInMonth = myCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                for(int j=1;j<=daysInMonth;j++){
                    newSeries.appendData(new DataPoint(j,monthPointArray.get(j-1)),true,31);
                }
                graphView.addSeries(newSeries);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Init the data for current month
        graphView = (GraphView) findViewById(R.id.statistic_graph_view);
        monthPointArray = dbHandler.getSelectedMonthPoint(selectedMonth,selectedYear);
        series = new LineGraphSeries<>();
        for(int i=1;i<=daysInMonth;i++){
            series.appendData(new DataPoint(i,monthPointArray.get(i-1)),true,31);
        }
        graphView.addSeries(series);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(31);
        graphView.getViewport().setXAxisBoundsManual(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.statistic_activity_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.statistic_cancel_button) {
            dbHandler.close();
            currentActivity.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
