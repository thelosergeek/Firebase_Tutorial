package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {

    private Button regist, git, phone, google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regist = (Button) findViewById(R.id.btnemail);
        git = (Button) findViewById(R.id.btgithub);
        phone = (Button) findViewById(R.id.btphone);
        google = (Button) findViewById(R.id.btgoogle);


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, phonereg.class));
            }
        });


        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, emailregistration.class));
            }
        });
    }
}
