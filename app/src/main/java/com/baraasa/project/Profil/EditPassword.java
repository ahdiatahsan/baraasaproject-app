package com.baraasa.project.Profil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataPassword;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPassword extends AppCompatActivity {

    Toolbar toolbar;
    EditText EditPass, EditKonPass;
    String etpass, etpassconfir;
    ImageButton img_ceklis;
    LocalStorage localStorage;
    Login_response login_response;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass);
        getSupportActionBar().hide();

        localStorage = new LocalStorage(this);
        login_response = new Login_response();
        apiInterface = Client.getClient().create(ApiInterface.class);

        toolbar = (Toolbar) findViewById(R.id.toolbarEditpass);
        EditPass = findViewById(R.id.editpass);
        EditKonPass = findViewById(R.id.editpasskonfir);

        toolbar.setTitle("Ubah Password");
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
                    etpass = EditPass.getText().toString();
                    etpassconfir = EditKonPass.getText().toString();
                    if (etpass.isEmpty() && etpassconfir.isEmpty()) {
                        EditPass.setError("Data Kosong");
                        EditKonPass.setError("Data Kosong");
                    } else if (etpass.isEmpty()) {
                        EditPass.setError("Data Kosong");
                    } else if (etpass.length() < 8) {
                        EditPass.setError("Minimal Password 8 digit");
                    } else if (!etpassconfir.equals(etpass)) {
                        EditKonPass.setError("Sesusaikan Dengan Password");
                    }
                    else {
//                        Toast.makeText(EditPassword.this, etpass+etpassconfir, Toast.LENGTH_SHORT).show();
                        Call<DataPassword> call = apiInterface.passwordmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA), etpass, etpassconfir);
                        call.enqueue(new Callback<DataPassword>() {
                            @Override
                            public void onResponse(Call<DataPassword> call, Response<DataPassword> response) {
                                if (response.isSuccessful()) {
                                    DataPassword dataPassword = response.body();
                                    Toast.makeText(EditPassword.this, dataPassword.getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
//                                else if (!response.isSuccessful()) {
//                                    try {
//                                        Gson gson = new Gson();
//                                        DataPassword message = gson.fromJson(response.errorBody().string(), DataPassword.class);
//                                        Toast.makeText(EditPassword.this, message.getMessage(), Toast.LENGTH_SHORT).show();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                }
                            }

                            @Override
                            public void onFailure(Call<DataPassword> call, Throwable t) {
                                Toast.makeText(EditPassword.this, "" + t, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
                return false;
            }
        });


    }
}