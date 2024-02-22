package com.kawser.cprf;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SavedReport";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    public static final String TABLE_CARDS = "cards";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FILE_NAME = "FileName";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SID = "Id";
    public static final String COLUMN_BATCH = "Batch";
    public static final String COLUMN_YEAR = "Year";
    public static final String COLUMN_SEMESTER = "Semester";
    public static final String COLUMN_SESSION = "Session";
    public static final String COLUMN_COURSE_CODE = "Course_code";
    public static final String COLUMN_COURSE_TITLE = "Course_title";
    public static final String COLUMN_LAB_NO = "Lab_no";
    public static final String COLUMN_EXP_DATE = "Exp_date";
    public static final String COLUMN_SUB_DATE = "Sub_date";
    public static final String COLUMN_EXP_NAME = "Exp_name";
    public static final String COLUMN_TEACHER1 = "Teacher1";
    public static final String COLUMN_TEACHER1_POS = "Teacher1_pos";
    public static final String COLUMN_TEACHER2 = "Teacher2";
    public static final String COLUMN_TEACHER2_POS = "Teacher2_pos";
    public static final String COLUMN_TEACHER3 = "Teacher3";
    public static final String COLUMN_TEACHER3_POS = "Teacher3_pos";

    // Create table query
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CARDS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FILE_NAME + " TEXT," + COLUMN_NAME + " TEXT, " +
                    COLUMN_SID + " TEXT," +
                    COLUMN_BATCH + " TEXT, " +
                    COLUMN_YEAR + " TEXT, " +
                    COLUMN_SEMESTER + " TEXT, " +
                    COLUMN_SESSION + " TEXT, " +
                    COLUMN_COURSE_CODE + " TEXT, " +
                    COLUMN_COURSE_TITLE + " TEXT, " +
                    COLUMN_LAB_NO + " TEXT, " +
                    COLUMN_EXP_DATE + " TEXT, " +
                    COLUMN_SUB_DATE + " TEXT, " +
                    COLUMN_EXP_NAME + " TEXT, " +
                    COLUMN_TEACHER1 + " TEXT, " +
                    COLUMN_TEACHER1_POS + " TEXT, " +
                    COLUMN_TEACHER2 + " TEXT, " +
                    COLUMN_TEACHER2_POS + " TEXT, " +
                    COLUMN_TEACHER3 + " TEXT, " +
                    COLUMN_TEACHER3_POS + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle upgrades if needed
    }

    public Cursor GetData ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM " + TABLE_CARDS, null);
        return cursor;
    }

}
