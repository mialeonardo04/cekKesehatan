package com.tetapdirumah.selfcheck.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rey.material.app.Dialog;
import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractFragmentForm;
import com.tetapdirumah.selfcheck.manager.ButtonManager;
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.presenter.PresenterBatukFragment;
import com.tetapdirumah.selfcheck.view.ViewForm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tenggorokan extends Fragment implements ContractFragmentForm.View{

    private static final String TAG = "Tenggorokan";

    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.fragment_tv_title)
    TextView tvTitle;
    @BindView(R.id.dialog_btn0)
    Button btn0;
    @BindView(R.id.dialog_btn1)
    Button btn1;
    @BindView(R.id.dialog_btn2)
    Button btn2;
    @BindView(R.id.dialog_btn3)
    Button btn3;
    @BindView(R.id.dialog_btn4)
    Button btn4;
    @BindView(R.id.img_fragment)
    ImageView imgFragment;

    Boolean next = false;
    String poin = "0";

    private ContractFragmentForm.Presenter presenter;
    private DataManager dataManager;
    private IDataManager iDataManager;

    ButtonManager buttonManager;
    SharedPreferences preferences;



    public Tenggorokan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout-w480dp for this fragment
        return inflater.inflate(R.layout.fragment_batuk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        dataManager = new DataManager(getActivity().getApplicationContext());
        iDataManager = new DataManagerWrapper(getActivity().getApplicationContext());
        presenter = new PresenterBatukFragment(this, iDataManager);

        preferences = getActivity().getApplicationContext().getSharedPreferences("dataManager", 0);

        btnNext.setOnClickListener(v -> {
            presenter.updateTenggorokan();
            ((ViewForm)getActivity()).changePage(4, true);
            log();
        });

        imgFragment.setImageBitmap(null);
        Glide.with(this)
                .load(R.drawable.sakittenggorokan)
                .fitCenter()
                .into(imgFragment);

        btnBack.setOnClickListener(v -> {
            Log.d(TAG, "onButtonBack: clicked" );
            ((ViewForm)getActivity()).changePage(2, true);
        });

        tvTitle.setText("Sakit tenggorokan?");
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeDialog();
    }



    @Override
    public void initializeDialog() {

        btn0.setOnClickListener(v -> {
            poin = "0";
            changeSelectedColor(btn0);
            changeNotSelecetedColor(btn1);
            changeNotSelecetedColor(btn2);
            changeNotSelecetedColor(btn3);
            changeNotSelecetedColor(btn4);
            presenter.updateTenggorokan();
            changePage(4, true);
        });

        btn1.setOnClickListener(v -> {
            poin = "1";
            changeSelectedColor(btn1);
            changeNotSelecetedColor(btn0);
            changeNotSelecetedColor(btn2);
            changeNotSelecetedColor(btn3);
            changeNotSelecetedColor(btn4);
            presenter.updateTenggorokan();
            changePage(4, true);
        });

        btn2.setOnClickListener(v -> {
            poin = "2";
            changeSelectedColor(btn2);
            changeNotSelecetedColor(btn1);
            changeNotSelecetedColor(btn0);
            changeNotSelecetedColor(btn3);
            changeNotSelecetedColor(btn4);
            presenter.updateTenggorokan();
            changePage(4, true);
        });

        btn3.setOnClickListener(v -> {
            poin = "3";
            changeSelectedColor(btn3);
            changeNotSelecetedColor(btn1);
            changeNotSelecetedColor(btn2);
            changeNotSelecetedColor(btn0);
            changeNotSelecetedColor(btn4);
            presenter.updateTenggorokan();
            changePage(4, true);
        });

        btn4.setOnClickListener(v -> {
            poin = "4";
            changeSelectedColor(btn4);
            changeNotSelecetedColor(btn1);
            changeNotSelecetedColor(btn2);
            changeNotSelecetedColor(btn3);
            changeNotSelecetedColor(btn0);
            presenter.updateTenggorokan();
            changePage(4, true);
        });
    }

    void changePage(int id, boolean value){
        ((ViewForm)getActivity()).changePage(id, value);
    }

    void changeSelectedColor(Button button){
        button.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_color_accent, getActivity().getTheme()));
        Log.d(TAG, "changeSelectedColor: " + button.getText().toString());
    }

    void changeNotSelecetedColor(Button button){
        button.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_color_primary_dark, getActivity().getTheme()));
    }

    @Override
    public void onItemSelected(String poin, String text) {
        this.poin = poin;
    }


    @Override
    public String store() {
        return poin;
    }

    @Override
    public void log() {
        Log.d(TAG, "Nama: " + preferences.getString("nama",""));
        Log.d(TAG, "Usia: " + preferences.getString("usia", ""));
        Log.d(TAG, "Kota: " + preferences.getString("kota",""));
        Log.d(TAG, "Kecamatan: " + preferences.getString("kecamatan", ""));
        Log.d(TAG, "Longitude: " + preferences.getString("longitude", ""));
        Log.d(TAG, "Latitude: " + preferences.getString("latitude", ""));
        Log.d(TAG, "Batuk: " + preferences.getString("batuk",""));
        Log.d(TAG, "Demam: " + preferences.getString("demam", ""));
        Log.d(TAG, "Hidung: " + preferences.getString("hidung", ""));
        Log.d(TAG, "Tenggorokan: " + preferences.getString("tenggorokan", ""));
    }
}
