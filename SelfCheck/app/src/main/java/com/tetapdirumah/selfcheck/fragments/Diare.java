package com.tetapdirumah.selfcheck.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.tetapdirumah.selfcheck.contract.ContractSubmit;
import com.tetapdirumah.selfcheck.manager.ButtonManager;
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.presenter.PresenterBatukFragment;
import com.tetapdirumah.selfcheck.view.ViewForm;
import com.tetapdirumah.selfcheck.view.ViewResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Diare extends Fragment implements ContractFragmentForm.View, ContractSubmit.View {

    private static final String TAG = "Diare";

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

    String poin = "0";

    private ContractFragmentForm.Presenter presenter;
    private DataManager dataManager;
    private IDataManager iDataManager;

    SharedPreferences preferences;

    Intent intent;


    public Diare() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout-w480dp for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_batuk, container, false);
        ButterKnife.bind(this, fragmentView);
        btn0.callOnClick();

        return fragmentView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        dataManager = new DataManager(getActivity().getApplicationContext());
        iDataManager = new DataManagerWrapper(getActivity().getApplicationContext());
        presenter = new PresenterBatukFragment(this, iDataManager);
        intent = new Intent(getActivity().getApplicationContext(), ViewResult.class);

        preferences = getActivity().getApplicationContext().getSharedPreferences("dataManager", 0);

        btnNext.setText("Submit");

        imgFragment.setImageBitmap(null);
        Glide.with(this)
                .load(R.drawable.diare)
                .fitCenter()
                .into(imgFragment);

        btnNext.setOnClickListener(v -> {
            presenter.updateDiare();
            log();
            toActivityResult();
        });

        btnBack.setOnClickListener(v -> {
            Log.d(TAG, "onButtonBack: clicked" );
            ((ViewForm)getActivity()).changePage(8, true);
        });

        tvTitle.setText("Diare?");

        btn2.setText("Kadang");

        initializeDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
//        initializeDialog();
//        resetColor();
    }



    @Override
    public void initializeDialog() {

        btn0.setOnClickListener(v -> {
            poin = "0";
            resetColor();
            changeSelectedColor(btn0);
            presenter.updateBatuk();
            showAlertDialog();
        });

        btn1.setOnClickListener(v -> {
            poin = "1";
            resetColor();
            changeSelectedColor(btn1);
            presenter.updateBatuk();
            showAlertDialog();
        });

        btn2.setOnClickListener(v -> {
            poin = "2";
            resetColor();
            changeSelectedColor(btn2);
            presenter.updateBatuk();
            showAlertDialog();
        });

        btn3.setOnClickListener(v -> {
            poin = "3";
            resetColor();
            changeSelectedColor(btn3);
            presenter.updateBatuk();
            showAlertDialog();
        });

        btn4.setOnClickListener(v -> {
            poin = "4";
            resetColor();
            changeSelectedColor(btn4);
            presenter.updateBatuk();
            showAlertDialog();
        });
    }

    void changePage(int id, boolean value){
        ((ViewForm)getActivity()).changePage(id, value);
    }

    void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Lakukan diagnosa?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toActivityResult();
                    }
                })
                .setNegativeButton("Cek lagi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "cancel");
                        dialog.cancel();
                    }
                });
//        builder.show();
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialog1 -> {
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorBlack, getActivity().getTheme()));
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorBlack, getActivity().getTheme()));
        });
        dialog.setOnCancelListener(dialog1 -> {
            resetColor();
        });
        dialog.show();
    }

    void changeSelectedColor(Button button){
        button.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_color_accent, getActivity().getTheme()));
        Log.d(TAG, "changeSelectedColor: " + button.getText().toString());
    }

    void changeNotSelecetedColor(Button button){
        button.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_color_primary_dark, getActivity().getTheme()));
    }

    void resetColor(){
        Log.d(TAG, "resetColor");
        btn0.setText("Tidak");
        changeNotSelecetedColor(btn0);
        btn1.setText("Sedikit");
        changeNotSelecetedColor(btn1);
        btn2.setText("Kadang");
        changeNotSelecetedColor(btn2);
        btn3.setText("Sedang");
        changeNotSelecetedColor(btn3);
        btn4.setText("Selalu");
        changeNotSelecetedColor(btn4);
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
        Log.d(TAG, "Sesak: " + preferences.getString("sesak", ""));
        Log.d(TAG, "Kepala: " + preferences.getString("kepala", ""));
        Log.d(TAG, "Pegal: " + preferences.getString("pegal", ""));
        Log.d(TAG, "Bersin: " + preferences.getString("bersin", ""));
        Log.d(TAG, "Lelah: " + preferences.getString("lelah", ""));
        Log.d(TAG, "Diare: " + preferences.getString("diare", ""));
    }

    @Override
    public void toActivityResult() {
        startActivity(new Intent(getActivity().getApplicationContext(), ViewResult.class));
        getActivity().finish();
    }

}
