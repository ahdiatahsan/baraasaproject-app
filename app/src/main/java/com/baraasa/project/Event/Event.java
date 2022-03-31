package com.baraasa.project.Event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.baraasa.project.API.Server;
import com.baraasa.project.R;

public class Event extends AppCompatActivity {

    Button Daftar;
    WebView webView;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbareventdetail);
        swipeRefreshLayout = findViewById(R.id.swipe_refreshevent);
        Daftar = findViewById(R.id.ikutevent);
        Daftar.setVisibility(View.GONE);
        toolbar.setTitle("Acara");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (getIntent().getStringExtra("status").equals("0")) {
            Daftar.setVisibility(View.GONE);
        } else if (getIntent().getStringExtra("status").equals("1")) {
            Daftar.setVisibility(View.VISIBLE);
            Daftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Event.this, Event_Tambah.class).putExtra("id", getIntent().getStringExtra("id"))
                            .putExtra("judul", getIntent().getStringExtra("judul")));
                }
            });
        }


        getData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

    }

    void getData() {
        swipeRefreshLayout.setRefreshing(false);
        WebView webview = (WebView) findViewById(R.id.webviewevent);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(Server.URL_DETAILEVENT + getIntent().getStringExtra("id") + "/webview");
    }

}