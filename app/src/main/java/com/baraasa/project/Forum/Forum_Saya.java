package com.baraasa.project.Forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baraasa.project.API.Client;
import com.baraasa.project.Adapter.AdapterForumDetailSaya;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Model.DataProfileThread;
import com.baraasa.project.Model.ThreadModel;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forum_Saya extends AppCompatActivity {

    Toolbar toolbar;
    ImageView img_tambah;
    AdapterForumDetailSaya adapterForumDetailSaya;
    RecyclerView rv_forumsaya;
    LocalStorage localStorage;
    Login_response login_response;
    //    ConstraintLayout datakosong, dataerror;
    SwipeRefreshLayout srlData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_saya);

        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarforumsaya);
        srlData = findViewById(R.id.swipe_refresh_forum_saya);
//        datakosong = findViewById(R.id.dataforumsayakosong);
//        dataerror = findViewById(R.id.dataforumsayaerror);
//        datakosong.setVisibility(View.GONE);
//        dataerror.setVisibility(View.GONE);
        toolbar.setTitle("Forum Saya");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_tambah = findViewById(R.id.img_tambah);
        img_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forum_Saya.this, Forum_Tambah.class);
                startActivity(intent);
            }
        });
        rv_forumsaya = findViewById(R.id.rv_forum);
        localStorage = new LocalStorage(Forum_Saya.this);
        login_response = new Login_response();
        getDataForum();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataForum();
            }
        });

    }

    private void getDataForum() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataProfileThread> call = apiInterface.threadprofilemodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataProfileThread>() {
            @Override
            public void onResponse(Call<DataProfileThread> call, Response<DataProfileThread> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<ThreadModel> thread = response.body().getThread();
                    adapterForumDetailSaya = new AdapterForumDetailSaya(thread, Forum_Saya.this);
                    adapterForumDetailSaya.notifyDataSetChanged();
                    rv_forumsaya.setLayoutManager(new LinearLayoutManager(Forum_Saya.this, RecyclerView.VERTICAL, false));
                    rv_forumsaya.setAdapter(adapterForumDetailSaya);
                }
            }

            @Override
            public void onFailure(Call<DataProfileThread> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataForum();
    }
}