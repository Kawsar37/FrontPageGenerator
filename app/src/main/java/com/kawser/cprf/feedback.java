package com.kawser.cprf;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class feedback extends AppCompatActivity {
    private DatabaseReference feedbackRef;
    private EditText username, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        feedbackRef = FirebaseDatabase.getInstance().getReference("feedback");

        username = findViewById(R.id.editTextUsername);
        feedback = findViewById(R.id.editTextFeedback);

        Button submitButton = findViewById(R.id.btnSendFeedback);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    submitFeedback();
                } else {
//                    Intent intent = new Intent(android.provider.Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
//                    startActivity(intent);
                    Toast.makeText(feedback.this,"Please Turn On The Internet", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void submitFeedback() {
        String user = username.getText().toString().trim();
        String userFeedback = feedback.getText().toString().trim();

        if (!user.isEmpty() && !userFeedback.isEmpty()) {
            String key = feedbackRef.push().getKey();
            feedbackRef.child(key).child("name").setValue(user);
            feedbackRef.child(key).child("feedback").setValue(userFeedback);
            // Add any other fields or information you want to store

            // Optionally, you can clear the EditText fields after submission
            username.setText("");
            feedback.setText("");
            submitToGoogleForm(user, userFeedback);
            Toast.makeText(feedback.this, "Thank You For Sharing Your FeedBack !", Toast.LENGTH_LONG).show();
        } else {
            // Handle empty fields or show an error message
            if (user.isEmpty()) {
                username.setError("Please Provide Your Name");
            }
            if (userFeedback.isEmpty()) {
                feedback.setError("Please Provide Your FeedBack");
            }
        }
    }

    private void submitToGoogleForm(String user, String userFeedback) {
//        String formUrl = "https://docs.google.com/forms/d/e/1FAIpQLSdwvmjjvqB3DNuUJg_hj5jcV-t4KKVooiT6LO93G2ngQD5a0w/viewform?usp=sf_link";  // Replace with your Google Form URL
        String formUrl = "https://forms.gle/zqqs9DhpPeZe3f846";
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("entry.604362570", user).add("entry.80401019", userFeedback).build();

        Request request = new Request.Builder().url(formUrl).post(formBody).build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
                e.printStackTrace();
//                Toast.makeText(feedback.this, "Failed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Toast.makeText(feedback.this, "Successful", Toast.LENGTH_LONG).show();
                // Handle successful submission
                // Note: This runs on a background thread, so update UI components on the main thread if needed
            }
        });
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}