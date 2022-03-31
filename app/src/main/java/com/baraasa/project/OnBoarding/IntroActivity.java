package com.baraasa.project.OnBoarding;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.baraasa.project.Login;
import com.baraasa.project.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
//    Button btnNext;
    int position = 0 ;
//    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // when this activity is about to be launch we need to check if its openened before or not
        if (restorePrefData()) {
            Intent mainActivity = new Intent(IntroActivity.this, Login.class );
            startActivity(mainActivity);
            finish();
        }
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);
        // ini views
//        btnNext = findViewById(R.id.btn_next);
//        btnGetStarted = findViewById(R.id.btn_get_started);
//        btnNext.setBackgroundColor(Color.BLACK);
//        btnGetStarted.setBackgroundColor(Color.BLACK);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Selamat Datang","Bara Asa Project hadir sebagai katalisator pengembangan diri yang berkelanjutan demi terwujudnya anak muda yang berdaya saing dan berkontribusi untuk Kota Palu.",R.drawable.ic_about_us_page_pana));
        mList.add(new ScreenItem("Berbagi Informasi","Bagikan informasi, ide, dan pemikiranmu seputar pengembangan diri.",R.drawable.ic_sharing_articles_pana));
        mList.add(new ScreenItem("Saling Terhubung","Saling terhubung, berbagi dan berkolaborasi dalam pengembangan diri secara berkelanjutan dengan seluruh pengguna.",R.drawable.ic_collab_pana));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);
        tabIndicator.clearOnTabSelectedListeners();
        for (View v : tabIndicator.getTouchables()) {
            v.setEnabled(false);
        }

        // next button click Listner
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                position = screenPager.getCurrentItem();
//                if (position < mList.size()) {
//                    position++;
//                    screenPager.setCurrentItem(position);
//                }
//                if (position == mList.size()-1) { // when we rech to the last screen
//                    // TODO : show the GETSTARTED Button and hide the indicator and the next button
//                    loaddLastScreen();
//                }
//            }
//        });
        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-3) {
                    tvSkip.setVisibility(View.GONE);
                }if (tab.getPosition() == mList.size()-2) {
                    tvSkip.setVisibility(View.GONE);
                }if (tab.getPosition() == mList.size()-1) {
                    tvSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        // Get Started button click listener
//        btnGetStarted.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                open main activity
//                Intent mainActivity = new Intent(IntroActivity.this, Login.class);
//                startActivity(mainActivity);
//                // also we need to save a boolean value to storage so next time when the user run the app
//                // we could know that he is already checked the intro screen activity
//                // i'm going to use shared preferences to that process
//                savePrefsData();
//                finish();
//            }
//        });

        // skip button click listener
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                screenPager.setCurrentItem(mList.size());
                Intent mainActivity = new Intent(IntroActivity.this, Login.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {
//        btnNext.setVisibility(View.INVISIBLE);
//        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button
        // setup animation
//        btnGetStarted.setAnimation(btnAnim);
    }
}
