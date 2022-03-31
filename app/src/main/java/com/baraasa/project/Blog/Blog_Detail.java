package com.baraasa.project.Blog;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.baraasa.project.API.Server;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;
//import com.example.baraasaproject.ViewWebView;


public class Blog_Detail extends AppCompatActivity {

    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    LocalStorage localStorage;
    Login_response login_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);
        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarblog);
        toolbar.setTitle("Blog");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        localStorage = new LocalStorage(this);
        login_response = new Login_response();
        localStorage.getStringValue(LocalStorage.TOKEN_BARA);
//        getData();
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
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(Server.URL_DETAILBLOG+getIntent().getStringExtra("id"));
    }


//    void getDataBlog() {
//        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
//        Call<DataHapusForum> call = apiInterface.blogAPImodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), getIntent().getStringExtra("id"));
//        call.enqueue(new Callback<DataHapusForum>() {
//            @Override
//            public void onResponse(Call<DataHapusForum> call, Response<DataHapusForum> response) {
//                if (response.isSuccessful()) {
//                    DataHapusForum dataHapusForum = response.raw().message();
//                    Toast.makeText(Blog_Detail.this, "dataHapusForum", Toast.LENGTH_SHORT).show();
////                    Log.e("Blog", dataHapusForum);
//                } else {
//                    Toast.makeText(Blog_Detail.this, "dataHapusElse", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<DataHapusForum> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}