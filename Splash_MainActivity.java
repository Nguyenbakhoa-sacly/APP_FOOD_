package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash_MainActivity extends AppCompatActivity {
Animation top_ani, bottom_ani;
ImageView imgtxtfood,img;
TextView slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_main);

        //animation
        top_ani = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_ani = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        img= findViewById(R.id.img);
        imgtxtfood = findViewById(R.id.imgtxtfood);
        slogan= findViewById(R.id.slogan);

        img.setAnimation(top_ani);
        imgtxtfood.setAnimation(bottom_ani);
        slogan.setAnimation(bottom_ani);

        //ran time chuyen activity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_MainActivity.this, Sign_In.class);
                startActivity(intent);
            }
        },3000);

    }

}