package com.kawser.cprf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText email = findViewById(R.id.login_email);

        EditText pass = findViewById(R.id.login_pass);
        Button login = findViewById(R.id.login_btn);
        fauth = FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Pass = pass.getText().toString().trim();


                if(TextUtils.isEmpty(Email)){
                    email.setError("Enter your email");
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


                fauth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    public void onComplete(Task<AuthResult> task){
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Homepage.class));
                        }
                        else{
                            Toast.makeText(Login.this, "Error\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });





    }



}