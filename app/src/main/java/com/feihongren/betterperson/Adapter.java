package com.feihongren.betterperson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(id,null);
        }
        System.out.println("the position is " + position);
        TextView taskName = (TextView) convertView.findViewById(R.id.task_name);
        final CheckBox taskCheckBox = (CheckBox) convertView.findViewById(R.id.task_checkbox);
        final Task task = taskList.get(position);

        //variable use to change the task background when click checkbox
        final TextView taskStatus = (TextView) convertView.findViewById(R.id.task_status);
        //Actionlistener when click checkbox
        taskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(taskCheckBox.isChecked()) {
                    taskStatus.setBackgroundColor(Color.parseColor("#58D68D"));
                }
                else{
                    taskStatus.setBackgroundColor(Color.parseColor("#F5F5F5"));
                }
            }
        });

        //actionlistener when click task
        taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        taskName.setText(task.getTitle());
        if(task.getIsCompleted() == 1) {
            taskCheckBox.setChecked(true);
        }
        else {
            taskCheckBox.setChecked(false);
        }


        return convertView;
    }


}
