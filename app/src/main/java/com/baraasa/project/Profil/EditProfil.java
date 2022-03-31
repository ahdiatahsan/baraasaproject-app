package com.baraasa.project.Profil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataUpdate;
import com.baraasa.project.Response.LocalStorage;
import com.bumptech.glide.Glide;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfil extends AppCompatActivity {

    Toolbar toolbar;

    EditText EditNama, EditT4_lhr, EditTgl_lhr, EditAlamat, EditNotlpn, EditEmail, EditPass, EditKonPass;
    ImageButton img_ceklis;
    ImageView EditfotoProfil;
    Spinner EditJenis_Kelamin;
    LocalStorage localStorage;
    Login_response login_response;
    ApiInterface apiInterface;
    Uri selectedImage;
    String nama, jenis_kelamin, t4_lhr, tgl_lhr, alamat, notlpn, email;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat inputdateFormatter, tampildateFormatter;
    String dateFormatter, jk;
    Calendar newCalendar = Calendar.getInstance();
    String[] EditJKL = {"Laki - Laki", "Perempuan"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        localStorage = new LocalStorage(this);
        login_response = new Login_response();
        apiInterface = Client.getClient().create(ApiInterface.class);
        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarEditprofile);
        inputdateFormatter = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        tampildateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        jk = null;

        EditfotoProfil = findViewById(R.id.imgeeditfoto);
        EditNama = findViewById(R.id.editnama);
        EditJenis_Kelamin = findViewById(R.id.editspinnerjkl);
        EditT4_lhr = findViewById(R.id.editt4lhr);
        EditTgl_lhr = findViewById(R.id.edittgllhr);
        EditAlamat = findViewById(R.id.editalamat);
        EditNotlpn = findViewById(R.id.editnotlpn);
        EditEmail = findViewById(R.id.editemail);

        EditfotoProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(EditProfil.this)
                        .cropSquare()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditProfil.this, R.layout.textspinner, EditJKL);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        EditJenis_Kelamin.setAdapter(adapter);
        EditTgl_lhr.setText(tampildateFormatter.format(newCalendar.getTime()));
        EditTgl_lhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        EditAlamat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.editalamat) {
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

        Glide.with(this).load(localStorage.getStringValue(LocalStorage.FOTO)).into(EditfotoProfil);
        EditNama.setText(localStorage.getStringValue(LocalStorage.NAME));
        if (localStorage.getStringValue(LocalStorage.GENDER).equals("L")) {
            EditJenis_Kelamin.setSelection(0);
        } else if (localStorage.getStringValue(LocalStorage.GENDER).equals("P")) {
            EditJenis_Kelamin.setSelection(1);
        }
        EditT4_lhr.setText(localStorage.getStringValue(LocalStorage.BIRDTHPLEACE));
        EditTgl_lhr.setText(localStorage.getStringValue(LocalStorage.DATE_OF_BIRTH));
        EditAlamat.setText(localStorage.getStringValue(LocalStorage.ADDRESS));
        EditNotlpn.setText(localStorage.getStringValue(LocalStorage.PHONE_NUMBER));
        EditEmail.setText(localStorage.getStringValue(LocalStorage.EMAIL));


        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setTitle("Ubah Profil");
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

                    nama = EditNama.getText().toString();
                    jenis_kelamin = EditJenis_Kelamin.getSelectedItem().toString();
                    t4_lhr = EditT4_lhr.getText().toString();
                    tgl_lhr = EditTgl_lhr.getText().toString();
                    alamat = EditAlamat.getText().toString();
                    notlpn = EditNotlpn.getText().toString();
                    email = EditEmail.getText().toString();

                    if (nama.isEmpty() && t4_lhr.isEmpty() && alamat.isEmpty() && notlpn.isEmpty() && email.isEmpty()) {
                        EditNama.setError("Data Kosong");
                        EditT4_lhr.setError("Data Kosong");
                        EditAlamat.setError("Data Kosong");
                        EditNotlpn.setError("Data Kosong");
                        EditEmail.setError("Data Kosong");
                    } else if (nama.isEmpty()) {
                        EditNama.setError("Data Kosong");
                    } else if (t4_lhr.isEmpty()) {
                        EditT4_lhr.setError("Data Kosong");
                    } else if (alamat.isEmpty()) {
                        EditAlamat.setError("Data Kosong");
                    } else if (notlpn.isEmpty()) {
                        EditNotlpn.setError("Data Kosong");
                    } else if (email.isEmpty()) {
                        EditEmail.setError("Data Kosong");
                    } else if (selectedImage == null) {
                        if (EditJenis_Kelamin.getSelectedItem().toString().equals("Laki - Laki")) {
                            jk = "L";
                        } else if (EditJenis_Kelamin.getSelectedItem().toString().equals("Perempuan")) {
                            jk = "P";
                        }
//                        dateFormatter = inputdateFormatter.format(newCalendar.getTime());
                        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                        Call<DataUpdate> call = apiInterface.editProfile("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), RequestBody.create(MediaType.parse("text/plain"), nama),
                                RequestBody.create(MediaType.parse("text/plain"), jk), RequestBody.create(MediaType.parse("text/plain"), t4_lhr),
                                RequestBody.create(MediaType.parse("text/plain"), tgl_lhr), RequestBody.create(MediaType.parse("text/plain"), alamat),
                                RequestBody.create(MediaType.parse("text/plain"), notlpn), RequestBody.create(MediaType.parse("text/plain"), email), null);
                        call.enqueue(new Callback<DataUpdate>() {
                            @Override
                            public void onResponse(Call<DataUpdate> call, Response<DataUpdate> response) {
                                if (response.isSuccessful()) {
                                    DataUpdate dataUpdate = response.body();
                                    localStorage.save(LocalStorage.NAME, nama);
                                    localStorage.save(LocalStorage.GENDER, jk);
                                    localStorage.save(LocalStorage.BIRDTHPLEACE, t4_lhr);
                                    localStorage.save(LocalStorage.DATE_OF_BIRTH,tgl_lhr);
                                    localStorage.save(LocalStorage.ADDRESS, alamat);
                                    localStorage.save(LocalStorage.PHONE_NUMBER, notlpn);
                                    localStorage.save(LocalStorage.EMAIL, email);
                                    Toast.makeText(EditProfil.this, dataUpdate.getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<DataUpdate> call, Throwable t) {

                            }
                        });
                    } else {
                        if (EditJenis_Kelamin.getSelectedItem().toString().equals("Laki - Laki")) {
                            jk = "L";
                        } else if (EditJenis_Kelamin.getSelectedItem().toString().equals("Perempuan")) {
                            jk = "P";
                        }
//                        dateFormatter = inputdateFormatter.format(newCalendar.getTime());
                        File imagefile = new File(selectedImage.getPath());
                        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
                        MultipartBody.Part partImage = MultipartBody.Part.createFormData("photo", imagefile.getName(), reqBody);
                        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                        Call<DataUpdate> call = apiInterface.editProfile("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), RequestBody.create(MediaType.parse("text/plain"), nama),
                                RequestBody.create(MediaType.parse("text/plain"), jk), RequestBody.create(MediaType.parse("text/plain"), t4_lhr),
                                RequestBody.create(MediaType.parse("text/plain"), tgl_lhr), RequestBody.create(MediaType.parse("text/plain"), alamat),
                                RequestBody.create(MediaType.parse("text/plain"), notlpn), RequestBody.create(MediaType.parse("text/plain"), email), partImage);
                        call.enqueue(new Callback<DataUpdate>() {
                            @Override
                            public void onResponse(Call<DataUpdate> call, Response<DataUpdate> response) {
                                if (response.isSuccessful()) {
                                    DataUpdate dataUpdate = response.body();
                                    localStorage.save(LocalStorage.NAME, nama);
                                    localStorage.save(LocalStorage.GENDER, jk);
                                    localStorage.save(LocalStorage.BIRDTHPLEACE, t4_lhr);
                                    localStorage.save(LocalStorage.DATE_OF_BIRTH, tgl_lhr);
                                    localStorage.save(LocalStorage.ADDRESS, alamat);
                                    localStorage.save(LocalStorage.PHONE_NUMBER, notlpn);
                                    localStorage.save(LocalStorage.EMAIL, email);
                                    localStorage.save(LocalStorage.FOTO, selectedImage.getPath());
                                    Toast.makeText(EditProfil.this, dataUpdate.getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<DataUpdate> call, Throwable t) {

                            }
                        });
                    }
                }
                return false;
            }
        });

    }

    private void showDateDialog() {
        //Initiate DatePicker dialog
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                //Set Calendar untuk menampung tanggal yang dipilih
                newCalendar.set(year, monthOfYear, dayOfMonth);
                //Update TextView dengan tanggal yang kita pilih
                EditTgl_lhr.setText(inputdateFormatter.format(newCalendar.getTime()));
//                dateFormatter = inputdateFormatter.format(newCalendar.getTime());
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        //Tampilkan DatePicker dialog
        datePickerDialog.show();
    }
//    private void setDataEditProfile() {
//        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
//        Call<User> call = apiInterface.editProfile("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//            }
//        });
//
//
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            selectedImage = data.getData();
            EditfotoProfil.setImageURI(selectedImage);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Glide.with(this).load(localStorage.getStringValue(LocalStorage.FOTO)).into(EditfotoProfil);
//        EditNama.setText(localStorage.getStringValue(LocalStorage.NAME));
//        if (localStorage.getStringValue(LocalStorage.GENDER).equals("L")) {
//            EditJenis_Kelamin.setSelection(0);
//        } else if (localStorage.getStringValue(LocalStorage.GENDER).equals("P")) {
//            EditJenis_Kelamin.setSelection(1);
//        }
//        EditT4_lhr.setText(localStorage.getStringValue(LocalStorage.BIRDTHPLEACE));
//        EditTgl_lhr.setText(localStorage.getStringValue(LocalStorage.DATE_OF_BIRTH));
//        EditAlamat.setText(localStorage.getStringValue(LocalStorage.ADDRESS));
//        EditNotlpn.setText(localStorage.getStringValue(LocalStorage.PHONE_NUMBER));
//        EditEmail.setText(localStorage.getStringValue(LocalStorage.EMAIL));
//    }
}
