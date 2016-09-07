package com.feihongren.betterperson;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fwr50 on 2016/9/6.
 */
public class CalendarHistoryAdapter extends ArrayAdapter<Task> {
    private Activity context;
    private int id;
    ArrayList<Task> taskList;
    public CalendarHistoryAdapter(Activity context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);
        this.context = context;
        this.id = resource;
        this.taskList = objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(id,null);
        }


        TextView calendarHistoryStatus = (TextView) convertView.findViewById(R.id.calendar_history_status);
        TextView calendarHistoryTitle = (TextView) convertView.findViewById(R.id.calendar_history_task_name);
        TextView calendarHistoryTotalTime = (TextView) convertView.findViewById(R.id.calendar_history_total_time);
        TextView calendarHistoryCompletedTime = (TextView) convertView.findViewById(R.id.calendar_history_completed_time);
        TextView calendarHistoryPoint = (TextView) convertView.findViewById(R.id.calendar_history_point);

        Task currentTask = taskList.get(position);
        if(currentTask.getIsCompleted()==1){
            calendarHistoryStatus.setBackgroundColor(Color.parseColor("#58D68D"));
        }
        else{
            calendarHistoryStatus.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        calendarHistoryTitle.setText(currentTask.getTitle());
        String totalTimeString = "Total:"+currentTask.getHourTotal() + ":" + currentTask.getMinuteTotal() + ":00";
        calendarHistoryTotalTime.setText(totalTimeString);
        String ReTimeString = "Remain:"+currentTask.getHourRemain() + ":" + currentTask.getMinuteRemain() + ":" + currentTask.getSecondRemain();
        calendarHistoryCompletedTime.setText(ReTimeString);
        calendarHistoryPoint.setText(Integer.toString(currentTask.getPoint()));

        return convertView;
    }

}
