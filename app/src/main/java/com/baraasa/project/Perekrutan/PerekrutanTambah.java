package com.baraasa.project.Perekrutan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataRecruitmentDaftar;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerekrutanTambah extends AppCompatActivity {

    private static final int PICKFILE_RESULT_CODE = 1;
    private Toolbar toolbar;
    private EditText Nama_Peserta, BerkasPerekrutan;

    LocalStorage localStorage;
    Login_response login_response;
    ImageView fotoPofil;
    Uri uri;
    String nama, filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perekrutantambah);

        getSupportActionBar().hide();

        localStorage = new LocalStorage(PerekrutanTambah.this);
        login_response = new Login_response();

        toolbar = (Toolbar) findViewById(R.id.toolbarPerekrutan);
        toolbar.setTitle("Perekrutan");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.ceklis);
        fotoPofil = findViewById(R.id.imgfoto);

        BerkasPerekrutan = findViewById(R.id.edittextberkasperekrutan);
        Nama_Peserta = findViewById(R.id.edittextnamapendaftar);
        Nama_Peserta.setText(localStorage.getStringValue(LocalStorage.NAME));
        Nama_Peserta.setEnabled(false);
//        BerkasPerekrutan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImagePicker.with(PerekrutanTambah.this)
//                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
//                        .start();
//            }
//        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_ceklis) {
//                    nama = Nama_Peserta.getText().toString();
//                    Nama_Peserta.setText(localStorage.getStringValue(LocalStorage.NAME));
//                    filename = BerkasPerekrutan.getText().toString();
                    if (BerkasPerekrutan.getText().toString().isEmpty()){
                        BerkasPerekrutan.setError("Data Kosong");
                    } else {
                        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                        Call<DataRecruitmentDaftar> call = apiInterface.recruitmentdaftarmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA),
                                BerkasPerekrutan.getText().toString().trim());
                        call.enqueue(new Callback<DataRecruitmentDaftar>() {
                            @Override
                            public void onResponse(Call<DataRecruitmentDaftar> call, Response<DataRecruitmentDaftar> response) {
                                if (response.isSuccessful()) {
                                    ProgressDialog progressDialog = new ProgressDialog(PerekrutanTambah.this);
                                    progressDialog.show();
                                    progressDialog.setContentView(R.layout.dialog_loader);
                                    progressDialog.setCancelable(false);
                                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            DataRecruitmentDaftar dataRecruitmentDaftar = response.body();
                                            Toast.makeText(PerekrutanTambah.this, dataRecruitmentDaftar.getMessage(), Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }, 2000);
                                }
                            }

                            @Override
                            public void onFailure(Call<DataRecruitmentDaftar> call, Throwable t) {
                                Toast.makeText(PerekrutanTambah.this, "" + t, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
                return false;
            }
        });

    }
}