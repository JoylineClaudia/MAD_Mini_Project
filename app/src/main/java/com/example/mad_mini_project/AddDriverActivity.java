package com.example.mad_mini_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddDriverActivity extends AppCompatActivity {
    EditText DriverName,BusNo,DriverEmail,DriverPhone,DriverPassword;
    Button btnAddDriver;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    String driverID;

    FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DriverName=findViewById(R.id.DriverName);
        BusNo=findViewById(R.id.BusNo);
        DriverEmail=findViewById(R.id.DriverEmail);
        DriverPhone=findViewById(R.id.DriverPhone);
        DriverPassword=findViewById(R.id.DriverPassword);
        btnAddDriver=findViewById(R.id.btnAddDriver);
        progressDialog=new ProgressDialog(this);
        fAuth=FirebaseAuth.getInstance();
        fUser=fAuth.getCurrentUser();
        fStore=FirebaseFirestore.getInstance();


        btnAddDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String drv_name=DriverName.getText().toString();
        String drv_busno=BusNo.getText().toString();
        String drv_email=DriverEmail.getText().toString();
        String drv_phone=DriverPhone.getText().toString();
        String drv_password=DriverPassword.getText().toString();

        if(!drv_email.matches(emailPattern))
        {
            DriverEmail.setError("Enter Correct Email");
        }
        else if (drv_password.isEmpty() || drv_password.length()<6)
        {
            DriverPassword.setError("Enter Proper Password");
        }
        else if (drv_phone.length()<10)
        {
            DriverPhone.setError("Enter Correct Phone Number");
        }
        else
        {
            progressDialog.setMessage("Please wait while Adding Driver details...");
            progressDialog.setTitle("Driver Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            fAuth.createUserWithEmailAndPassword(drv_email,drv_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(AddDriverActivity.this,"Driver Added Sucessfully",Toast.LENGTH_SHORT).show();
                        driverID = fAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("drivers").document(driverID);
                        Map<String,Object> driver = new HashMap<>();
                        driver.put("drv_name",drv_name);
                        driver.put("drv_email",drv_email);
                        driver.put("drv_phone",drv_phone);
                        driver.put("drv_busno",drv_busno);
                        driver.put("drv_phone",drv_phone);
                        driver.put("drv_password",drv_password);
                        documentReference.set(driver).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "onSuccess: Driver Profile is created for "+ driverID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "onFailure: " + e.toString());
                            }
                        });
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(AddDriverActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(AddDriverActivity.this,ViewDriverActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}