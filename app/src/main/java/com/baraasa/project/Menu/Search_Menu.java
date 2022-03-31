package com.baraasa.project.Menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.baraasa.project.Adapter.AdapterBlogSearch;
import com.baraasa.project.Response.LocalStorage;
import com.baraasa.project.ViewPagerAdapter.ViewPagerAdapterSearch;
import com.baraasa.project.Adapter.AdapterForum;
import com.baraasa.project.Response.Login_response;
import com.baraasa.project.R;
import com.google.android.material.tabs.TabLayout;

public class Search_Menu extends Fragment {

    AdapterBlogSearch adapterBlog;
    AdapterForum adapterForum;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabhome;
    EditText pencarian;
    Button cari, cariForum;
    LocalStorage localStorage;
    Login_response login_response;
    ViewPagerAdapterSearch viewPagerAdapterSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        localStorage = new LocalStorage(getActivity());
        login_response = new Login_response();
        toolbar = (Toolbar) view.findViewById(R.id.toolbarsearch);
        viewPager = view.findViewById(R.id.viewPagerSearch);
        tabhome = view.findViewById(R.id.tabsearch);
        viewPagerAdapterSearch = new ViewPagerAdapterSearch(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapterSearch);
        tabhome.setupWithViewPager(viewPager, true);

//        cari.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ApiInterface apiInterface = Client.getClient().create(ApiInterface.class);
//                Call<DataSearch> call = apiInterface.searchmodel("Bearer " + localStorage.getStringValue(LocalStorage.TOKEN_BARA),pencarian.getText().toString());
//                call.enqueue(new Callback<DataSearch>() {
//                    @Override
//                    public void onResponse(Call<DataSearch> call, Response<DataSearch> response) {
//                        if (response.isSuccessful()){
//                            ArrayList<BlogModelSearch> blog = response.body().getBlog();
//                            adapterBlog = new AdapterBlogSearch(blog, getActivity());
//                            adapterBlog.getFilter().filter(pencarian.getText().toString());
//                            ArrayList<ThreadModel> thread = response.body().getThread();
//                            adapterForum = new AdapterForum(thread, getActivity());
//                            adapterForum.getFilter().filter(pencarian.getText().toString());
////                            ArrayList<EventModel> event = response.body().getEvent();
////                            ArrayList<ResearchModel> research = response.body().getResearch();
//                            DataSearch dataSearch = response.body();
//                            Toast.makeText(getActivity(), dataSearch.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<DataSearch> call, Throwable t) {
//
//                    }
//                });
//            }
//        });

    }
}