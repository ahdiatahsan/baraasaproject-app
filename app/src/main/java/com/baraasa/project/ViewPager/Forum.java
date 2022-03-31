package com.baraasa.project.ViewPager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataThread;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Adapter.AdapterForum;
import com.baraasa.project.Model.ThreadModel;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forum extends Fragment {

    AdapterForum adapterForum;
    RecyclerView rv_forum;
    LocalStorage localStorage;
    Login_response login_response;
    SwipeRefreshLayout srlData;
    ConstraintLayout datakosong, dataerror;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_forum, container, false);
        srlData = view.findViewById(R.id.swipe_refresh_forum);
        datakosong = view.findViewById(R.id.dataforumkosong);
        dataerror = view.findViewById(R.id.dataforumerror);
        dataerror.setVisibility(View.GONE);
        datakosong.setVisibility(View.GONE);
        rv_forum = view.findViewById(R.id.rv_forum);
        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        getDataForum();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataForum();
                dataerror.setVisibility(View.GONE);

            }
        });
        return view;
    }

    public void getDataForum() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataThread> call = apiInterface.threadmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataThread>() {
            @Override
            public void onResponse(Call<DataThread> call, Response<DataThread> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<ThreadModel> thread = response.body().getThread();
                    DataThread dataThread = response.body();
                    adapterForum = new AdapterForum(thread, getActivity());
                    adapterForum.notifyDataSetChanged();
                    rv_forum.setVisibility(View.VISIBLE);
                    rv_forum.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_forum.setAdapter(adapterForum);
                    if (dataThread.getMessage().equals("Data Kosong")) {
                        datakosong.setVisibility(View.VISIBLE);
                        rv_forum.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<DataThread> call, Throwable t) {
                srlData.setRefreshing(false);
                dataerror.setVisibility(View.VISIBLE);
            }
        });
    }

}