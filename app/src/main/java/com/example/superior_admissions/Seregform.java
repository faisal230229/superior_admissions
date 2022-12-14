package com.example.superior_admissions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.superior_admissions.databinding.ActivityCsregfromBinding;
import com.example.superior_admissions.databinding.ActivitySeregformBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Seregform extends AppCompatActivity {

    ActivitySeregformBinding binding;
    String Name, Fname, email, Mobile_num,cnic1,fcnic,mat,inte,d1,d2, pass, register;
    long subfee = 145350, collfee =1, markfee=1, semfee, fee;
    int adfee = 20000, mischarge = 7500;
    float per, percent, d25=25/100, d30 = 30/100, d40 = 40/100, d50=50/100;

    RadioButton degree1, degree2, sup, other;

    FirebaseDatabase db;
    DatabaseReference reference;
    RadioGroup radioClass, programclass, college;

    RadioButton selectedBtn, selectedprog, selectclg;

    TextInputEditText name, fname, eml, pswd, mob, cnic, cnic2, matmarks, intmarks, regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seregform);

        binding = ActivitySeregformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        degree1 = findViewById(R.id.intdegree);
        degree2 = findViewById(R.id.intdegree2);
        sup = findViewById (R.id.sup);
        other = findViewById (R.id.other);
        regist = findViewById (R.id.registeration);
        fname = findViewById (R.id.fname);
        radioClass = findViewById(R.id.radioClass);
        college = findViewById (R.id.college);
        programclass = findViewById(R.id.programclass);
        name = findViewById(R.id.userName);
        eml = findViewById(R.id.Email);
        pswd = findViewById(R.id.password);
        mob = findViewById(R.id.Mobileno);
        cnic = findViewById(R.id.cnic);
        cnic2 = findViewById(R.id.fcnc);
        matmarks = findViewById(R.id.matric1);
        intmarks = findViewById(R.id.inter1);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = binding.userName.getText().toString();
                Fname = binding.fname.getText ().toString ();
                email = binding.Email.getText().toString();
                Mobile_num = binding.Mobileno.getText().toString();
                cnic1 = binding.cnic.getText().toString();
                fcnic = binding.fcnc.getText().toString();
                mat = binding.matric1.getText().toString();
                inte = binding.inter1.getText().toString();
                pass = binding.password.getText().toString();
                register = binding.registeration.getText ().toString ();

                int selectedId = radioClass.getCheckedRadioButtonId();
                int selectedid = programclass.getCheckedRadioButtonId();
                int select = college.getCheckedRadioButtonId ();
                // find the radiobutton by returned id
                selectedBtn = (RadioButton) findViewById(selectedId);
                selectedprog = findViewById(selectedid);
                selectclg = findViewById (select);

                Toast.makeText(Seregform.this,
                        selectedBtn.getText(), Toast.LENGTH_SHORT).show();


                Toast.makeText(Seregform.this,
                        selectedprog.getText(), Toast.LENGTH_SHORT).show();

                per = (Integer.parseInt (inte)) * 100;
                percent = per/1100;

                if(sup.isChecked ())
                {
                    collfee = subfee/2;
                }
                else if(other.isChecked()){
                    collfee = subfee;
                }
                if(percent>=70 && percent<=75){
                    markfee = (long) (subfee * d25);
                }
                else if(percent>=75 && percent<=80)
                {
                    markfee = (long) (subfee * d30);
                }
                else if(percent>=80 && percent<=85)
                {
                    markfee = (long) (subfee * d40);
                }
                else if(percent>=85 && percent<=90)
                {
                    markfee = (long) (subfee * d50);
                }

                if(collfee>=markfee){
                    semfee = markfee;
                }
                else if(markfee>collfee){
                    semfee = collfee;
                }



                fee = semfee + adfee + mischarge;

                System.out.println(fee);


                Users user = new Users(Name,Fname,email,Mobile_num,cnic1,fcnic,mat,inte,selectedBtn.getText ().toString (), pass,selectedprog.getText().toString(),selectclg.getText().toString(),register , String.valueOf(fee));

                if (binding.userName.getText().toString().isEmpty()){
                    binding.userName.setError("Name cannot be empty!");
                }
                else if (binding.Email.getText().toString().isEmpty()){
                    binding.Email.setError("Email cannot be empty!");
                }
                else if (binding.Mobileno.getText().toString().isEmpty()){
                    binding.Mobileno.setError("Mobile Number cannot be empty!");
                }
                else if (binding.Mobileno.getText().toString().trim().length() != 11){
                    binding.Mobileno.setError("Invalid mobile number!");
                }
                else if (binding.cnic.getText().toString().isEmpty()){
                    binding.cnic.setError("Your CNIC number cannot be empty!");
                }
                else if (binding.cnic.getText().toString().trim().length() != 13){
                    binding.cnic.setError("Invalid CNIC number!");
                }
                else if (binding.fcnc.getText().toString().isEmpty()){
                    binding.fcnc.setError("Your father's CNIC number cannot be empty!");
                }
                else if (binding.fcnc.getText().toString().trim().length() != 13){
                    binding.fcnc.setError("Invalid CNIC number!");
                }
                else if (binding.matric1.getText().toString().isEmpty()){
                    binding.matric1.setError("Marks cannot be left empty");
                }
                else if (Integer.parseInt(binding.matric1.getText().toString()) < 550){
                    binding.matric1.setError("You cannot apply in this program with marks less than 550");
                }
                else if (binding.inter1.getText().toString().isEmpty()){
                    binding.inter1.setError("Marks cannot be left empty");
                }
                else if (Integer.parseInt(binding.inter1.getText().toString()) < 550){
                    binding.inter1.setError("You cannot apply in this program with marks less than 550");
                }


                else {
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Student20");
                    reference.child(cnic1).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            binding.userName.setText("");
                            binding.Email.setText("");
                            binding.Mobileno.setText("");
                            binding.cnic.setText("");
                            binding.fcnc.setText("");
                            binding.matric1.setText("");
                            binding.inter1.setText("");
                            binding.password.setText("");


                            Toast.makeText(Seregform.this,"Successfuly Registered",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Seregform.this, Subjects.class);
                            intent.putExtra("cnic", cnic1);
                            intent.putExtra("degree", selectedprog.getText());
                            if (selectedprog.getText().equals("BS Software Engineering")){
                                intent.putExtra("department", "se");
                            }
                            startActivity(intent);
                        }
                    });
                }

            }




        });
    }
}