package com.example.superior_admissions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Mandpcv extends AppCompatActivity {
    Button bsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandpcv);

        bsg = findViewById(R.id.gamingreg_btn);
        bsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mandpcv.this, Gandmregform.class);
                startActivity(intent);
            }
        });
    }
}