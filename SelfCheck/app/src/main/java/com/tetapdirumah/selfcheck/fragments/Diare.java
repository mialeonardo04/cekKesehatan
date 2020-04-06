package com.tetapdirumah.selfcheck.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tetapdirumah.selfcheck.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Diare extends Fragment {


    public Diare() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diare, container, false);
    }

}
