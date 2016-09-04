package com.feihongren.betterperson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by fwr50 on 2016/8/24.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "better_person.db";
    private static final String TASK_TABLE_NAME = "task_info";
    private static final String TASK_COLUMN_ID = "id";
    private static final String TASK_COLUMN_TITLE = "title";
    private static final String TASK_COLUMN_HOUR_TOTAL = "hour_total";
    private static final String TASK_COLUMN_MINUTE_TOTAL = "minute_total";
    private static final String TASK_COLUMN_HOUR_REMAIN = "hour_remain";
    private static final String TASK_COLUMN_MINUTE_REMAIN = "minute_remain";
    private static final String TASK_COLUMN_SECOND_REMAIN = "second_remain";
    private static final String TASK_COLUMN_POINT = "point";
    private static final String TASK_COLUMN_MONDAY = "monday";
    private static final String TASK_COLUMN_TUESDAY = "tuesday";
    private static final String TASK_COLUMN_WEDNESDAY = "wednesday";
    private static final String TASK_COLUMN_THURSDAY = "thursday";
    private static final String TASK_COLUMN_FRIDAY = "friday";
    private static final String TASK_COLUMN_SATURDAY = "saturday";
    private static final String TASK_COLUMN_SUNDAY = "sunday";
    private static final String TASK_COLUMN_DESCRIPTION = "description";
    private static final String TASK_COLUMN_IS_COMPLETED = "is_completed";

    private static final String COUNT_TABLE_NAME = "count_info";
    private static final String COUNT_COLUMN_id = "id";
    private static final String COUNT_COLUMN_COUNT = "count";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TASK_TABLE = "CREATE TABLE " +
                TASK_TABLE_NAME + "(" +
                TASK_COLUMN_ID + " INTEGER PRIMARY KEY,"+
                TASK_COLUMN_TITLE + " TEXT UNIQUE,"+
                TASK_COLUMN_HOUR_TOTAL + " REAL," +
                TASK_COLUMN_MINUTE_TOTAL + " REAL,"+
                TASK_COLUMN_HOUR_REMAIN + " REAL," +
                TASK_COLUMN_MINUTE_REMAIN + " REAL," +
                TASK_COLUMN_SECOND_REMAIN + " REAL," +
                TASK_COLUMN_POINT + " INTEGER," +
                TASK_COLUMN_MONDAY + " INTEGER,"+
                TASK_COLUMN_TUESDAY + " INTEGER," +
                TASK_COLUMN_WEDNESDAY + " INTEGER,"+
                TASK_COLUMN_THURSDAY + " INTEGER," +
                TASK_COLUMN_FRIDAY + " INTEGER,"+
                TASK_COLUMN_SATURDAY + " INTEGER,"+
                TASK_COLUMN_SUNDAY + " INTEGER," +
                TASK_COLUMN_DESCRIPTION + " TEXT," +
                TASK_COLUMN_IS_COMPLETED+ " INTEGER" +");";

        String CREATE_COUNT_TABLE = "CREATE TABLE " +
                COUNT_TABLE_NAME + "(" +
                COUNT_COLUMN_id + " INTEGER PRIMARY KEY,"+
                COUNT_COLUMN_COUNT + " INTEGER" + ");";

        db.execSQL(CREATE_TASK_TABLE);
        db.execSQL(CREATE_COUNT_TABLE);
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COUNT_TABLE_NAME);
        onCreate(db);
    }

    public void addTask(int id, String title, float hour, float minute, int point, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday, String description, int isCompleted){
        SQLiteDatabase SQLDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_COLUMN_ID,id);
        contentValues.put(TASK_COLUMN_TITLE,title);
        contentValues.put(TASK_COLUMN_HOUR_TOTAL,hour);
        contentValues.put(TASK_COLUMN_MINUTE_TOTAL,minute);
        contentValues.put(TASK_COLUMN_HOUR_REMAIN,hour);
        contentValues.put(TASK_COLUMN_MINUTE_REMAIN,minute);
        contentValues.put(TASK_COLUMN_SECOND_REMAIN,0);
        contentValues.put(TASK_COLUMN_POINT,point);
        contentValues.put(TASK_COLUMN_MONDAY,monday);
        contentValues.put(TASK_COLUMN_TUESDAY,tuesday);
        contentValues.put(TASK_COLUMN_WEDNESDAY,wednesday);
        contentValues.put(TASK_COLUMN_THURSDAY,thursday);
        contentValues.put(TASK_COLUMN_FRIDAY,friday);
        contentValues.put(TASK_COLUMN_SATURDAY,saturday);
        contentValues.put(TASK_COLUMN_SUNDAY,sunday);
        contentValues.put(TASK_COLUMN_DESCRIPTION,description);
        contentValues.put(TASK_COLUMN_IS_COMPLETED,isCompleted);
        SQLDB.insert(TASK_TABLE_NAME, null, contentValues);

        Log.d("Database operations", "One row inserted");
        SQLDB.close();
    }

    public ArrayList<Task> getTaskList(){
        SQLiteDatabase SQLDB = this.getReadableDatabase();
        //Check if the task table is empty
        Cursor checkCursor = SQLDB.rawQuery("SELECT count(*) FROM "+ TASK_TABLE_NAME, null);
        if(checkCursor.getCount()==0){
            return new ArrayList<Task>();
        }
        else{
            Cursor cursor = SQLDB.query(TASK_TABLE_NAME,null,null,null,null,null,COUNT_COLUMN_id + " ASC");
            ArrayList<Task> taskList = new ArrayList<Task>();
            if(cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndex(TASK_COLUMN_TITLE));
                    Float hourTotal = cursor.getFloat(cursor.getColumnIndex(TASK_COLUMN_HOUR_TOTAL));
                    Float minuteTotal = cursor.getFloat(cursor.getColumnIndex(TASK_COLUMN_MINUTE_TOTAL));
                    Float hourRemain = cursor.getFloat(cursor.getColumnIndex(TASK_COLUMN_HOUR_REMAIN));
                    Float minuteRemain = cursor.getFloat(cursor.getColumnIndex(TASK_COLUMN_MINUTE_REMAIN));
                    Float secondRemain = cursor.getFloat(cursor.getColumnIndex(TASK_COLUMN_SECOND_REMAIN));
                    int point = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_POINT));
                    int monday = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_MONDAY));
                    int tuesday = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_TUESDAY));
                    int wednesday = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_WEDNESDAY));
                    int thursday = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_THURSDAY));
                    int friday = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_FRIDAY));
                    int saturday = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_SATURDAY));
                    int sunday = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_SUNDAY));
                    String description = cursor.getString(cursor.getColumnIndex(TASK_COLUMN_DESCRIPTION));
                    int isCompleted = cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_IS_COMPLETED));
                    taskList.add(new Task(id,title,hourTotal,minuteTotal,hourTotal,minuteRemain,secondRemain,point,monday,tuesday,wednesday,thursday,friday,saturday,sunday,description,isCompleted));

                }while(cursor.moveToNext());

            }
            return taskList;
        }

    }

    public void addCount(){
        SQLiteDatabase SQLDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNT_COLUMN_id,0);
        contentValues.put(COUNT_COLUMN_COUNT,0);
        SQLDB.insert(COUNT_TABLE_NAME,null,contentValues);
        SQLDB.close();
    }
    public Cursor getCursorCount(){
        SQLiteDatabase SQLDB = this.getReadableDatabase();
        Cursor checkCursor = SQLDB.rawQuery("SELECT count(*) FROM "+ COUNT_TABLE_NAME, null);

        if(checkCursor.getCount()==0){
            addCount();
        }

        Cursor cursor = SQLDB.query(COUNT_TABLE_NAME,new String[]{COUNT_COLUMN_id, COUNT_COLUMN_COUNT},null,null,null,null,null);
        return cursor;
    }

    public int getCount(){
        Cursor cursor = getCursorCount();
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() == 0) {
            addCount();
            return 0;
        }
        else {
            return cursor.getInt(1);
        }
    }

    public void updateCount(int count){
        SQLiteDatabase SQLDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNT_COLUMN_COUNT, count);
        SQLDB.update(COUNT_TABLE_NAME,contentValues, COUNT_COLUMN_id +"=0", null);

    }

    public void updateIsCompleted(Task task,int isCompleted){
        SQLiteDatabase SQLDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_COLUMN_IS_COMPLETED, isCompleted);
        SQLDB.update(TASK_TABLE_NAME,contentValues, TASK_COLUMN_ID + "="+ task.getID(),null );

    }
    

}
