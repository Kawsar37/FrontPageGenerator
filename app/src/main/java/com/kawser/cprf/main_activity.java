package com.kawser.cprf;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class main_activity extends AppCompatActivity {

    Button add;
    LinearLayout layout;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);

        layout = findViewById(R.id.container);

        dbHelper = new DatabaseHelper(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dialog.show();
                Intent intent = new Intent(main_activity.this, LabReportInfo.class);
                startActivity(intent);

            }
        });

        loadSavedDataFromDatabase();
    }



    public void addCard(String name) {
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView nameView = view.findViewById(R.id.name);
        ImageButton delete = view.findViewById(R.id.delete);
        ImageButton edit = view.findViewById(R.id.edit);

        nameView.setText(name);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);

                // Delete the card from the database
                deleteCard(name);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase DB = dbHelper.getWritableDatabase();
                Cursor cursor = DB.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CARDS + " WHERE " + DatabaseHelper.COLUMN_FILE_NAME + " = ?", new String[]{name});

                // Check if the cursor has any rows
                if (cursor != null && cursor.moveToFirst()) {
                    // Retrieve data from the first row
                    if (cursor.moveToFirst()) {
                        // Assuming the column indices, adjust them based on your actual schema
                        int nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
                        int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SID);
                        int batchIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_BATCH);
                        int yearIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_YEAR);
                        int semesterIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SEMESTER);
                        int sessionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SESSION);
                        int courseCodeIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_COURSE_CODE);
                        int courseTitleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_COURSE_TITLE);
                        int labNoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LAB_NO);
                        int expDateIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EXP_DATE);
                        int subDateIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SUB_DATE);
                        int expNameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EXP_NAME);
                        int teacher1Index = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER1);
                        int teacher1PosIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER1_POS);
                        int teacher2Index = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER2);
                        int teacher2PosIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER2_POS);
                        int teacher3Index = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER3);
                        int teacher3PosIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEACHER3_POS);

                        // Set values to your variables
                        String Name = cursor.getString(nameIndex);
                        String Id = cursor.getString(idIndex);
                        String Batch = cursor.getString(batchIndex);
                        String Year = cursor.getString(yearIndex);
                        String Semester = cursor.getString(semesterIndex);
                        String Session = cursor.getString(sessionIndex);
                        String Course_code = cursor.getString(courseCodeIndex);
                        String Course_title = cursor.getString(courseTitleIndex);
                        String Lab_no = cursor.getString(labNoIndex);
                        String Exp_date = cursor.getString(expDateIndex);
                        String Sub_date = cursor.getString(subDateIndex);
                        String Exp_name = cursor.getString(expNameIndex);
                        String Teacher1 = cursor.getString(teacher1Index);
                        String Teacher1_pos = cursor.getString(teacher1PosIndex);
                        String Teacher2 = cursor.getString(teacher2Index);
                        String Teacher2_pos = cursor.getString(teacher2PosIndex);
                        String Teacher3 = cursor.getString(teacher3Index);
                        String Teacher3_pos = cursor.getString(teacher3PosIndex);
                        String FileName = cursor.getString(1);

                        // Send intent
                        Intent intent = new Intent(main_activity.this, LabReportInfo.class);
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
                        startActivity(intent);

                        // Close the cursor when done
                        cursor.close();
                    }


                    // Don't forget to close the cursor when done
                    cursor.close();
                } else {
                    Toast.makeText(main_activity.this, "No data found for the specified name", Toast.LENGTH_LONG).show();
                }
            }
        });



        layout.addView(view);
    }


    public void deleteCard(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_CARDS, DatabaseHelper.COLUMN_FILE_NAME + " = ?", new String[]{name});
        db.close();
    }


    // Inside main_activity.java

    public void loadSavedDataFromDatabase() {
        Cursor cursor = dbHelper.GetData();

        while (cursor.moveToNext()) {
            String fileName = cursor.getString(1);
            if (fileName != null && !fileName.isEmpty()) {
                addCard(fileName);
            }
        }

        cursor.close();
    }

}
