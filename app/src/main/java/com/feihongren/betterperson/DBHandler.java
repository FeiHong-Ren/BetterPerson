package com.feihongren.betterperson;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by fwr50 on 2016/8/24.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "better_person_db";
    private static final String TASK_TABLE_NAME = "task_info";
    private static final String TASK_COLUMN_TITLE = "title";
    private static final String TASK_COLUMN_HOUR = "hour";
    private static final String TASK_COLUMN_MINUTE = "minute";
    private static final String TASK_COLUMN_POINT = "point";
    private static final String TASK_TABLE_MONDAY = "monday";
    private static final String TASK_COLUMN_TUESDAY = "tuesday";
    private static final String TASK_COLUMN_WEDNESDAY = "wednesday";
    private static final String TASK_COLUMN_THURSDAY = "thursday";
    private static final String TASK_COLUMN_FRIDAY = "friday";
    private static final String TASK_COLUMN_SATURDAY = "saturday";
    private static final String TASK_COLUMN_SUNDAY = "sunday";
    private static final String TASK_COLUMN_DESCRIPTION = "description";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE_NAME + "(" + TASK_COLUMN_TITLE + "TEXT,"+ TASK_COLUMN_HOUR + "REAL," +
                TASK_COLUMN_MINUTE + "REAL,"+ TASK_COLUMN_POINT + "INTEGER," + TASK_TABLE_MONDAY + "INTEGER,"+ TASK_COLUMN_TUESDAY + "INTEGER," +
                TASK_COLUMN_WEDNESDAY + "INTEGER,"+ TASK_COLUMN_THURSDAY + "INTEGER," + TASK_COLUMN_FRIDAY + "INTEGER,"+
                TASK_COLUMN_SATURDAY + "INTEGER,"+ TASK_COLUMN_SUNDAY + "INTEGER," + TASK_COLUMN_DESCRIPTION + "TEXT" +")";
        db.execSQL(CREATE_TASK_TABLE);
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE_NAME);
        onCreate(db);
    }

    public void addTask(String title, float hour, float minute, int point, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday, String description){
        SQLiteDatabase SQLDB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_COLUMN_TITLE,title);
        contentValues.put(TASK_COLUMN_HOUR,hour);
        contentValues.put(TASK_COLUMN_MINUTE,minute);
        contentValues.put(TASK_COLUMN_POINT,point);
        contentValues.put(TASK_TABLE_MONDAY,monday);
        contentValues.put(TASK_COLUMN_TUESDAY,tuesday);
        contentValues.put(TASK_COLUMN_WEDNESDAY,wednesday);
        contentValues.put(TASK_COLUMN_THURSDAY,thursday);
        contentValues.put(TASK_COLUMN_FRIDAY,friday);
        contentValues.put(TASK_COLUMN_SATURDAY,saturday);
        contentValues.put(TASK_COLUMN_SUNDAY,sunday);
        contentValues.put(TASK_COLUMN_DESCRIPTION,description);
        SQLDB.insert(DATABASE_NAME, null, contentValues);
        Log.d("Database operations", "One row inserted");
        SQLDB.close();

    }
    

}