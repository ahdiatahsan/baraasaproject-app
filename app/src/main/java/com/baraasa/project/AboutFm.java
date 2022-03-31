package com.baraasa.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Model.DataAbout;
import com.baraasa.project.Response.Login_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutFm extends Fragment {

    LocalStorage localStorage;
    Login_response login_response;
    TextView email, tlpn, ig, spotify, alamat;
    CardView cv_email, cv_tlpn, cv_ig, cv_spotify, cv_alamat;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarabout);
//        toolbar.setTitle("");
//        toolbar.setTitleTextAppearance(getActivity(), R.style.Toolbar);
//        toolbar.setNavigationIcon(R.drawable.info);

        email = view.findViewById(R.id.about_email);
        tlpn = view.findViewById(R.id.about_telepon);
        ig = view.findViewById(R.id.about_ig);
        spotify = view.findViewById(R.id.about_spotify);
        alamat = view.findViewById(R.id.about_alamat);

        cv_email = view.findViewById(R.id.cv_about_email);
        cv_tlpn = view.findViewById(R.id.cv_about_tlpn);
        cv_ig = view.findViewById(R.id.cv_about_ig);
        cv_spotify = view.findViewById(R.id.cv_about_spotify);
        cv_alamat = view.findViewById(R.id.cv_about_alamat);

        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataAbout> call= apiInterface.aboutmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataAbout>() {
            @Override
            public void onResponse(Call<DataAbout> call, Response<DataAbout> response) {
                DataAbout aboutModel = response.body();
                email.setText(aboutModel.getAbout().getEmail());
                cv_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent (Intent.ACTION_SEND);
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{aboutModel.getAbout().getEmail()});
                        intent.setPackage("com.google.android.gm");
                        if (intent.resolveActivity(getContext().getPackageManager())!=null) {
                            startActivity(intent);
                        }else{
                            Intent intent1 = new Intent(Intent.ACTION_VIEW);
                            intent1.setData(Uri.parse("https://mail.google.com/mail/?view=cm&source=mailto&to="+aboutModel.getAbout().getEmail()));
                            startActivity(intent1);
                        }
                    }
                });
                tlpn.setText(aboutModel.getAbout().getPhone());
                cv_ig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(aboutModel.getAbout().getInstagram()));
                        startActivity(intent);
                    }
                });
                cv_spotify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(aboutModel.getAbout().getSpotify()));
                        startActivity(intent);
                    }
                });
                alamat.setText(aboutModel.getAbout().getAddress());
            }

            @Override
            public void onFailure(Call<DataAbout> call, Throwable t) {
                Toast.makeText(getActivity(), (CharSequence) t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_fm, container, false);
    }
}