package com.kawser.cprf;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Calendar;

public class LabReportInfo extends AppCompatActivity {
    EditText file_name;
    EditText name;
    EditText id;
    EditText batch;
    EditText year;
    EditText semester;
    EditText session;
    EditText course_code;
    EditText course_title;
    EditText lab_no;
    EditText exp_date;
    EditText sub_date;
    EditText exp_name;
    EditText teacher1;
    EditText teacher1_pos;
    EditText teacher2;
    EditText teacher2_pos;
    EditText teacher3;
    EditText teacher3_pos;
    Button submit;

    DatabaseHelper dbHelper;



    public static final int REQUEST_STORAGE = 101;

    boolean thikAse(String str) {
        boolean isInteger = true;
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                isInteger = false;
                break;
            }
        }
        return isInteger;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_report_info);


        dbHelper = new DatabaseHelper(this);

        int permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionGranted == PackageManager.PERMISSION_GRANTED) {
            // Continue with your code
        } else {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        }


        // for experiment date
        exp_date = (EditText) findViewById(R.id.edit_text_ex_date);
        Calendar calendar = Calendar.getInstance();
        exp_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(LabReportInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        exp_date.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        // for submission date
        sub_date = (EditText) findViewById(R.id.edit_text_sub_date);
        Calendar scalendar = Calendar.getInstance();
        sub_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int year = scalendar.get(Calendar.YEAR);
                int month = scalendar.get(Calendar.MONTH);
                int day = scalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(LabReportInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        sub_date.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        file_name = (EditText) findViewById(R.id.edit_text_file_name);
        name = (EditText) findViewById(R.id.edit_text_name);
        id = (EditText) findViewById(R.id.edit_text_id);
        batch = (EditText) findViewById(R.id.edit_text_batch);
        year = (EditText) findViewById(R.id.edit_text_year);
        semester = (EditText) findViewById(R.id.edit_text_semester);
        session = (EditText) findViewById(R.id.edit_text_session);
        course_code = (EditText) findViewById(R.id.edit_text_course_code);
        course_title = (EditText) findViewById(R.id.edit_text_course_title);
        lab_no = (EditText) findViewById(R.id.edit_text_lab_no);
        exp_name = (EditText) findViewById(R.id.edit_text_exp_name);
        teacher1 = (EditText) findViewById(R.id.edit_text_submitted_teacher1);
        teacher1_pos = (EditText) findViewById(R.id.edit_text_pos_teacher1);
        teacher2 = (EditText) findViewById(R.id.edit_text_submitted_teacher2);
        teacher2_pos = (EditText) findViewById(R.id.edit_text_pos_teacher2);
        teacher3 = (EditText) findViewById(R.id.edit_text_submitted_teacher3);
        teacher3_pos = (EditText) findViewById(R.id.edit_text_pos_teacher3);
        submit = (Button) findViewById(R.id.submit_button_lab_report);
        

        // Get value from database
        String Name_d = getIntent().getStringExtra("name");
        String Id_d = getIntent().getStringExtra("id");
        String Batch_d = getIntent().getStringExtra("batch");
        String Year_d = getIntent().getStringExtra("year");
        String Semester_d = getIntent().getStringExtra("semester");
        String Session_d = getIntent().getStringExtra("session");
        String Course_code_d = getIntent().getStringExtra("course_code");
        String Course_title_d = getIntent().getStringExtra("course_title");
        String Lab_no_d = getIntent().getStringExtra("lab_no");
        String Exp_date_d = getIntent().getStringExtra("exp_date");
        String Sub_date_d = getIntent().getStringExtra("sub_date");
        String Exp_name_d = getIntent().getStringExtra("exp_name");
        String Teacher1_d = getIntent().getStringExtra("teacher1");
        String Teacher1_pos_d = getIntent().getStringExtra("teacher1_pos");
        String Teacher2_d = getIntent().getStringExtra("teacher2");
        String Teacher2_pos_d = getIntent().getStringExtra("teacher2_pos");
        String Teacher3_d = getIntent().getStringExtra("teacher3");
        String Teacher3_pos_d = getIntent().getStringExtra("teacher3_pos");
        String FileName_d = getIntent().getStringExtra("file_name");


        // Set all string to edit text
        file_name.setText(FileName_d);
        name.setText(Name_d);
        id.setText(Id_d);
        batch.setText(Batch_d);
        year.setText(Year_d);
        semester.setText(Semester_d);
        session.setText(Session_d);
        course_code.setText(Course_code_d);
        course_title.setText(Course_title_d);
        lab_no.setText(Lab_no_d);
        sub_date.setText(Sub_date_d);
        exp_date.setText(Exp_date_d);
        exp_name.setText(Exp_name_d);
        teacher1.setText(Teacher1_d);
        teacher1_pos.setText(Teacher1_pos_d);
        teacher2.setText(Teacher2_d);
        teacher2_pos.setText(Teacher2_pos_d);
        teacher3.setText(Teacher3_d);
        teacher3_pos.setText(Teacher3_pos_d);


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SQLiteDatabase DB = dbHelper.getWritableDatabase();
                Cursor cursor = DB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CARDS + " WHERE " + DatabaseHelper.COLUMN_FILE_NAME + " = ?", new String[]{file_name.getText().toString()});

                if (file_name.getText().toString().length() == 0 ||
                        name.getText().toString().length() == 0 ||
                        id.getText().toString().length() == 0 ||
                        batch.getText().toString().length() == 0 ||
                        year.getText().toString().length() == 0 ||
                        semester.getText().toString().length() == 0 ||
                        course_code.getText().toString().length() == 0 ||
                        course_title.getText().toString().length() == 0 ||
                        teacher1.getText().toString().length() == 0 ||
                        teacher1_pos.getText().toString().length() == 0) {

                    Toast.makeText(LabReportInfo.this, "*** Please fill-up all required field ***", Toast.LENGTH_LONG).show();
                } else if (!thikAse(batch.getText().toString()) || !thikAse(year.getText().toString()) || !thikAse(semester.getText().toString())) {
                    Toast.makeText(LabReportInfo.this, "Please Enter only Number for Year, Semester, Batch\n*** No need to add th/nd/rd ***", Toast.LENGTH_LONG).show();
                } else if (cursor.getCount() >= 1) {
                    Toast.makeText(LabReportInfo.this, "Notun PDF File Name deo vai :3", Toast.LENGTH_LONG).show();
                } else if (file_name.getText().toString().contains(".pdf")) {
                    Toast.makeText(LabReportInfo.this, "** Remove .pdf from pdf file name **", Toast.LENGTH_LONG).show();
                } else {
                    String FileName = file_name.getText().toString();
                    String Name = name.getText().toString();
                    String Id = id.getText().toString();
                    String Batch = batch.getText().toString();
                    String Year = year.getText().toString();
                    String Semester = semester.getText().toString();
                    String Session = session.getText().toString();
                    String Course_code = course_code.getText().toString();
                    String Course_title = course_title.getText().toString();
                    String Lab_no = lab_no.getText().toString();
                    String Exp_date = exp_date.getText().toString();
                    String Sub_date = sub_date.getText().toString();
                    String Exp_name = exp_name.getText().toString();
                    String Teacher1 = teacher1.getText().toString();
                    String Teacher1_pos = teacher1_pos.getText().toString();
                    String Teacher2 = teacher2.getText().toString();
                    String Teacher2_pos = teacher2_pos.getText().toString();
                    String Teacher3 = teacher3.getText().toString();
                    String Teacher3_pos = teacher3_pos.getText().toString();


                    // changes
                    Intent intent = new Intent(LabReportInfo.this, LabReportSuccessActivity.class);
                    intent.putExtra("name", Name);
                    intent.putExtra("id", Id);
                    intent.putExtra("batch", Batch);
                    intent.putExtra("year", Year);
                    intent.putExtra("semester", Semester);
                    intent.putExtra("session", Session);
                    intent.putExtra("course_code", Course_code);
                    intent.putExtra("course_title", Course_title);
                    intent.putExtra("lab_no", Lab_no);
                    intent.putExtra("exp_date", Exp_date);
                    intent.putExtra("sub_date", Sub_date);
                    intent.putExtra("exp_name", Exp_name);
                    intent.putExtra("teacher1", Teacher1);
                    intent.putExtra("teacher1_pos", Teacher1_pos);
                    intent.putExtra("teacher2", Teacher2);
                    intent.putExtra("teacher2_pos", Teacher2_pos);
                    intent.putExtra("teacher3", Teacher3);
                    intent.putExtra("teacher3_pos", Teacher3_pos);
                    intent.putExtra("file_name", FileName);

                    saveData(FileName, Name, Id, Batch, Year, Semester, Session, Course_code, Course_title, Lab_no, Exp_date, Sub_date, Exp_name, Teacher1, Teacher1_pos, Teacher2, Teacher2_pos, Teacher3, Teacher3_pos);

                    startActivity(intent);


                }
            }
        });



    }

    private void saveData(String FileName, String Name, String Id, String Batch, String Year, String Semester, String Session, String Course_code, String Course_title, String Lab_no, String Exp_date, String Sub_date, String Exp_name, String Teacher1, String Teacher1_pos, String Teacher2, String Teacher2_pos, String Teacher3, String Teacher3_pos) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FILE_NAME, FileName);
        values.put(DatabaseHelper.COLUMN_NAME, Name != null ? Name : "");
        values.put(DatabaseHelper.COLUMN_SID, Id != null ? Id : "");
        values.put(DatabaseHelper.COLUMN_BATCH, Batch != null ? Batch : "");
        values.put(DatabaseHelper.COLUMN_YEAR, Year != null ? Year : "");
        values.put(DatabaseHelper.COLUMN_SEMESTER, Semester != null ? Semester : "");
        values.put(DatabaseHelper.COLUMN_SESSION, Session != null ? Session : "");
        values.put(DatabaseHelper.COLUMN_COURSE_CODE, Course_code != null ? Course_code : "");
        values.put(DatabaseHelper.COLUMN_COURSE_TITLE, Course_title != null ? Course_title : "");
        values.put(DatabaseHelper.COLUMN_LAB_NO, Lab_no != null ? Lab_no : "");
        values.put(DatabaseHelper.COLUMN_EXP_DATE, Exp_date != null ? Exp_date : "");
        values.put(DatabaseHelper.COLUMN_SUB_DATE, Sub_date != null ? Sub_date : "");
        values.put(DatabaseHelper.COLUMN_EXP_NAME, Exp_name != null ? Exp_name : "");
        values.put(DatabaseHelper.COLUMN_TEACHER1, Teacher1 != null ? Teacher1 : "");
        values.put(DatabaseHelper.COLUMN_TEACHER1_POS, Teacher1_pos != null ? Teacher1_pos : "");
        values.put(DatabaseHelper.COLUMN_TEACHER2, Teacher2 != null ? Teacher2 : "");
        values.put(DatabaseHelper.COLUMN_TEACHER2_POS, Teacher2_pos != null ? Teacher2_pos : "");
        values.put(DatabaseHelper.COLUMN_TEACHER3, Teacher3 != null ? Teacher3 : "");
        values.put(DatabaseHelper.COLUMN_TEACHER3_POS, Teacher3_pos != null ? Teacher3_pos : "");


        long newRowId = db.insert(DatabaseHelper.TABLE_CARDS, null, values);

//        if (newRowId != -1) {
//            // Successfully saved to the database
//            Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_LONG).show();
////            Log.d("Database", "Data saved successfully! Row ID: " + newRowId);
//        } else {
//            // Failed to save to the database
//            Toast.makeText(this, "Failed to save data!", Toast.LENGTH_LONG).show();
////            Log.e("Database", "Failed to save data!");
//        }
//        db.close();
    }


}