package com.tetapdirumah.selfcheck.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterTab extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragment = new ArrayList<>();
    private final List<String> mTitles = new ArrayList<>();

    public AdapterTab(FragmentManager fm){super (fm);}

    public void addFragment(Fragment fragment, String title){
        mFragment.add(fragment);
        mTitles.add(title);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }


    @Override
    public int getCount() {
        return mFragment.size();
    }
}
