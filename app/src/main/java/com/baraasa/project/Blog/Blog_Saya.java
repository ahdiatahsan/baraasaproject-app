package com.baraasa.project.Blog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Adapter.AdapterBlogDetailSaya;
import com.baraasa.project.Model.DataProfileBlog;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Model.BlogModel;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog_Saya extends AppCompatActivity {

    Toolbar toolbar;
    ImageView img_tambahblog;
    AdapterBlogDetailSaya adapterBlogDetailSaya;
    RecyclerView rv_blogsaya;
    LocalStorage localStorage;
    Login_response login_response;
//    ConstraintLayout datakosong, dataerror;
    SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_saya);
        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarblogsaya);
//        datakosong = findViewById(R.id.datablogsayakosong);
//        dataerror = findViewById(R.id.datablogsayaerror);
//        datakosong.setVisibility(View.GONE);
//        dataerror.setVisibility(View.GONE);
        toolbar.setTitle("Blog Saya");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        img_tambahblog = findViewById(R.id.img_tambah);
//        img_tambahblog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Blog_Saya.this, Blog_Tambah.class);
//                startActivity(intent);
//            }
//        });
        srlData = findViewById(R.id.swipe_refreshBlogsaya);
        rv_blogsaya = findViewById(R.id.rv_blogsaya);
        localStorage = new LocalStorage(Blog_Saya.this);
        login_response = new Login_response();
        getDataBlog();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataBlog();
            }
        });
    }

    private void getDataBlog() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataProfileBlog> call = apiInterface.blogprofilemodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataProfileBlog>() {
            @Override
            public void onResponse(Call<DataProfileBlog> call, Response<DataProfileBlog> response) {
//                ProgressDialog progressDialog = new ProgressDialog(Blog_Saya.this);
//                progressDialog.show();
//                progressDialog.setContentView(R.layout.dialog_loader);
//                progressDialog.setCancelable(false);
//                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressDialog.dismiss();
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<BlogModel> blog = response.body().getBlog();
                    adapterBlogDetailSaya = new AdapterBlogDetailSaya(blog, Blog_Saya.this);
                    adapterBlogDetailSaya.notifyDataSetChanged();
                    rv_blogsaya.setLayoutManager(new LinearLayoutManager(Blog_Saya.this, RecyclerView.VERTICAL, false));
                    rv_blogsaya.setAdapter(adapterBlogDetailSaya);
//                    if (dataBlog.getMessage() == "Data Kosong"){
//                        datakosong.setVisibility(View.VISIBLE);
//                        rv_blog.setVisibility(View.GONE);
//
//                    }
                }
//                    }
//                }, 2000);
            }

            @Override
            public void onFailure(Call<DataProfileBlog> call, Throwable t) {
                Toast.makeText(Blog_Saya.this, "test" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataBlog();
    }
}