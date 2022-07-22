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

public class DriverActivity extends AppCompatActivity {

    EditText DriverEmail,DriverPassword;
    Button btnDriverLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth fAuth;
    FirebaseUser fUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DriverEmail=findViewById(R.id.DriverEmail);
        DriverPassword=findViewById(R.id.DriverPassword);
        btnDriverLogin=findViewById(R.id.btnDriverLogin);

        progressDialog=new ProgressDialog(this);
        fAuth=FirebaseAuth.getInstance();
        fUser=fAuth.getCurrentUser();


        btnDriverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String drv_email=DriverEmail.getText().toString();
        String drv_password=DriverPassword.getText().toString();

        if(!drv_email.matches(emailPattern))
        {
            DriverEmail.setError("Enter Correct Email");
        }
        else if (drv_password.isEmpty() || drv_password.length()<6)
        {
            DriverPassword.setError("Enter Proper Password");
        }
        else
        {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            fAuth.signInWithEmailAndPassword(drv_email,drv_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(DriverActivity.this,"Login Successful",Toast.LENGTH_SHORT);
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(DriverActivity.this, ""+task.getException(), Toast.LENGTH_SHORT);

                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(DriverActivity.this,MapsActivityDriver.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}