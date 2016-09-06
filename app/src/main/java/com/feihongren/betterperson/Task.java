package com.feihongren.betterperson;

/**
 * Created by fwr50 on 2016/8/31.
 */
public class Task {
    private int id = -1;
    private String title = "";
    private int hourTotal = 0;
    private int minuteTotal = 0;
    private int hourRemain = 0;
    private int minuteRemain = 0;
    private int secondRemain = 0;
    private int point = 0;
    private int mondayOn = 1;
    private int tuesdayOn = 1;
    private int wednesdayOn = 1;
    private int thursdayOn = 1;
    private int fridayOn = 1;
    private int saturdayOn = 1;
    private int sundayOn = 1;
    private String description = "";
    private int isCompleted = 0;

    public Task(int id, String title, int hourTotal, int minuteTotal, int hourRemain, int minuteRemain, int secondRemain, int point, int mondayOn, int tuesdayOn, int wednesdayOn, int thursdayOn, int fridayOn, int saturdayOn, int sundayOn, String description, int isCompleted) {
        this.id = id;
        this.title = title;
        this.hourTotal = hourTotal;
        this.minuteTotal = minuteTotal;
        this.hourRemain = hourRemain;
        this.minuteRemain = minuteRemain;
        this.secondRemain = secondRemain;
        this.point = point;
        this.mondayOn = mondayOn;
        this.thursdayOn = thursdayOn;
        this.tuesdayOn = tuesdayOn;
        this.wednesdayOn = wednesdayOn;
        this.fridayOn = fridayOn;
        this.sundayOn = sundayOn;
        this.saturdayOn = saturdayOn;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public int getID() { return id;}

    public void setID(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHourTotal() {
        return hourTotal;
    }

    public void setHourTotal(int hourTotal) {
        this.hourTotal = hourTotal;
    }

    public int getMinuteTotal() {
        return minuteTotal;
    }

    public void setMinuteTotal(int minuteTotal) {
        this.minuteTotal = minuteTotal;
    }

    public int getMinuteRemain() {
        return minuteRemain;
    }

    public void setMinuteRemain(int minuteRemain) {
        this.minuteRemain = minuteRemain;
    }

    public int getHourRemain() {
        return hourRemain;
    }

    public void setHourRemain(int hourRemain) {
        this.hourRemain = hourRemain;
    }

    public int getSecondRemain() {
        return secondRemain;
    }

    public void setSecondRemain(int secondRemain) {
        this.secondRemain = secondRemain;
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

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        if(isCompleted){
            this.isCompleted = 1;
        }
        else{
            this.isCompleted = 0;
        }
    }
}
