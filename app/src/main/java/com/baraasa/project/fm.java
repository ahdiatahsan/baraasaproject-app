package com.baraasa.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.baraasa.project.API.Client;
import com.baraasa.project.Adapter.AdapterForumSearch;
import com.baraasa.project.Model.DataSearch;
import com.baraasa.project.Model.ThreadModelSearch;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fm extends AppCompatActivity {

    AdapterForumSearch adapterForumSearch;
    RecyclerView rv_forum;
    LocalStorage localStorage;
    Login_response login_response;
    SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);
        srlData = findViewById(R.id.swipe_refresh_forums);
//        datakosong = view.findViewById(R.id.dataforumkosong);
//        dataerror = view.findViewById(R.id.dataforumerror);
//        dataerror.setVisibility(View.GONE);
//        datakosong.setVisibility(View.GONE);
        rv_forum = findViewById(R.id.rv_forum);
        localStorage = new LocalStorage(fm.this);
        login_response = new Login_response();
        getDataForum();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataForum();
            }
        });
    }
    public void getDataForum() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataSearch> call = apiInterface.searchmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA),
                getIntent().getStringExtra("cariforum"));
        call.enqueue(new Callback<DataSearch>() {
            @Override
            public void onResponse(Call<DataSearch> call, Response<DataSearch> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<ThreadModelSearch> thread = response.body().getThread();
                    adapterForumSearch = new AdapterForumSearch(thread, fm.this);
                    adapterForumSearch.notifyDataSetChanged();
                    rv_forum.setVisibility(View.VISIBLE);
                    rv_forum.setLayoutManager(new LinearLayoutManager(fm.this, RecyclerView.VERTICAL, false));
                    rv_forum.setAdapter(adapterForumSearch);

                }
            }

            @Override
            public void onFailure(Call<DataSearch> call, Throwable t) {
//                srlData.setRefreshing(false);
//                dataerror.setVisibility(View.VISIBLE);
            }
        });
    }

}