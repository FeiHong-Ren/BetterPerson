package com.feihongren.betterperson;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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



        return convertView;
    }

}
