package com.feihongren.betterperson;

/**
 * Created by fwr50 on 2016/8/31.
 */
public class Task {
    private String title = "";
    private float hour = 0;
    private float minute = 0;
    private int point = 0;
    private int mondayOn = 1;
    private int tuesdayOn = 1;
    private int wednesdayOn = 1;
    private int thursdayOn = 1;
    private int fridayOn = 1;
    private int saturdayOn = 1;
    private int sundayOn = 1;
    private String description = "";
    private int isChecked = 0;

    public Task(String title, float hour, float minute, int point, int mondayOn, int thursdayOn, int tuesdayOn, int wednesdayOn, int fridayOn, int sundayOn, int saturdayOn, String description, int isChecked) {
        this.title = title;
        this.hour = hour;
        this.minute = minute;
        this.point = point;
        this.mondayOn = mondayOn;
        this.thursdayOn = thursdayOn;
        this.tuesdayOn = tuesdayOn;
        this.wednesdayOn = wednesdayOn;
        this.fridayOn = fridayOn;
        this.sundayOn = sundayOn;
        this.saturdayOn = saturdayOn;
        this.description = description;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getHour() {
        return hour;
    }

    public void setHour(float hour) {
        this.hour = hour;
    }

    public float getMinute() {
        return minute;
    }

    public void setMinute(float minute) {
        this.minute = minute;
    }

    public int getMondayOn() {
        return mondayOn;
    }

    public void setMondayOn(int mondayOn) {
        this.mondayOn = mondayOn;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getTuesdayOn() {
        return tuesdayOn;
    }

    public void setTuesdayOn(int tuesdayOn) {
        this.tuesdayOn = tuesdayOn;
    }

    public int getWednesdayOn() {
        return wednesdayOn;
    }

    public void setWednesdayOn(int wednesdayOn) {
        this.wednesdayOn = wednesdayOn;
    }

    public int getThursdayOn() {
        return thursdayOn;
    }

    public void setThursdayOn(int thursdayOn) {
        this.thursdayOn = thursdayOn;
    }

    public int getFridayOn() {
        return fridayOn;
    }

    public void setFridayOn(int fridayOn) {
        this.fridayOn = fridayOn;
    }

    public int getSaturdayOn() {
        return saturdayOn;
    }

    public void setSaturdayOn(int saturdayOn) {
        this.saturdayOn = saturdayOn;
    }

    public int getSundayOn() {
        return sundayOn;
    }

    public void setSundayOn(int sundayOn) {
        this.sundayOn = sundayOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        if(isChecked){
            this.isChecked = 1;
        }
        else{
            this.isChecked = 0;
        }
    }
}
