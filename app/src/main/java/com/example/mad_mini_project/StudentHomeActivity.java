package com.example.mad_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentHomeActivity extends AppCompatActivity {
    private Button StudentMessage, btnBusLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        btnBusLocation = (Button) findViewById(R.id.btnBusLocation);
        btnBusLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentHomeActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });
        StudentMessage = (Button) findViewById(R.id.StudentMessage);
        StudentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this, StudentHomeActivity.class);
                startActivity(intent);
            }
        });

    }

}