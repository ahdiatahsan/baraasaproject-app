package com.baraasa.project;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Response.X0;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    Toolbar toolbar;
    EditText Nama, T4_lhr, Tgl_lhr, Alamat, Notlpn, Email, Pass, KonPass;
    ImageView fotoPofil;
    Spinner Jenis_Kelamin;
    String nama, jenis_kelamin, t4_lhr, tgl_lhr, alamat, notlpn, email, pass, konPass, jk;
    Uri selectedImage;


    DatePickerDialog datePickerDialog;
    SimpleDateFormat inputdateFormatter, tampildateFormatter;
    String dateFormatter;
    Calendar newCalendar = Calendar.getInstance();

    String[] JKL = {"Laki - Laki", "Perempuan", "Pilih Jenis Kelamin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbarregistrasi);
        inputdateFormatter = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        tampildateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
//        img_ceklis = findViewById(R.id.img_ceklis);
        fotoPofil = findViewById(R.id.imgfoto);
        Nama = findViewById(R.id.edittextnama);
        Jenis_Kelamin = findViewById(R.id.spinnerjkl);
        T4_lhr = findViewById(R.id.edittextt4lhr);
        Tgl_lhr = findViewById(R.id.edittexttgllhr);
        Alamat = findViewById(R.id.edittextalamat);
        Notlpn = findViewById(R.id.edittextnotlpn);
        Email = findViewById(R.id.edittextemail);
        Pass = findViewById(R.id.edittextpass);
        KonPass = findViewById(R.id.edittextpasskonfir);

        nama = Nama.getText().toString();
//        jenis_kelamin = Jenis_Kelamin.getSelectedItem().toString();
        t4_lhr = T4_lhr.getText().toString();
        tgl_lhr = Tgl_lhr.getText().toString();
        alamat = Alamat.getText().toString();
        notlpn = Notlpn.getText().toString();
        email = Email.getText().toString();
        pass = Pass.getText().toString();
        konPass = KonPass.getText().toString();
        jk = null;
        toolbar.setTitle("Registrasi");
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
//                    Toast.makeText(Register.this, "" + selectedImage, Toast.LENGTH_SHORT).show();
                    nama = Nama.getText().toString();
                    jenis_kelamin = Jenis_Kelamin.getSelectedItem().toString();
                    t4_lhr = T4_lhr.getText().toString();
                    tgl_lhr = Tgl_lhr.getText().toString();
                    alamat = Alamat.getText().toString();
                    notlpn = Notlpn.getText().toString();
                    email = Email.getText().toString();
                    pass = Pass.getText().toString();
                    konPass = KonPass.getText().toString();
                    if (nama.isEmpty() && Jenis_Kelamin.getSelectedItem().equals("Pilih Jenis Kelamin") && t4_lhr.isEmpty() &&
                            alamat.isEmpty() && notlpn.isEmpty() && email.isEmpty() && pass.isEmpty() && konPass.isEmpty()) {
                        Nama.setError("Data Kosong");
                        ((TextView) Jenis_Kelamin.getSelectedView()).setError("Pilih Jenis Kelamin");
                        T4_lhr.setError("Data Kosong");
                        Alamat.setError("Data Kosong");
                        Notlpn.setError("Data Kosong");
                        Email.setError("Data Kosong");
                        Pass.setError("Data Kosong");
                        KonPass.setError("Data Kosong");
                    } else if (nama.isEmpty()) {
                        Nama.setError("Data Kosong");
                    } else if (Jenis_Kelamin.getSelectedItem().equals("Pilih Jenis Kelamin")) {
                        ((TextView) Jenis_Kelamin.getSelectedView()).setError("Pilih Jenis Kelamin");
                    } else if (t4_lhr.isEmpty()) {
                        T4_lhr.setError("Data Kosong");
                    } else if (alamat.isEmpty()) {
                        Alamat.setError("Data Kosong");
                    } else if (notlpn.isEmpty()) {
                        Notlpn.setError("Data Kosong");
                    } else if (email.isEmpty()) {
                        Email.setError("Data Kosong");
                    } else if (!isEmailValid(Email)) {
                        Email.setError("Pastikan Alamat Email");
                    } else if (pass.isEmpty()) {
                        Pass.setError("Data Kosong");
                    } else if (pass.length() < 8) {
                        Pass.setError("Minimal Password 8 digit");
                    } else if (konPass.isEmpty()) {
                        KonPass.setError("Data Kosong");
                    } else if (!konPass.equals(pass)) {
                        KonPass.setError("Sesusaikan Dengan Password");
                    } else if (selectedImage == null) {
                        if (Jenis_Kelamin.getSelectedItem().toString().equals("Laki - Laki")) {
                            jk = "L";
                        } else if (Jenis_Kelamin.getSelectedItem().toString().equals("Perempuan")) {
                            jk = "P";
                        }
                        dateFormatter = inputdateFormatter.format(newCalendar.getTime());
                        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                        Call<Login_response<X0>> call = apiInterface.regitermodel(RequestBody.create(MediaType.parse("text/plain"), nama),
                                RequestBody.create(MediaType.parse("text/plain"), jk), RequestBody.create(MediaType.parse("text/plain"), t4_lhr),
                                RequestBody.create(MediaType.parse("text/plain"), dateFormatter), RequestBody.create(MediaType.parse("text/plain"), alamat),
                                RequestBody.create(MediaType.parse("text/plain"), notlpn), RequestBody.create(MediaType.parse("text/plain"), email),
                                RequestBody.create(MediaType.parse("text/plain"), pass), RequestBody.create(MediaType.parse("text/plain"), konPass), null);
                        call.enqueue(new Callback<Login_response<X0>>() {
                            @Override
                            public void onResponse(Call<Login_response<X0>> call, Response<Login_response<X0>> response) {
                                if (response.isSuccessful()) {
                                    Login_response login_response = response.body();
                                    Toast.makeText(Register.this, login_response.getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (!response.isSuccessful()) {
                                    try {
                                        Gson gson = new Gson();
                                        Login_response message = gson.fromJson(response.errorBody().string(), Login_response.class);
                                        Toast.makeText(Register.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<Login_response<X0>> call, Throwable t) {
                                Toast.makeText(Register.this, "" + t, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        if (Jenis_Kelamin.getSelectedItem().toString().equals("Laki - Laki")) {
                            jk = "L";
                        } else if (Jenis_Kelamin.getSelectedItem().toString().equals("Perempuan")) {
                            jk = "P";
                        }
                        dateFormatter = inputdateFormatter.format(newCalendar.getTime());
                        File imagefile = new File(selectedImage.getPath());
                        RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
                        MultipartBody.Part partImage = MultipartBody.Part.createFormData("photo", imagefile.getName(), reqBody);
                        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
                        Call<Login_response<X0>> call = apiInterface.regitermodel(RequestBody.create(MediaType.parse("text/plain"), nama),
                                RequestBody.create(MediaType.parse("text/plain"), jk), RequestBody.create(MediaType.parse("text/plain"), t4_lhr),
                                RequestBody.create(MediaType.parse("text/plain"), dateFormatter), RequestBody.create(MediaType.parse("text/plain"), alamat),
                                RequestBody.create(MediaType.parse("text/plain"), notlpn), RequestBody.create(MediaType.parse("text/plain"), email),
                                RequestBody.create(MediaType.parse("text/plain"), pass), RequestBody.create(MediaType.parse("text/plain"), konPass), partImage);
                        call.enqueue(new Callback<Login_response<X0>>() {
                            @Override
                            public void onResponse(Call<Login_response<X0>> call, Response<Login_response<X0>> response) {
                                if (response.isSuccessful()) {
                                    Login_response login_response = response.body();
                                    Toast.makeText(Register.this, login_response.getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                } else if (!response.isSuccessful()) {
                                    try {
                                        Gson gson = new Gson();
                                        Login_response message = gson.fromJson(response.errorBody().string(), Login_response.class);
                                        Toast.makeText(Register.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<Login_response<X0>> call, Throwable t) {
                                Toast.makeText(Register.this, "" + t, Toast.LENGTH_SHORT).show();
                            }
                        });
//
//                        Toast.makeText(Register.this, "Nama : " + nama +
//                                "\nJenis Kelamin : " + jk + "\nTempat Lahir : " + t4_lhr +
//                                "\nTanggal Lahir : " + dateFormatter + "\nNo Telpon : " + notlpn +
//                                "\nEmail : " + email + "\nPassword : " + pass +
//                                "\nKonfirmasi Password : " + konPass, Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this, R.layout.textspinner, JKL) {
            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Jenis_Kelamin.setAdapter(adapter);
        Jenis_Kelamin.setSelection(adapter.getCount()); //set the hint the default selection so it appears on launch.
        Tgl_lhr.setText(tampildateFormatter.format(newCalendar.getTime()));
        Tgl_lhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }

        });

        fotoPofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Register.this)
                        .cropSquare()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        Alamat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.edittextalamat) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            selectedImage = data.getData();
            fotoPofil.setImageURI(data.getData());
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
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
                Tgl_lhr.setText(tampildateFormatter.format(newCalendar.getTime()));
                dateFormatter = inputdateFormatter.format(newCalendar.getTime());

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        //Tampilkan DatePicker dialog
        datePickerDialog.show();
    }

    public static boolean isEmailValid(EditText email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email.getText());
        return matcher.matches();
    }
}