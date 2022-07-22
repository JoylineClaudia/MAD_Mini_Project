package com.example.mad_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {

    private EditText uname, fname, quantity, number;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);




        Intent i = getIntent();


        Double x =i.getDoubleExtra("lat",13.05124);
        Double y =i.getDoubleExtra("lng",74.964864);




        final String address = "geo:"+String.valueOf(x)+","+String.valueOf(y);
        String lat=String.valueOf(x);
        String lng=String.valueOf(y);
        btn = (Button) findViewById(R.id.btnloc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "Label which you want" + ")";
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

//                Uri gmmIntentUri = Uri.parse(address);
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.gms.maps ");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                else{
                    Toast.makeText(DisplayActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(address));
//                startActivity(intent);

            }
        });

    }
}