package com.baraasa.project.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baraasa.project.API.Client;
import com.baraasa.project.API.Server;
import com.baraasa.project.Model.DataRecruitment;
import com.baraasa.project.Perekrutan.PerekrutanDetail;
import com.baraasa.project.Response.LocalStorage;
import com.bumptech.glide.Glide;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recruitment extends Fragment {

    ImageView Tambah, Thumbnail;
    ConstraintLayout layout, nolayout;
    LocalStorage localStorage;
    Login_response login_response;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Tambah = view.findViewById(R.id.tambahperekrutan);
        Thumbnail = view.findViewById(R.id.thumbnail);
        nolayout = view.findViewById(R.id.tdkadarecruitment);
        layout = view.findViewById(R.id.adarecruitment);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_recturitment);


        Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PerekrutanDetail.class);
                v.getContext().startActivity(intent);
            }
        });
        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        getRecruitment();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRecruitment();
            }
        });
    }

    void getRecruitment(){
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataRecruitment> call= apiInterface.recruitmentmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataRecruitment>() {
            @Override
            public void onResponse(Call<DataRecruitment> call, Response<DataRecruitment> response) {
                DataRecruitment dataRecruitment = response.body();
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()){
                    if (dataRecruitment.getRecruitment().getRecruitment_status().equals("0")){
                        nolayout.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.GONE);
                    } else if (dataRecruitment.getRecruitment().getRecruitment_status().equals("1")) {
                        Glide.with(getActivity()).load(Server.URL_GAMBARRECRUITMENT + dataRecruitment.getRecruitment().getRecruitment_thumbnail()).into(Thumbnail);
                    }
                }
            }

            @Override
            public void onFailure(Call<DataRecruitment> call, Throwable t) {
                nolayout.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recruitment, container, false);
    }
}