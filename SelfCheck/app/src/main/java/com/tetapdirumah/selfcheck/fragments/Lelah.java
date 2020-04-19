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
public class Lelah extends Fragment implements ContractFragmentForm.View{
    private static final String TAG = "Lelah";

    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.batuk_btnPilih)
    Button btnPilih;
    @BindView(R.id.fragment_tv_title)
    TextView tvTitle;
    @BindView(R.id.img_fragment)
    ImageView img;

    Button btn0, btn1, btn2, btn3, btn4;
    Boolean next = false;
    String poin = "0";

    private ContractFragmentForm.Presenter presenter;
    private DataManager dataManager;
    private IDataManager iDataManager;

    SharedPreferences preferences;


    public Lelah() {
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
            presenter.updateLelah();
            ((ViewForm)getActivity()).changePage(9, true);
            log();
        });

        img.setImageBitmap(null);
        Glide.with(this)
                .load(R.drawable.img_person)
                .fitCenter()
                .into(img);

        btnBack.setOnClickListener(v -> {
            Log.d(TAG, "onButtonBack: clicked" );
            ((ViewForm)getActivity()).changePage(7, true);
        });

        tvTitle.setText("Kelelahan?");

        btnPilih.setOnClickListener(v -> {
            initializeDialog();
        });
    }

    @Override
    public void initializeDialog() {
        Dialog dialog = new Dialog(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_form, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setTitle("Pilih salah satu");
        dialog.cornerRadius(5);
        dialog.showDivider(true);
        dialog.backgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getActivity().getTheme()));
        dialog.titleColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getActivity().getTheme()));
        dialog.contentMargin(20, 20, 20, 20);

        btn0 = (Button) view.findViewById(R.id.dialog_btn0);
        btn1 = (Button) view.findViewById(R.id.dialog_btn1);
        btn2 = (Button) view.findViewById(R.id.dialog_btn2);
        btn3 = (Button) view.findViewById(R.id.dialog_btn3);
        btn4 = (Button) view.findViewById(R.id.dialog_btn4);

        btn0.setOnClickListener(v -> {
            onItemSelected("0", "Tidak");
            dialog.dismiss();
            next = true;
        });

        btn1.setOnClickListener(v -> {
            onItemSelected("1", "Sedikit");
            dialog.dismiss();
            next = true;
        });

        btn2.setOnClickListener(v -> {
            onItemSelected("2", "Cukup");
            dialog.dismiss();
            next = true;
        });

        btn3.setOnClickListener(v -> {
            onItemSelected("3", "Sedang");
            dialog.dismiss();
            next = true;
        });

        btn4.setText("Berat");
        btn4.setOnClickListener(v -> {
            onItemSelected("4", "Berat");
            dialog.dismiss();
            next = true;
        });

        dialog.show();
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
        Log.d(TAG, "Lelah: " + preferences.getString("lelah", ""));
    }
}
