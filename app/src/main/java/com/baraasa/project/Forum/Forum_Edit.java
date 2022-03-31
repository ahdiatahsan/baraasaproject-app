package com.baraasa.project.Forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataEditForum;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forum_Edit extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText Judul, Deskripsi;
    LocalStorage localStorage;
    Login_response login_response;
    String title, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_edit);
        getSupportActionBar().hide();
        localStorage = new LocalStorage(this);
        login_response = new Login_response();
        toolbar = (Toolbar) findViewById(R.id.toolbareditforum);
        toolbar.setTitle("Ubah Forum");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Judul = findViewById(R.id.editetpertanyaan);
        Judul.setText(getIntent().getStringExtra("pertanyaan"));
        Deskripsi = findViewById(R.id.editdeskripsipertanyaan);
        Deskripsi.setText(getIntent().getStringExtra("deskripsi"));

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
                        Call<DataEditForum> call = apiInterface.editthreasmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA),getIntent().getStringExtra("id"), title, body);
                        call.enqueue(new Callback<DataEditForum>() {
                            @Override
                            public void onResponse(Call<DataEditForum> call, Response<DataEditForum> response) {
                                if (response.isSuccessful()){
                                    ProgressDialog progressDialog = new ProgressDialog(Forum_Edit.this);
                                    progressDialog.show();
                                    progressDialog.setContentView(R.layout.dialog_loader);
                                    progressDialog.setCancelable(false);
                                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            DataEditForum dataEditForum = response.body();
                                            Toast.makeText(Forum_Edit.this, dataEditForum.getMessage(), Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Forum_Edit.this, Forum_Detail_Saya.class);
                                            intent.putExtra("id", getIntent().getStringExtra("id"));
                                            intent.putExtra("nama", getIntent().getStringExtra("nama"));
                                            intent.putExtra("pertanyaan",title);
                                            intent.putExtra("deskripsi",body);
                                            intent.putExtra("tgl", getIntent().getStringExtra("tgl"));
                                            intent.putExtra("gambar", getIntent().getStringExtra("gambar"));
                                            startActivity(intent);
                                            finish();
                                        }
                                    }, 2000);

                                }
                            }

                            @Override
                            public void onFailure(Call<DataEditForum> call, Throwable t) {

                            }
                        });

                    }
                }
                return false;
            }
        });


    }
}