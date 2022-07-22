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

public class StudentLoginActivity extends AppCompatActivity {
    TextView CreateNewAccount;

    EditText StudentEmail,StudentPassword;
    Button btnStudentLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth fAuth;
    FirebaseUser fUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        CreateNewAccount=findViewById(R.id.CreateNewAccount);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        StudentEmail=findViewById(R.id.StudentEmail);
        StudentPassword=findViewById(R.id.StudentPassword);
        btnStudentLogin=findViewById(R.id.btnStudentLogin);

        progressDialog=new ProgressDialog(this);
        fAuth=FirebaseAuth.getInstance();
        fUser=fAuth.getCurrentUser();

        CreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentLoginActivity.this,StudentRegisterActivity.class));
            }
        });

        btnStudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String std_email=StudentEmail.getText().toString();
        String std_password=StudentPassword.getText().toString();

        if(!std_email.matches(emailPattern))
        {
            StudentEmail.setError("Enter Correct Email");
        }
        else if (std_password.isEmpty() || std_password.length()<6)
        {
            StudentPassword.setError("Enter Proper Password");
        }
        else
        {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            fAuth.signInWithEmailAndPassword(std_email,std_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(StudentLoginActivity.this,"Login Successful",Toast.LENGTH_SHORT);
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(StudentLoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT);

                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(StudentLoginActivity.this,StudentHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}