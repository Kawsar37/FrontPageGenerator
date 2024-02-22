package com.kawser.cprf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button buttonLabReport = findViewById(R.id.button_lab_report);
        Button buttonAssignment = findViewById(R.id.button_assignment);
        Button buttonProjectProposal = findViewById(R.id.button_project_proposal);
        Button buttonThesis = findViewById(R.id.button_thesis_report);
        Button buttonEvaluation = findViewById(R.id.button_evaluation);

        Toast.makeText(this, "This app created by Kawsar CSE 14th💀", Toast.LENGTH_LONG).show();

        buttonLabReport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, main_activity.class);
                startActivity(intent);
            }
        });

        buttonAssignment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, AssignmentInfo.class);
                startActivity(intent);
            }
        });

        buttonProjectProposal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, ProjectProposalInfo.class);
                startActivity(intent);
            }
        });

        buttonThesis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, ThesisInfo.class);
                startActivity(intent);
            }
        });

//        buttonEvaluation.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        buttonEvaluation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, EvaluationDownload.class);
                startActivity(intent);
            }
        });

    }
}