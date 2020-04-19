package com.tetapdirumah.selfcheck.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import com.rey.material.widget.SnackBar;
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
public class Batuk extends Fragment implements ContractFragmentForm.View {

    private static final String TAG = "Batuk";

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

    SharedPreferences preferences;

    private ContractFragmentForm.Presenter presenter;
    private DataManager dataManager;
    private IDataManager iDataManager;

    public Batuk() {
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
            presenter.updateBatuk();
            ((ViewForm)getActivity()).changePage(1, true);
            log();
        });

        img.setImageBitmap(null);
        Glide.with(this)
                .load(R.drawable.img_person)
                .fitCenter()
                .into(img);

        btnBack.setVisibility(View.INVISIBLE);

        tvTitle.setText("Batuk kering?");

        btnPilih.setOnClickListener(v -> {
            initializeDialog();
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

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

        btn2.setText("Kadang");
        btn2.setOnClickListener(v -> {
            onItemSelected("2", "Kadang");
            dialog.dismiss();
            next = true;
        });

        btn3.setText("Sering");
        btn3.setOnClickListener(v -> {
            onItemSelected("3", "Sering");
            dialog.dismiss();
            next = true;
        });

        btn4.setOnClickListener(v -> {
            onItemSelected("4", "Selalu");
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
    }
}
