package com.kawser.cprf;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EvaluationDownload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_download);

        Button saveButton = findViewById(R.id.ev_success_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UnspecifiedRegisterReceiverFlag")
            @Override
            public void onClick(View v) {
                String url = "https://firebasestorage.googleapis.com/v0/b/cover-page-and-report-format.appspot.com/o/Lab%20report%20evaluation%20page.pdf?alt=media&token=91edd51d-140c-480c-aa05-b3d5f2e734a1";
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(url);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false)
                        .setTitle("Evaluation Page.pdf")
                        .setDescription("Downloading...")
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Evaluation Page.pdf");
                long downloadId = downloadManager.enqueue(request);

                // Register a broadcast receiver to listen for download status changes
                IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
                registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                        if (downloadId == downloadId) {
                            // The download is complete
                            Toast.makeText(context, "Evaluation Page Download complete!", Toast.LENGTH_SHORT).show();

                            // Unregister the broadcast receiver
                            context.unregisterReceiver(this);
                        }
                    }
                }, filter);
            }
        });


    }
    public void Evaluation_buttonOpenFile(View view){
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
        }
        intent.setType("*/*");
        this.startActivity(intent);
    }

}