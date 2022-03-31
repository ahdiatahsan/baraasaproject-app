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
import android.widget.EditText;
import android.widget.ImageView;

import com.baraasa.project.API.Client;
import com.baraasa.project.Adapter.AdapterResearch;
import com.baraasa.project.Model.DataResearch;
import com.baraasa.project.Model.DataSearch;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Model.ResearchModel;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JurnalSearch extends Fragment {

    AdapterResearch adapterResearch;
    RecyclerView rv_research;
    LocalStorage localStorage;
    Login_response login_response;
    ConstraintLayout datakosong, dataerror;
    SwipeRefreshLayout srlData;
    EditText pencarian;
    ImageView cari;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        srlData = view.findViewById(R.id.swipe_refresh_research);
        rv_research = view.findViewById(R.id.rv_research);
        datakosong = view.findViewById(R.id.dataresearchkosong);
        dataerror = view.findViewById(R.id.dataresearcherror);
        dataerror.setVisibility(View.GONE);
        datakosong.setVisibility(View.GONE);
        pencarian = view.findViewById(R.id.carijurnal);
        cari = view.findViewById(R.id.bt_carijurnal);
        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        getDataResearch();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataResearch();
                dataerror.setVisibility(View.GONE);
            }
        });
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCariResearch();
                pencarian.setText("");
            }
        });
    }

    private void getDataResearch() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataResearch> call = apiInterface.researchmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataResearch>() {
            @Override
            public void onResponse(Call<DataResearch> call, Response<DataResearch> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<ResearchModel> research = response.body().getResearch();
                    DataResearch dataResearch = response.body();
                    adapterResearch = new AdapterResearch(research, getActivity());
                    adapterResearch.notifyDataSetChanged();
                    rv_research.setVisibility(View.VISIBLE);
                    rv_research.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_research.setAdapter(adapterResearch);
                    if (dataResearch.getMessage().equals("Data kosong")) {
                        datakosong.setVisibility(View.VISIBLE);
                        rv_research.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<DataResearch> call, Throwable t) {
                srlData.setRefreshing(false);
                dataerror.setVisibility(View.VISIBLE);
            }
        });


    }

    private void getCariResearch() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataSearch> call = apiInterface.searchmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA),
                pencarian.getText().toString());
        call.enqueue(new Callback<DataSearch>() {
            @Override
            public void onResponse(Call<DataSearch> call, Response<DataSearch> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<ResearchModel> research = response.body().getResearch();
                    DataSearch dataResearch = response.body();
                    adapterResearch = new AdapterResearch(research, getActivity());
                    adapterResearch.notifyDataSetChanged();
                    rv_research.setVisibility(View.VISIBLE);
                    rv_research.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_research.setAdapter(adapterResearch);
                    if (dataResearch.getMessage().equals("Data Kosong")) {
                        datakosong.setVisibility(View.VISIBLE);
                        rv_research.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<DataSearch> call, Throwable t) {
                srlData.setRefreshing(false);
                dataerror.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jurnal_search, container, false);
    }
}