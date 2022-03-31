package com.baraasa.project.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.baraasa.project.ViewPager.BlogSearch;
import com.baraasa.project.ViewPager.EventSearch;
import com.baraasa.project.ViewPager.ForumSearch;
import com.baraasa.project.ViewPager.JurnalSearch;

public class ViewPagerAdapterSearch extends FragmentPagerAdapter {

    private String mSearchTerm;


    public ViewPagerAdapterSearch(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new BlogSearch();
        } else if (position == 1) {
            fragment = new ForumSearch();
        } else if (position == 2) {
            fragment = new EventSearch();
        } else if (position == 3) {
            fragment = new JurnalSearch();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if (position == 0) {
            title = "Blog";
        } else if (position == 1) {
            title = "Forum";
        } else if (position == 2) {
            title = "Acara";
        } else if (position == 3) {
            title = "Jurnal";
        }
        return title;
    }

    public void setTextQueryChanged(String newText) {
        mSearchTerm = newText;
    }

}
