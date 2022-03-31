package com.baraasa.project.ViewPager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baraasa.project.API.Client;
import com.baraasa.project.Model.DataBlog;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Adapter.AdapterBlog;
import com.baraasa.project.Model.BlogModel;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Blog extends Fragment {

    AdapterBlog adapterBlog;
    RecyclerView rv_blog;
    LocalStorage localStorage;
    Login_response login_response;
    SwipeRefreshLayout srlData;
    ConstraintLayout datakosong, dataerror;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_blog, container, false);

        srlData = view.findViewById(R.id.swipe_refresh_blog);
        datakosong = view.findViewById(R.id.datablogkosong);
        dataerror = view.findViewById(R.id.datablogerror);
        datakosong.setVisibility(View.GONE);
        dataerror.setVisibility(View.GONE);
        rv_blog = view.findViewById(R.id.rv_blog);
        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        getDataBlog();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataBlog();
                dataerror.setVisibility(View.GONE);
            }
        });
        return view;
    }


    public void getDataBlog() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataBlog> call = apiInterface.blogmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA));
        call.enqueue(new Callback<DataBlog>() {
            @Override
            public void onResponse(Call<DataBlog> call, Response<DataBlog> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<BlogModel> blog = response.body().getBlog();
                    DataBlog dataBlog = response.body();
                    adapterBlog = new AdapterBlog(blog, getActivity());
                    adapterBlog.notifyDataSetChanged();
                    rv_blog.setVisibility(View.VISIBLE);
                    rv_blog.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_blog.setAdapter(adapterBlog);
                    if (dataBlog.getMessage().equals("Data kosong")){
                        datakosong.setVisibility(View.VISIBLE);
                        rv_blog.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<DataBlog> call, Throwable t) {
                srlData.setRefreshing(false);
                dataerror.setVisibility(View.VISIBLE);
            }
        });
    }

}