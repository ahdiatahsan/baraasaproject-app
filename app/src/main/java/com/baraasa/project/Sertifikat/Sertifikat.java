package com.baraasa.project.Sertifikat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.CertificateModel;
import com.baraasa.project.Model.DataCertificate;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Adapter.AdapterSertifikat;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sertifikat extends AppCompatActivity {

    Toolbar toolbar;
    AdapterSertifikat adapterSertifikat;
    RecyclerView rv_sertifikat;
    SwipeRefreshLayout srlData;
    LocalStorage localStorage;
    Login_response login_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sertifikat);

        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarsertifikat);
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setTitle("Sertifikat Saya");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        srlData = findViewById(R.id.swipe_refreshsertifikat);
        rv_sertifikat = findViewById(R.id.rv_sertifikat);
        localStorage = new LocalStorage(Sertifikat.this);
        login_response = new Login_response();
        getDataCetificate();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataCetificate();
            }
        });
    }

    private void getDataCetificate() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataCertificate> call = apiInterface.certificatemodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataCertificate>() {
            @Override
            public void onResponse(Call<DataCertificate> call, Response<DataCertificate> response) {
                if (response.isSuccessful()) {
                    srlData.setRefreshing(false);
                    ArrayList<CertificateModel> sertifikat = response.body().getResearch();
                    adapterSertifikat = new AdapterSertifikat(sertifikat, Sertifikat.this);
                    adapterSertifikat.notifyDataSetChanged();
                    rv_sertifikat.setLayoutManager(new LinearLayoutManager(Sertifikat.this, RecyclerView.VERTICAL, false));
                    rv_sertifikat.setAdapter(adapterSertifikat);
                }

            }

            @Override
            public void onFailure(Call<DataCertificate> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataCetificate();
    }
}