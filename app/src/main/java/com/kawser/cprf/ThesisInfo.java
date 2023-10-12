package com.kawser.cprf;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ThesisInfo extends AppCompatActivity {

    EditText file_name;
    EditText course_code;
    EditText course_title;
    EditText thesis_title;
    EditText team_member1_name;
    EditText team_member1_id;
    EditText team_member2_name;
    EditText team_member2_id;
    EditText team_member3_name;
    EditText team_member3_id;
    EditText team_member4_name;
    EditText team_member4_id;
    EditText team_batch;
    EditText team_session;

    Button submit;

    public static final int REQUEST_STORAGE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thesis_info);

        int permissionGranted = PackageManager.PERMISSION_GRANTED;
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


        course_code = (EditText) findViewById(R.id.th_edit_text_course_code);
        course_title  = (EditText) findViewById(R.id.th_edit_text_course_title);
        thesis_title = (EditText) findViewById(R.id.th_edit_text_project_title);
        team_member1_name = (EditText) findViewById(R.id.th_team_member1_name);
        team_member1_id = (EditText) findViewById(R.id.th_team_member1_Id);
        team_member2_name = (EditText) findViewById(R.id.th_team_member2_name);
        team_member2_id = (EditText) findViewById(R.id.th_team_member2_Id);
        team_member3_name = (EditText) findViewById(R.id.th_team_member3_name);
        team_member3_id = (EditText) findViewById(R.id.th_team_member3_Id);
        team_member4_name = (EditText) findViewById(R.id.th_team_member4_name);
        team_member4_id = (EditText) findViewById(R.id.th_team_member4_Id);
        team_batch = (EditText) findViewById(R.id.th_team_batch);
        team_session = (EditText) findViewById(R.id.th_team_session);
        file_name = (EditText) findViewById(R.id.th_edit_text_file_name);
        submit = (Button) findViewById(R.id.th_submit_button);




        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (file_name.getText().toString().length() == 0 ||
                        course_code.getText().toString().length() == 0 ||
                        course_title.getText().toString().length() == 0 ||
                        thesis_title.getText().toString().length() == 0 ||
                        team_member1_id.getText().toString().length() == 0 ||
                        team_member1_name.getText().toString().length() == 0 ||
                        team_batch.getText().toString().length() == 0 ||
                        team_session.getText().toString().length() == 0 ||
                        file_name.getText().toString().length() == 0){

                    Toast.makeText(ThesisInfo.this, "Some Info Missing!!!", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(ThesisInfo.this, ThesisSuccess.class);
                    intent.putExtra("course_code", course_code.getText().toString());
                    intent.putExtra("course_title", course_title.getText().toString());
                    intent.putExtra("thesis_title", thesis_title.getText().toString());
                    intent.putExtra("team_member1_name", team_member1_name.getText().toString());
                    intent.putExtra("team_member1_id", team_member1_id.getText().toString());
                    intent.putExtra("team_member2_name", team_member2_name.getText().toString());
                    intent.putExtra("team_member2_id", team_member2_id.getText().toString());
                    intent.putExtra("team_member3_name", team_member3_name.getText().toString());
                    intent.putExtra("team_member3_id", team_member3_id.getText().toString());
                    intent.putExtra("team_member4_name", team_member4_name.getText().toString());
                    intent.putExtra("team_member4_id", team_member4_id.getText().toString());
                    intent.putExtra("team_batch", team_batch.getText().toString());
                    intent.putExtra("team_session", team_session.getText().toString());
                    intent.putExtra("file_name", file_name.getText().toString());

                    startActivity(intent);
                }
            }
        });


    }
}