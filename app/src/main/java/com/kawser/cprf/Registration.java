package com.kawser.cprf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Registration extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    String[] batch = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
    String[] dept = {"EEE", "CSE", "CE", "ICE", "ME", "BBA", "English", "Law and Justice"};
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteDept;
    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterDept;

    // Captcha
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    String SiteKey = "6LeQajMoAAAAAM0abjxyn5zhd9URMysZ_7EdcqNM";

    FirebaseAuth fauth;
    DatabaseReference mdatabase;
    ProgressDialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Captcha Authentication
        checkBox = findViewById(R.id.CheckBox_Id);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Registration.this)
                .build();
        googleApiClient.connect();
        checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if ((status != null) && (status.isSuccess())) {
                                        Toast.makeText(Registration.this, "Verification Successful", Toast.LENGTH_SHORT).show();
                                        checkBox.setTextColor(Color.BLUE);
                                    }
                                }
                            });
                } else {
                    checkBox.setTextColor(Color.RED);
                    Toast.makeText(Registration.this, "Verification Not Complete", Toast.LENGTH_SHORT).show();
                }
            }
        });



        EditText name = findViewById(R.id.edit_text_register_full_name);
        EditText email = findViewById(R.id.edit_text_register_email);
        EditText stdid = findViewById(R.id.edit_text_register_id);
        EditText pass = findViewById(R.id.edit_text_password);
        EditText repass = findViewById(R.id.edit_text_re_password);
        TextInputLayout department = findViewById(R.id.dept_box);
        TextInputLayout stdbatch = findViewById(R.id.batch);
        Button signin= findViewById(R.id.submit_button);
        fauth = FirebaseAuth.getInstance();







        autoCompleteTextView = findViewById(R.id.auto_complete_textview);
        autoCompleteDept = findViewById(R.id.auto_complete_dept);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, batch);
        adapterDept = new ArrayAdapter<String>(this, R.layout.list_item, dept);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteDept.setAdapter(adapterDept);

        autoCompleteDept.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Registration.this, "Dept. "+item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Registration.this, "Batch "+item, Toast.LENGTH_SHORT).show();
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    String Name = name.getText().toString().trim();
                    String Email = email.getText().toString().trim();
                    String ID = stdid.getText().toString().trim();
                    String Dept = department.toString().trim();
                    String Batch = stdbatch.toString().trim();
                    String Pass = pass.getText().toString().trim();
                    String Repass = repass.getText().toString().trim();


                    if(TextUtils.isEmpty(Name)){
                        name.setError("Enter your name");
                        return;
                    }
                    if(TextUtils.isEmpty(Email)){
                        email.setError("Enter your email");
                        return;
                    }
                    if(TextUtils.isEmpty(ID)){
                        stdid.setError("Enter your id");
                        return;
                    }
                    if(TextUtils.isEmpty(Dept)){
                        department.setError("Select your dept");
                        return;
                    }
                    if(TextUtils.isEmpty(Batch)){
                        stdbatch.setError("Select your batch");
                        return;
                    }
                    if(TextUtils.isEmpty(Pass)){
                        pass.setError("Enter password");
                        return;
                    }
                    if(Pass.length()<6){
                        pass.setError("Enter password at least 6 characters");
                        return;
                    }
                    if(!Pass.equals(Repass)) {
                        repass.setError("Password does not match");
                        return;
                    }

                    fauth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        public void onComplete(Task<AuthResult> task){
                            if(task.isSuccessful()){
                                Toast.makeText(Registration.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            }
                            else{
                                Toast.makeText(Registration.this, "Error something is wrong!\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Registration.this, "Captcha verification failed!\n", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}