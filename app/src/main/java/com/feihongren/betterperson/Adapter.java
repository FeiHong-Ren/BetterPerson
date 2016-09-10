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
public class Adapter extends ArrayAdapter<Task>{
    private Activity context;
    private int id;
    private ArrayList<Task> taskList;


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
        ImageButton editImageButton = (ImageButton) convertView.findViewById(R.id.edit_image_button);
        final CheckBox taskCheckBox = (CheckBox) convertView.findViewById(R.id.task_checkbox);
        Task task = taskList.get(position);

        final DBHandler dbHandler = new DBHandler(context);

        //variable use to change the task background when click checkbox
        final TextView taskStatus = (TextView) convertView.findViewById(R.id.task_status);


        taskName.setText(task.getTitle());

        if(task.getIsCompleted() == 1) {
            taskCheckBox.setChecked(true);
            taskStatus.setBackgroundColor(Color.parseColor("#58D68D"));
        }
        else {
            taskCheckBox.setChecked(false);
            taskStatus.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        taskCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = taskList.get(position);
                if(taskCheckBox.isChecked()) {
                    dbHandler.updateIsCompleted(task,1);
                    taskStatus.setBackgroundColor(Color.parseColor("#58D68D"));
                }
                else{
                    dbHandler.updateIsCompleted(task,0);
                    taskStatus.setBackgroundColor(Color.parseColor("#F5F5F5"));
                }
            }
        });

        taskCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = taskList.get(position);
                if(taskCheckBox.isChecked()) {
                    dbHandler.updateIsCompleted(task,1);
                    taskStatus.setBackgroundColor(Color.parseColor("#58D68D"));
                }
                else{
                    dbHandler.updateIsCompleted(task,0);
                    taskStatus.setBackgroundColor(Color.parseColor("#F5F5F5"));
                }
            }
        });


        editImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Task task = taskList.get(position);
                Intent editActivity = new Intent(context, EditActivity.class);
                editActivity.putExtra("EXTRA_EDIT_TITLE",task.getTitle());
                editActivity.putExtra("EXTRA_EDIT_INDEX",position);
                context.startActivityForResult(editActivity,1);
            }
        });

        //actionlistener when click task
        taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = taskList.get(position);
                Intent startCountDownClockActivity = new Intent(context, CountdownClockActivity.class);
                startCountDownClockActivity.putExtra("EXTRA_TASK_Title",task.getTitle());
                context.startActivity(startCountDownClockActivity);


            }
        });

        return convertView;
    }


}
