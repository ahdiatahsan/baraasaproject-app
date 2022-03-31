package com.baraasa.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Menu.Home_Menu;
import com.baraasa.project.Menu.Profile_Menu;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    boolean doubleBack = false;
    private ChipNavigationBar bottomNavigationView;
    private Fragment fragment = null;
    Client client;
    ApiInterface apiInterface;
    LocalStorage localStorage;
    private ConstraintLayout errorconnection;
    private RelativeLayout connected;
    private Button test;
    private String message;

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }
        this.doubleBack = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack = false;
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        errorconnection = findViewById(R.id.error_connection);
        connected = findViewById(R.id.connected);
        test = findViewById(R.id.test);
        bottomNavigationView.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new Home_Menu()).commit();
        bottomNavigationView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.home:
                        fragment = new Home_Menu();
                        break;
                    case R.id.profile:
                        fragment = new Profile_Menu();
                        break;
                    case R.id.about:
                        fragment = new AboutFm();
                        break;
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();

                }
            }
        });
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(new ConnectionReceiver(), filter);
    }


}
