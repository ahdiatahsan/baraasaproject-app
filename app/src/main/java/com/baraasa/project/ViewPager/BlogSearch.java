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
import android.widget.EditText;
import android.widget.ImageView;

import com.baraasa.project.API.Client;
import com.baraasa.project.Adapter.AdapterBlogSearch;
import com.baraasa.project.Model.BlogModelSearch;
import com.baraasa.project.Model.DataBlog;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.API.ApiInterface;
import com.baraasa.project.Adapter.AdapterBlog;
import com.baraasa.project.Model.BlogModel;
import com.baraasa.project.Model.DataSearch;
import com.baraasa.project.R;
import com.baraasa.project.Response.Login_response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogSearch extends Fragment {

    AdapterBlogSearch adapterBlogSearch;
    AdapterBlog adapterBlogs;
    RecyclerView rv_blog;
    LocalStorage localStorage;
    Login_response login_response;
    SwipeRefreshLayout srlData;
    ConstraintLayout datakosong, dataerror;
    EditText pencarian;
    ImageView cari;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        srlData = view.findViewById(R.id.swipe_refresh_blogs);
        datakosong = view.findViewById(R.id.datablogkosong);
        dataerror = view.findViewById(R.id.datablogerror);
        datakosong.setVisibility(View.GONE);
        dataerror.setVisibility(View.GONE);
        pencarian = view.findViewById(R.id.cariblog);
        cari = view.findViewById(R.id.bt_cariblog);
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
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataBlogSearch();
                pencarian.setText("");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_search, container, false);
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
                    adapterBlogs = new AdapterBlog(blog, getActivity());
                    adapterBlogs.notifyDataSetChanged();
                    rv_blog.setVisibility(View.VISIBLE);
                    rv_blog.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_blog.setAdapter(adapterBlogs);
                    if (dataBlog.getMessage().equals("Data kosong")) {
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

    public void getDataBlogSearch() {
        srlData.setRefreshing(true);
        ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
        Call<DataSearch> call = apiInterface.searchmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA),
                pencarian.getText().toString());
        call.enqueue(new Callback<DataSearch>() {
            @Override
            public void onResponse(Call<DataSearch> call, Response<DataSearch> response) {
                srlData.setRefreshing(false);
                if (response.isSuccessful()) {
                    ArrayList<BlogModelSearch> blog = response.body().getBlog();
                    DataSearch dataBlog = response.body();
                    adapterBlogSearch = new AdapterBlogSearch(blog, getActivity());
                    adapterBlogSearch.notifyDataSetChanged();
                    rv_blog.setVisibility(View.VISIBLE);
                    rv_blog.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                    rv_blog.setAdapter(adapterBlogSearch);
                    if (dataBlog.getMessage().equals("Data Kosong")) {
                        datakosong.setVisibility(View.VISIBLE);
                        rv_blog.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<DataSearch> call, Throwable t) {
                srlData.setRefreshing(false);
                dataerror.setVisibility(View.VISIBLE);
            }
        });
    }

}