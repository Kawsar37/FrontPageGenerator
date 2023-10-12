package com.kawser.cprf;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

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

        buttonLabReport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, LabReportInfo.class);
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
                String url = "https://drive.google.com/u/0/uc?id=14CV5aRRPq2md7RFuSGYP8LqmTHUr6TCN&export=download";
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDescription("Downloading file...");
                request.setTitle("Lab Report Evaluation");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Lab_Report_Evaluation.pdf");

                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
            }
        });


    }
}