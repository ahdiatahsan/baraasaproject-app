package com.baraasa.project.Forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataKomen;
import com.baraasa.project.Model.DataKomenList;
import com.baraasa.project.Model.KomentarModel;
import com.baraasa.project.Response.LocalStorage;
import com.bumptech.glide.Glide;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Adapter.AdapterKomentar;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forum_Detail extends AppCompatActivity {

    Toolbar toolbar;
    TextView judul, deskripsi, nama, tgl;
    ImageView gambar, kirim;
    EditText komen;
    AdapterKomentar adapterKomentar;
    RecyclerView rv_komentar;
    LocalStorage localStorage;
    Login_response login_response;
    SwipeRefreshLayout srlData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_detail);
        getSupportActionBar().hide();
        localStorage = new LocalStorage(this);
        login_response = new Login_response();
        toolbar = (Toolbar) findViewById(R.id.toolbarforum);
        toolbar.setTitle("Forum");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gambar = findViewById(R.id.forum_photo_profile);
        nama = findViewById(R.id.penulis);
        judul = findViewById(R.id.pertanyaan);
        deskripsi = findViewById(R.id.deskripsi);
        tgl = findViewById(R.id.date);
        komen = findViewById(R.id.comment);
        kirim = findViewById(R.id.bt_kirim);

        String gmbr = getIntent().getStringExtra("gambar");
        Glide.with(this).load(gmbr).into(gambar);
        nama.setText(getIntent().getStringExtra("nama"));
        judul.setText(getIntent().getStringExtra("pertanyaan"));
        deskripsi.setText(getIntent().getStringExtra("deskripsi"));
        tgl.setText(getIntent().getStringExtra("tgl"));

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (komen.getText().toString().isEmpty()) {
                    komen.setError("Data Kosong");
                } else {
                    ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                    Call<DataKomen> call = apiInterface.komenmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), getIntent().getStringExtra("id"), komen.getText().toString());
                    call.enqueue(new Callback<DataKomen>() {
                        @Override
                        public void onResponse(Call<DataKomen> call, Response<DataKomen> response) {
                            if (response.isSuccessful()) {
                                DataKomen dataKomen = response.body();
                                Toast.makeText(Forum_Detail.this, dataKomen.getMessage(), Toast.LENGTH_SHORT).show();
                                komen.setText("");
                                getDataKomen();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataKomen> call, Throwable t) {
                            Toast.makeText(Forum_Detail.this, ""+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        srlData = findViewById(R.id.swipe_refresh_komentar);
        getDataKomen();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataKomen();
            }
        });
    }

    public void getDataKomen() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataKomenList> call = apiInterface.listkomenmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), getIntent().getStringExtra("id"));
        call.enqueue(new Callback<DataKomenList>() {
            @Override
            public void onResponse(Call<DataKomenList> call, Response<DataKomenList> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()){
                    ArrayList<KomentarModel> komen = response.body().getComment();
//                    DataKomenList dataKomenList  = response.body();
                    adapterKomentar = new AdapterKomentar(komen,Forum_Detail.this);
                    adapterKomentar.notifyDataSetChanged();
                    rv_komentar = findViewById(R.id.rv_komentar);
                    rv_komentar.setLayoutManager(new LinearLayoutManager(Forum_Detail.this, RecyclerView.VERTICAL, false));
                    rv_komentar.setAdapter(adapterKomentar);
//                    Toast.makeText(Forum_Detail, dataKomenList.ge, Toast.LENGTH_SHORT).show();


                }
                else {
                    DataKomenList dataKomenList = response.body();
                    Toast.makeText(Forum_Detail.this, dataKomenList.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<DataKomenList> call, Throwable t) {
                srlData.setRefreshing(false);
                Toast.makeText(Forum_Detail.this, "Tidak ada komentar", Toast.LENGTH_SHORT).show();

            }
        });

    }

}