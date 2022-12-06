package com.example.superior_admissions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Secv extends AppCompatActivity {
    Button se;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secv);

        se = findViewById(R.id.sereg_btn);
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Secv.this, Seregform.class);
                startActivity(intent);
            }
        });
    }
}