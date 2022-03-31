package com.baraasa.project.Forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataTambahForum;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forum_Tambah extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText Judul, Deskripsi;
    LocalStorage localStorage;
    Login_response login_response;
    String title, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_tambah);

        getSupportActionBar().hide();

        localStorage = new LocalStorage(this);
        login_response = new Login_response();

        toolbar = (Toolbar) findViewById(R.id.toolbartambahforum);
        Judul = findViewById(R.id.etpertanyaan);
        Deskripsi = findViewById(R.id.deskripsipertanyaan);
        toolbar.setTitle("Tambah Forum");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.ceklis);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_ceklis) {

                    title = Judul.getText().toString().trim();
                    body = Deskripsi.getText().toString().trim();
                    if (title.isEmpty() && body.isEmpty()) {
                        Judul.setError("Data Kosong");
                        Deskripsi.setError("Data Kosong");
                    } else if (title.isEmpty()) {
                        Judul.setError("Data Kosong");
                    } else if (body.isEmpty()) {
                        Deskripsi.setError("Data Kosong");
                    } else {
                        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                        Call<DataTambahForum> call = apiInterface.tambahthreasmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), title, body);
                        call.enqueue(new Callback<DataTambahForum>() {
                            @Override
                            public void onResponse(Call<DataTambahForum> call, Response<DataTambahForum> response) {
                                if (response.isSuccessful()){
                                    ProgressDialog progressDialog = new ProgressDialog(Forum_Tambah.this);
                                    progressDialog.show();
                                    progressDialog.setContentView(R.layout.dialog_loader);
                                    progressDialog.setCancelable(false);
                                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            DataTambahForum dataTambahForum = response.body();
                                            Toast.makeText(Forum_Tambah.this, dataTambahForum.getMessage(), Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }, 2000);
                                }
                            }

                            @Override
                            public void onFailure(Call<DataTambahForum> call, Throwable t) {
                                Toast.makeText(Forum_Tambah.this, ""+t, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                return false;
            }
        });


        Deskripsi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.etpertanyaan) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });


    }
}