package com.example.mad_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminAllActivity extends AppCompatActivity {
    private Button AddStudentToBus, AddDriver, ViewDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_all);

        AddStudentToBus = (Button) findViewById(R.id.AddStudentToBus);
        AddStudentToBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminAllActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });
        AddDriver = (Button) findViewById(R.id.AddDriver);
        AddDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAllActivity.this, AddDriverActivity.class);
                startActivity(intent);
            }
        });
        ViewDriver = (Button) findViewById(R.id.ViewDriver);
        ViewDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAllActivity.this, ViewDriverActivity.class);
                startActivity(intent);
            }
        });
    }
}