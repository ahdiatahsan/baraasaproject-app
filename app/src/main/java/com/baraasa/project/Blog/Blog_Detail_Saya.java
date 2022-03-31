package com.baraasa.project.Blog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.API.Server;
import com.baraasa.project.Model.DataHapusForum;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog_Detail_Saya extends AppCompatActivity {

    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    LocalStorage localStorage;
    Login_response login_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail_saya);
        getSupportActionBar().hide();
        localStorage = new LocalStorage(this);
        login_response = new Login_response();
        toolbar = (Toolbar) findViewById(R.id.toolbarblogdetailsaya);
        swipeRefreshLayout = findViewById(R.id.swipe_refreshblsaya);
        toolbar.setTitle("Blog Saya");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.blog_edit_hapus);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_hapusblog) {
                    ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                    Call<DataHapusForum> call = apiInterface.blogAPImodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), getIntent().getStringExtra("id"));
                    call.enqueue(new Callback<DataHapusForum>() {
                        @Override
                        public void onResponse(Call<DataHapusForum> call, Response<DataHapusForum> response) {
                            if (response.isSuccessful()) {
                                DataHapusForum dataHapusForum = response.body();
                                Toast.makeText(Blog_Detail_Saya.this, dataHapusForum.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataHapusForum> call, Throwable t) {

                        }
                    });
                }
                return false;
            }
        });

        swipeRefreshLayout.setRefreshing(true);
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
        WebView webview = (WebView) findViewById(R.id.webviewblogsaya);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(Server.URL_DETAILBLOG + getIntent().getStringExtra("id"));
    }

}