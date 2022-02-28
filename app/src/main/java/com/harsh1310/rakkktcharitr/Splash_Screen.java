package com.harsh1310.rakkktcharitr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        img=findViewById(R.id.img1);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.p022vlh9);

        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 600, 100, true);

        img.setImageBitmap(bMapScaled);
getSupportActionBar().hide();
        int timeset=10000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splash_Screen.this,
                        firstActivitity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, timeset);
    }
}