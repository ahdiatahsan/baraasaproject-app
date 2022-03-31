package com.baraasa.project.Event;

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
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataEvent;
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

public class Event_Tambah extends AppCompatActivity {

    private EditText Judul_Event, Nama_Peserta, Alasan, BerkasAcara;
    private Toolbar toolbar;
    Uri uri;
    LocalStorage localStorage;
    Login_response login_response;
    public static final int PICKFILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_tambah);

        getSupportActionBar().hide();
        localStorage = new LocalStorage(Event_Tambah.this);
        login_response = new Login_response();
        toolbar = (Toolbar) findViewById(R.id.toolbareventikut);
        toolbar.setTitle("Pendaftaran");
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
                    if (Alasan.getText().toString().isEmpty()){
                        Alasan.setError("Data Kosong");
                    } else if (BerkasAcara.getText().toString().isEmpty()){
                        BerkasAcara.setError("Data Kosong");
                    } else {
                        File file = new File(uri.getPath());
                        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), file);
                        MultipartBody.Part partfile = MultipartBody.Part.createFormData("file", file.getName(), reqBody);
                        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                        Call<DataEvent> call = apiInterface.ikuteventmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA),
                                RequestBody.create(MediaType.parse("text/plain"), getIntent().getStringExtra("id")),
                                RequestBody.create(MediaType.parse("text/plain"), Alasan.getText().toString()),partfile);
                        call.enqueue(new Callback<DataEvent>() {
                            @Override
                            public void onResponse(Call<DataEvent> call, Response<DataEvent> response) {
                                if (response.isSuccessful()) {
                                    ProgressDialog progressDialog = new ProgressDialog(Event_Tambah.this);
                                    progressDialog.show();
                                    progressDialog.setContentView(R.layout.dialog_loader);
                                    progressDialog.setCancelable(false);
                                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            DataEvent dataEvent = response.body();
                                            Toast.makeText(Event_Tambah.this, dataEvent.getMessage(), Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }, 2000);
                                }
                            }

                            @Override
                            public void onFailure(Call<DataEvent> call, Throwable t) {
                                Toast.makeText(Event_Tambah.this, "Pastikan Koneksi Anda Baik", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }
                return false;
            }
        });

        Judul_Event = findViewById(R.id.edittexjudulacara);
        Nama_Peserta = findViewById(R.id.edittextnamaacara);
        Judul_Event.setText(getIntent().getStringExtra("judul"));
        Judul_Event.setEnabled(false);
        Nama_Peserta.setText(localStorage.getStringValue(LocalStorage.NAME));
        Nama_Peserta.setEnabled(false);
        Alasan = findViewById(R.id.edittextalasan);
        Alasan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.edittextalasan) {
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
        BerkasAcara = findViewById(R.id.edittextberkasacara);

        BerkasAcara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Event_Tambah.this)
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            BerkasAcara.setText(uri.getLastPathSegment());

        }

    }
}
