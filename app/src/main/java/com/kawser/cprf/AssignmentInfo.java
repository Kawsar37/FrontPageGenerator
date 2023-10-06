package com.kawser.cprf;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class AssignmentInfo extends AppCompatActivity {


    EditText file_name;
    EditText name;
    EditText id;
    EditText batch;
    EditText year;
    EditText semester;
    EditText session;
    EditText course_code;
    EditText course_title;
    EditText teacher1;
    EditText teacher1_pos;
    EditText teacher2;
    EditText teacher2_pos;
    EditText teacher3;
    EditText teacher3_pos;
    Button submit;


    public static final int REQUEST_STORAGE = 101;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_info);

        int permissionGranted = PackageManager.PERMISSION_GRANTED;
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        file_name = (EditText) findViewById(R.id.assignment_edit_text_file_name);
        name = (EditText) findViewById(R.id.assignment_edit_text_name);
        id = (EditText) findViewById(R.id.assignment_edit_text_id);
        batch = (EditText) findViewById(R.id.assignment_edit_text_batch);
        year = (EditText) findViewById(R.id.assignment_edit_text_year);
        semester = (EditText) findViewById(R.id.assignment_edit_text_semester);
        session = (EditText) findViewById(R.id.assignment_edit_text_session);
        course_code = (EditText) findViewById(R.id.assignment_edit_text_course_code);
        course_title = (EditText) findViewById(R.id.assignment_edit_text_course_title);
        teacher1 = (EditText) findViewById(R.id.assignment_edit_text_submitted_teacher1);
        teacher1_pos = (EditText) findViewById(R.id.assignment_edit_text_pos_teacher1);
        teacher2 = (EditText) findViewById(R.id.assignment_edit_text_submitted_teacher2);
        teacher2_pos = (EditText) findViewById(R.id.assignment_edit_text_pos_teacher2);
        teacher3 = (EditText) findViewById(R.id.assignment_edit_text_submitted_teacher3);
        teacher3_pos = (EditText) findViewById(R.id.assignment_edit_text_pos_teacher3);
        submit = (Button) findViewById(R.id.assignment_submit_button_lab_report);



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (file_name.getText().toString().length() == 0 ||
                        name.getText().toString().length() == 0 ||
                        id.getText().toString().length() == 0 ||
                        batch.getText().toString().length() == 0 ||
                        year.getText().toString().length() == 0 ||
                        semester.getText().toString().length() == 0 ||
                        session.getText().toString().length() == 0 ||
                        course_code.getText().toString().length() == 0 ||
                        course_title.getText().toString().length() == 0 ||
                        teacher1.getText().toString().length() == 0 ||
                        teacher1_pos.getText().toString().length() == 0){

                    Toast.makeText(AssignmentInfo.this, "Some Info Missing!!!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(AssignmentInfo.this, AssignmentSuccessActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("id", id.getText().toString());
                    intent.putExtra("batch", batch.getText().toString());
                    intent.putExtra("year", year.getText().toString());
                    intent.putExtra("semester", semester.getText().toString());
                    intent.putExtra("course_code", course_code.getText().toString());
                    intent.putExtra("course_title", course_title.getText().toString());
                    intent.putExtra("teacher1", teacher1.getText().toString());
                    intent.putExtra("teacher1_pos", teacher1_pos.getText().toString());
                    intent.putExtra("teacher2", teacher2.getText().toString());
                    intent.putExtra("teacher2_pos", teacher2_pos.getText().toString());
                    intent.putExtra("teacher3", teacher3.getText().toString());
                    intent.putExtra("teacher3_pos", teacher3_pos.getText().toString());
                    intent.putExtra("file_name", file_name.getText().toString());

                    startActivity(intent);
                }
            }
        });
    }
}