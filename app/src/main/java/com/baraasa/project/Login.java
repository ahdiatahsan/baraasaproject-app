package com.baraasa.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.baraasa.project.API.Client;
import com.baraasa.project.API.Server;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.Response.X0;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;
import com.google.gson.Gson;
//import SessionManager;
//import SessionManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    boolean doubleBack = false;
    Button Masuk;
    CheckBox ShowPass;
    EditText Email, Password;
    TextView Daftar, LupaPass;
    Client client;
    ConstraintLayout servererror;
    ScrollView login;
    ApiInterface apiInterface;
    LocalStorage localStorage;
    String email, password;
    Context context;

    @Override

    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }
        this.doubleBack = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack = false;
            }
        }, 2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

//        servererror = findViewById(R.id.servererror);
        login = findViewById(R.id.login);
        Masuk = findViewById(R.id.masuk);
        Daftar = findViewById(R.id.daftar);
        LupaPass = findViewById(R.id.lupapass);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.pass);
        ShowPass = findViewById(R.id.showPass);
        client = new Client();
        localStorage = new LocalStorage(this);
        apiInterface = Client.getClient().create(ApiInterface.class);

        if (localStorage.getStringValue(LocalStorage.TOKEN_BARA) != null) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        ShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShowPass.isChecked()) {
                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                    Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //Jika tidak, maka password akan di sembuyikan
                    Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        Masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email.getText().toString().trim();
                password = Password.getText().toString().trim();
                if (email.isEmpty() && password.isEmpty()) {
                    Email.setError("Data Kosong");
                    Password.setError("Data Kosong");
                } else if (email.isEmpty()) {
                    Email.setError("Data Kosong");
                } else if (password.isEmpty()) {
                    Password.setError("Data Kosong");
                } else {
                    apiInterface.login(email, password).enqueue(new Callback<Login_response<X0>>() {
                        @Override
                        public void onResponse(Call<Login_response<X0>> call, Response<Login_response<X0>> response) {
                            if (response.isSuccessful()) {
                                Login_response login_response = response.body();
//                                if (login_response.getMessage().equals("Password Anda salah.")){
//                                    Toast.makeText(Login.this, login_response.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
                                ProgressDialog progressDialog = new ProgressDialog(Login.this);
                                progressDialog.show();
                                progressDialog.setContentView(R.layout.dialog_loader);
                                progressDialog.setCancelable(false);
                                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        localStorage.save(LocalStorage.TOKEN_BARA, login_response.getX0().getToken());
                                        localStorage.save(LocalStorage.ID, login_response.getX0().getUser().getId());
                                        localStorage.save(LocalStorage.NAME, login_response.getX0().getUser().getName());
                                        localStorage.save(LocalStorage.GENDER, login_response.getX0().getUser().getGender());
                                        localStorage.save(LocalStorage.BIRDTHPLEACE, login_response.getX0().getUser().getBirthplace());
                                        localStorage.save(LocalStorage.DATE_OF_BIRTH, login_response.getX0().getUser().getDate_of_birth());
                                        localStorage.save(LocalStorage.ADDRESS, login_response.getX0().getUser().getAddress());
                                        localStorage.save(LocalStorage.PHONE_NUMBER, login_response.getX0().getUser().getPhone_number());
                                        localStorage.save(LocalStorage.EMAIL, login_response.getX0().getUser().getEmail());
                                        localStorage.save(LocalStorage.ROLE, login_response.getX0().getRole());
                                        localStorage.save(LocalStorage.FOTO, Server.URL_USER + login_response.getX0().getUser().getPhoto());
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 3000);

                            } else if (!response.isSuccessful()) {
                                try {
                                    Gson gson = new Gson();
                                    Login_response message = gson.fromJson(response.errorBody().string(), Login_response.class);
                                    Toast.makeText(Login.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<Login_response<X0>> call, Throwable t) {

                            Toast.makeText(Login.this, "" + t, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        LupaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse(Server.URL_LUPAPASS));
                startActivity(intent1);
            }
        });
    }

}