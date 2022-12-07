package com.example.superior_admissions;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Subjects extends AppCompatActivity {

    Button feebtn;
    RecyclerView recyclerView;
    DatabaseReference database;
    SubjectAdapter subjectAdapter;
    ArrayList<SubjectModel> list;
    String value = "";
    String cnic = "", degree = "";
    Integer totalCredit = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        recyclerView = findViewById(R.id.recyclerList);
        feebtn = findViewById(R.id.generatefeebtn);

        Intent intent = getIntent();
        value = intent.getStringExtra("department");

        cnic = intent.getStringExtra("cnic");
        degree = intent.getStringExtra("degree");

        database = FirebaseDatabase.getInstance().getReference("csSubjects");
        if (value.equals("it")) {
            database = FirebaseDatabase.getInstance().getReference("itSubjects");
        }
        else if(value.equals("cyber")){
            database = FirebaseDatabase.getInstance().getReference("cyberSubjects");
        }
        else if(value.equals("ai")){
            database = FirebaseDatabase.getInstance().getReference("aiSubjects");
        }
        else if(value.equals("se")){
            database = FirebaseDatabase.getInstance().getReference("seSubjects");
        }
        else if(value.equals("gm")){
            database = FirebaseDatabase.getInstance().getReference("gamingSubjects");
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        subjectAdapter = new SubjectAdapter(this,list);
        recyclerView.setAdapter(subjectAdapter);

        Intent i = new Intent(Subjects.this, Fee_challan.class);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SubjectModel subject = dataSnapshot.getValue(SubjectModel.class);
                    list.add(subject);
                }
                for (int i = 0; i < list.size(); i++){
                    totalCredit = totalCredit + list.get(i).sbjCredit;

                }
                i.putExtra("credits", totalCredit);
                subjectAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        feebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("cnic", cnic);
                i.putExtra("degree", degree);
                startActivity(i);
            }
        });

    }
}