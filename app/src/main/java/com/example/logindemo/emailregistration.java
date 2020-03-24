package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class emailregistration extends AppCompatActivity {

    private EditText email,password,name,phone;
    private Button regis;
    private FirebaseAuth firebaseAuth;
    String email1, name1, password1, phone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailregistration);
        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                                startActivity(new Intent(emailregistration.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(emailregistration.this,"Registration Not Successful",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean validate() {
            Boolean result;
            result = false;

            email1 = email.getText().toString();
            password1 = password.getText().toString();
            name1 = name.getText().toString();
            phone1 = phone.getText().toString();
            if (name1.isEmpty() || password1.isEmpty() || phone1.isEmpty() || email1.isEmpty()  ) {
                Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_LONG).show();
            } else {
                result = true;
            }
            return result;
        }
        private void setupUIViews() {
            email = (EditText) findViewById(R.id.etemail1);
            password = (EditText) findViewById(R.id.etpassword);
            name = (EditText) findViewById(R.id.etname);
            phone = (EditText) findViewById(R.id.etphone);
            regis = (Button) findViewById(R.id.reg);
        }
        private void sendEmailVerification(){
            final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if(firebaseUser!=null){
                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(emailregistration.this,"Successfully Registered, Verification Mail Sent",Toast.LENGTH_SHORT).show();
                            firebaseAuth.signOut();
                            finish();
                            startActivity(new Intent(emailregistration.this,MainActivity.class));
                        }
                        else{
                            Toast.makeText(emailregistration.this," Verification Mail Not Sent",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
    }
}

