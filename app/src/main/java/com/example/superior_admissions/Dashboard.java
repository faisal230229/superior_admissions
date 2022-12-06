package com.example.superior_admissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    Animation topAnim, bottomAnim, midAnim, leftAnim, rightAnim;
    ImageView imageView;
    ConstraintLayout constraintLayout;
    Button btLogout;
    CardView cv1, cv2, cv3;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageCarousel carousel = findViewById(R.id.imageslider);

        carousel.registerLifecycle(getLifecycle());
        List<CarouselItem> list = new ArrayList<>();

        list.add(new CarouselItem("https://pbs.twimg.com/media/DwAG_p_X4AMCsV3.jpg"));
        list.add(new CarouselItem("https://www.superiorcolleges.edu.pk/wp-content/uploads/2022/04/superior-university.jpg"));
        list.add(new CarouselItem("https://www.superior.edu.pk/wp-content/uploads/2022/04/47571366_329267381009073_3702311059541983232_n.jpg"));
        list.add(new CarouselItem("https://www.superior.edu.pk/wp-content/uploads/2022/04/superior_college_lahore-1024x693-1.jpg"));
        list.add(new CarouselItem("https://lh5.googleusercontent.com/p/AF1QipPsl4wOPf-8FjT5SC5xMf-jpoudBzBCVJo_9_-u=w1080-k-no"));
        carousel.setData(list);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        midAnim = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.left_animation);
        rightAnim = AnimationUtils.loadAnimation(this, R.anim.right_animation);

        imageView = findViewById(R.id.suplogo);
        constraintLayout = findViewById(R.id.box1);
        btLogout=findViewById(R.id.bt_logout);
        firebaseAuth=FirebaseAuth.getInstance();
        cv1 = (CardView) findViewById(R.id.cv1);
        cv2 = (CardView) findViewById(R.id.cv2);
        cv3 = (CardView) findViewById(R.id.cv3);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Csitcv.class);
                startActivity(intent);
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Secv.class);
                startActivity(intent);
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Mandpcv.class);
                startActivity(intent);
            }
        });

        carousel.setAnimation(rightAnim);
        imageView.setAnimation(topAnim);
        constraintLayout.setAnimation(leftAnim);
        cv1.setAnimation(bottomAnim);
        cv2.setAnimation(bottomAnim);
        cv3.setAnimation(bottomAnim);

        // Initialize firebase user
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        googleSignInClient= GoogleSignIn.getClient(Dashboard.this
                , GoogleSignInOptions.DEFAULT_SIGN_IN);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out from google
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Check condition
                        if(task.isSuccessful())
                        {
                            // When task is successful
                            // Sign out from firebase
                            firebaseAuth.signOut();

                            // Display Toast
                            Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();

                            // Finish activity
                            finish();
                        }
                    }
                });
            }
        });
    }
    }
