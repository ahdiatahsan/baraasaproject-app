package com.baraasa.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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

public class About extends AppCompatActivity {

    LocalStorage localStorage;
    Login_response login_response;
    TextView email, tlpn, ig, spotify, alamat;
    CardView cv_email, cv_tlpn, cv_ig, cv_spotify, cv_alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarabout);
        toolbar.setTitle("Tentang");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        email = findViewById(R.id.about_email);
        tlpn = findViewById(R.id.about_telepon);
        ig = findViewById(R.id.about_ig);
        spotify = findViewById(R.id.about_spotify);
        alamat = findViewById(R.id.about_alamat);

        cv_email = findViewById(R.id.cv_about_email);
        cv_tlpn = findViewById(R.id.cv_about_tlpn);
        cv_ig = findViewById(R.id.cv_about_ig);
        cv_spotify = findViewById(R.id.cv_about_spotify);
        cv_alamat = findViewById(R.id.cv_about_alamat);

        localStorage = new LocalStorage(About.this);
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
                        if (intent.resolveActivity(getPackageManager())!=null) {
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
                Toast.makeText(About.this, (CharSequence) t, Toast.LENGTH_SHORT).show();
            }
        });



    }
}