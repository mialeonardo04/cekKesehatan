package com.tetapdirumah.selfcheck.fragments;


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
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.presenter.PresenterBatukFragment;
import com.tetapdirumah.selfcheck.view.ViewForm;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bersin extends Fragment implements ContractFragmentForm.View{

    private static final String TAG = "Bersin";

    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.batuk_btnPilih)
    TextView btnPilih;
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

    Boolean next = false;
    String poin = "0";

    private ContractFragmentForm.Presenter presenter;
    private DataManager dataManager;
    private IDataManager iDataManager;

    SharedPreferences preferences;


    public Bersin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
            presenter.updateBersin();
            ((ViewForm)getActivity()).changePage(8, true);
            log();
        });


        btnBack.setOnClickListener(v -> {
            Log.d(TAG, "onButtonBack: clicked" );
            ((ViewForm)getActivity()).changePage(6, true);
        });

        tvTitle.setText("Bersin?");

        initializeDialog();
    }

    @Override
    public void initializeDialog() {

        btn0.setOnClickListener(v -> {
            onItemSelected("0", "Tidak");
            next = true;
        });

        btn1.setOnClickListener(v -> {
            onItemSelected("1", "Sedikit");
            next = true;
        });

        btn2.setOnClickListener(v -> {
            onItemSelected("2", "Cukup");
            next = true;
        });

        btn3.setOnClickListener(v -> {
            onItemSelected("3", "Sedang");
            next = true;
        });

        btn4.setText("Berat");
        btn4.setOnClickListener(v -> {
            onItemSelected("4", "Berat");
            next = true;
        });
    }

    @Override
    public void onItemSelected(String poin, String text) {
        this.poin = poin;
        btnPilih.setText(text);
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
        Log.d(TAG, "Sesak: " + preferences.getString("sesak", ""));
        Log.d(TAG, "Kepala: " + preferences.getString("kepala", ""));
        Log.d(TAG, "Pegal: " + preferences.getString("pegal", ""));
        Log.d(TAG, "Bersin: " + preferences.getString("bersin", ""));
    }
}
