package com.baraasa.project.Menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baraasa.project.API.Client;
import com.baraasa.project.Forum.Forum_Saya;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.Response.LogOut;
import com.baraasa.project.Sertifikat.Sertifikat;
import com.bumptech.glide.Glide;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Blog.Blog_Saya;
import com.baraasa.project.Login;
import com.baraasa.project.Profil.EditPassword;
import com.baraasa.project.Profil.EditProfil;
import com.baraasa.project.Response.Login_response;
import com.baraasa.project.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Profile_Menu extends Fragment {

    Toolbar toolbar;
    ImageView foto_profile;
    TextView Nama, Role;
    Button keluar;
    Client client;
    Context context;
    Login_response login_response;
    ApiInterface apiInterface;
    CardView ubahprofil, ubahpass, ubahfoto, blogsaya, forumsaya, sertifikatsaya;
    ProgressDialog progressDialog;
    LocalStorage localStorage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        toolbar = (Toolbar) view.findViewById(R.id.toolbarprofile);
//        toolbar.inflateMenu(R.menu.about);
        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        apiInterface = Client.getClient().create(ApiInterface.class);
        Nama = view.findViewById(R.id.profile_name);
        Role = view.findViewById(R.id.profile_role);
        foto_profile = view.findViewById(R.id.foto_profile);
        Nama.setText(localStorage.getStringValue(LocalStorage.NAME));
        Role.setText(localStorage.getStringValue(LocalStorage.ROLE));
        Glide.with(this).load(localStorage.getStringValue(LocalStorage.FOTO)).into(foto_profile);

//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == R.id.action_info) {
//                    Intent intent = new Intent(view.getContext(), About.class);
//                    getContext().startActivity(intent);
//                }
//                return false;
//            }
//        });

        ubahprofil = view.findViewById(R.id.ubahprofil);
        ubahprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditProfil.class);
                v.getContext().startActivity(intent);
            }
        });
        ubahpass = view.findViewById(R.id.ubahpass);
        ubahpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditPassword.class);
                v.getContext().startActivity(intent);
            }
        });

        blogsaya = view.findViewById(R.id.blogsaya);
        blogsaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Blog_Saya.class);
                v.getContext().startActivity(intent);

            }
        });
        forumsaya = view.findViewById(R.id.forumsaya);
        forumsaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Forum_Saya.class);
                v.getContext().startActivity(intent);
            }
        });
        sertifikatsaya = view.findViewById(R.id.sertifikatsaya);
        sertifikatsaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Sertifikat.class);
                v.getContext().startActivity(intent);
            }
        });

        keluar = view.findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.logout("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA)).enqueue(new Callback<LogOut>() {
                    @Override
                    public void onResponse(Call<LogOut> call, Response<LogOut> response) {
                        if (response.isSuccessful()) {
                            LogOut logOut = response.body();
                            localStorage.removeValue(LocalStorage.TOKEN_BARA);
//                            localStorage.removeValue(LocalStorage.NAma);
                            progressDialog = new ProgressDialog(v.getContext());
                            progressDialog.show();
                            progressDialog.setContentView(R.layout.dialog_loader);
                            progressDialog.setCancelable(false);
                            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(v.getContext(), Login.class);
                                    v.getContext().startActivity(intent);
                                    getActivity().finish();
                                }
                            }, 2000);
                        }

                    }

                    @Override
                    public void onFailure(Call<LogOut> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Nama.setText(localStorage.getStringValue(LocalStorage.NAME));
        Role.setText(localStorage.getStringValue(LocalStorage.ROLE));
        Glide.with(this).load(localStorage.getStringValue(LocalStorage.FOTO)).into(foto_profile);
    }
}