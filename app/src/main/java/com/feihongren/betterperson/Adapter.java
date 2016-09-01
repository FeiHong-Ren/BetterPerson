package com.feihongren.betterperson;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fwr50 on 2016/8/31.
 */
public class Adapter extends ArrayAdapter<Task> {
    private Activity context;
    private int id;
    ArrayList<Task> taskList;


    public Adapter(Activity context, int resource, ArrayList<Task> objects){
        super(context, resource, objects);
        this.context = context;
        this.id = resource;
        this.taskList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(id,null);
        }
        TextView taskName = (TextView) convertView.findViewById(R.id.task_name);
        CheckBox taskCheckBox = (CheckBox) convertView.findViewById(R.id.task_checkbox);
        final Task task = taskList.get(position);

        taskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                task.setIsChecked(b);
            }
        });

        taskName.setText(task.getTitle());
        if(task.getIsChecked() == 1) {
            taskCheckBox.setChecked(true);
        }
        else {
            taskCheckBox.setChecked(true);
        }
        return convertView;
    }

}
