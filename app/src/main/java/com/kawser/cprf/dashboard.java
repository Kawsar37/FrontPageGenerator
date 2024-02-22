package com.kawser.cprf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView labreport = findViewById(R.id.cardLabReport);
        CardView evaluation = findViewById(R.id.cardEvaluation);
        CardView assignmen = findViewById(R.id.cardAssignment);
        CardView project = findViewById(R.id.cardProjectProposal);
        CardView thesis = findViewById(R.id.cardThesisReport);
        CardView feedback = findViewById(R.id.feedback);

        labreport.setOnClickListener(v -> {
            Intent intent = new Intent(dashboard.this, main_activity.class);
            startActivity(intent);
        });

        evaluation.setOnClickListener(v -> {
            Intent intent = new Intent(dashboard.this, EvaluationDownload.class);
            startActivity(intent);
        });

        assignmen.setOnClickListener(v -> {
            Intent intent = new Intent(dashboard.this, AssignmentInfo.class);
            startActivity(intent);
        });

        project.setOnClickListener(v -> {
            Intent intent = new Intent(dashboard.this, ProjectProposalInfo.class);
            startActivity(intent);
        });

        thesis.setOnClickListener(v -> {
            Intent intent = new Intent(dashboard.this, ThesisInfo.class);
            startActivity(intent);
        });

        feedback.setOnClickListener(v -> {
            Intent intent = new Intent(dashboard.this, feedback.class);
            startActivity(intent);
        });

    }


    public void onBackPressed() {
        showExitConfirmationDialog();
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Html.fromHtml("<b>Exit</b>"));
        builder.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, just dismiss the dialog
                    }
                })
                .show();
    }

}