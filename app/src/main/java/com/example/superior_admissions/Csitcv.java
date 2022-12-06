package com.example.superior_admissions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Csitcv extends AppCompatActivity {
    Button cs, cyber, ai, it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csitcv);

        cs = findViewById(R.id.csreg_btn);
        cyber = findViewById(R.id.cyberreg_btn);
        ai = findViewById(R.id.aireg_btn);
        it = findViewById(R.id.itreg_btn);

        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Csitcv.this, Csregfrom.class);
                startActivity(intent);
            }
        });
        cyber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Csitcv.this, Csregfrom.class);
                startActivity(intent);
            }
        });
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Csitcv.this, Csregfrom.class);
                startActivity(intent);
            }
        });
        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Csitcv.this, Csregfrom.class);
                startActivity(intent);
            }
        });
    }
}