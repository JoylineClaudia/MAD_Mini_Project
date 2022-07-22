package com.example.mad_mini_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminActivity extends AppCompatActivity {

    EditText AdminUsername,AdminPassword;
    Button btnAdminLogin;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
       //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AdminUsername=findViewById(R.id.AdminUsername);
        AdminPassword=findViewById(R.id.AdminPassword);
        btnAdminLogin=findViewById(R.id.btnAdminLogin);

        progressDialog=new ProgressDialog(this);



        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String admin_uname=AdminUsername.getText().toString();
                String admin_password=AdminPassword.getText().toString();
                if(!admin_uname.equals("admin") ) {
                    Toast.makeText(AdminActivity.this,"Wrong Username!",Toast.LENGTH_LONG).show();
                    return;
                }else if(!admin_password.equals("admin123")){
                    Toast.makeText(AdminActivity.this,"Wrong password!",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Toast.makeText(AdminActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(AdminActivity.this,AdminAllActivity.class);
                    startActivity(i);

                }
            }
        });
    }



}
