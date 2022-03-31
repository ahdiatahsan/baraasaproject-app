package com.baraasa.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.baraasa.project.OnBoarding.IntroActivity;
import com.baraasa.project.R;

public class SplashScreen extends AppCompatActivity {

    private static int Splas_Sreen = 1500;
//    Animation topAnimation, bottomAnimation;
    ImageView logo;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
//        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
//        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo = findViewById(R.id.logo);
//        slogan = findViewById(R.id.textView);

//        logo.setAnimation(topAnimation);
//        slogan.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
//                Pair[] pairs = new Pair[2];
//                pairs [0] = new Pair<View,String>(logo,"logo");
//                pairs [1] = new Pair<View,String>(slogan,"slogan");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
//                startActivity(intent,options.toBundle());
                startActivity(intent);
                finish();
            }
        },Splas_Sreen);
    }
}