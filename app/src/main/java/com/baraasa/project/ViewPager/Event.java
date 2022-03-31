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
import com.baraasa.project.Model.DataEvent;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Adapter.AdapterEvent;
import com.baraasa.project.Model.EventModel;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Event extends Fragment {

    AdapterEvent adapterEvent;
    RecyclerView rv_event;
    LocalStorage localStorage;
    Login_response login_response;
    SwipeRefreshLayout srlData;
    ConstraintLayout datakosong, dataerror;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        srlData = view.findViewById(R.id.swipe_refresh_event);
        datakosong = view.findViewById(R.id.dataeventkosong);
        dataerror = view.findViewById(R.id.dataeventerror);
        dataerror.setVisibility(View.GONE);
        datakosong.setVisibility(View.GONE);
        rv_event = view.findViewById(R.id.rv_event);
        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        getDataEvent();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataEvent();
                dataerror.setVisibility(View.GONE);
            }
        });

    }

    public void getDataEvent() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataEvent> call = apiInterface.eventmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataEvent>() {
            @Override
            public void onResponse(Call<DataEvent> call, Response<DataEvent> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<EventModel> event = response.body().getEvent();
                    DataEvent dataEvent = response.body();
                    adapterEvent = new AdapterEvent(event, getActivity());
                    adapterEvent.notifyDataSetChanged();
                    rv_event.setVisibility(View.VISIBLE);
                    rv_event.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_event.setAdapter(adapterEvent);
                    if (dataEvent.getMessage().equals("Data kosong")){
                        datakosong.setVisibility(View.VISIBLE);
                        rv_event.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<DataEvent> call, Throwable t) {
                srlData.setRefreshing(false);
                dataerror.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }
}