package com.baraasa.project.Perekrutan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.baraasa.project.R;

public class PerekrutanDetail extends AppCompatActivity {

    Button Daftar;
    WebView webView;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perekrutandetail);
        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarperekrutandetail);
        swipeRefreshLayout = findViewById(R.id.swipe_refreshperekrutan);
        Daftar = findViewById(R.id.ikutevent);
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerekrutanDetail.this, PerekrutanTambah.class));
            }
        });
        toolbar.setTitle("Perekrutan");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        WebView webview = (WebView) findViewById(R.id.webviewperekrutan);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://baraasaproject.bonekurir.com/api/recruitmentAPI/webview");
    }
}