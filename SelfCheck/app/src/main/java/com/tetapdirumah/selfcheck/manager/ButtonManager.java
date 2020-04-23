package com.tetapdirumah.selfcheck.manager;

import android.content.Context;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractFragmentForm;
import com.tetapdirumah.selfcheck.presenter.PresenterBatukFragment;
import com.tetapdirumah.selfcheck.view.ViewForm;

import java.util.List;

public class ButtonManager {

    Fragment fragment;
    List<Button> mButton;
    String poin;
    int i;
    boolean value;
    ContractFragmentForm.Presenter presenter;
    IDataManager iDataManager;
    ContractFragmentForm.View view;


    public ButtonManager(Fragment fragment, ContractFragmentForm.View view ,List<Button> mButton, String poin, int i, boolean value) {
        this.view = view;
        this.fragment = fragment;
        this.mButton = mButton;
        this.poin = poin;
        this.i = i;
        this.value = value;
        this.iDataManager = new DataManagerWrapper(fragment.getContext());
        this.presenter = new PresenterBatukFragment(view, this.iDataManager);
    }

    public void submitChangeColor(){
        mButton.get(0).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "0";
        });

        mButton.get(1).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "1";
        });

        mButton.get(2).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "2";
        });

        mButton.get(3).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "3";
        });

        mButton.get(4).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(4));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(3));
            this.poin = "4";
        });
    }

    public void buttonChangeColor(){
        mButton.get(0).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "0";
            changePage(this.i, this.value);
        });

        mButton.get(1).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "1";
            changePage(this.i, this.value);
        });

        mButton.get(2).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "2";
            changePage(this.i, this.value);
        });

        mButton.get(3).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(3));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(4));
            this.poin = "3";
            changePage(this.i, this.value);
        });

        mButton.get(4).setOnClickListener(v -> {
            changeSelectedColor(mButton.get(4));
            changeNotSelecetedColor(mButton.get(0));
            changeNotSelecetedColor(mButton.get(1));
            changeNotSelecetedColor(mButton.get(2));
            changeNotSelecetedColor(mButton.get(3));
            this.poin = "4";
            changePage(this.i, this.value);
        });
    }

    void changePage(int id, boolean value){
        ((ViewForm)fragment.getActivity()).changePage(id, value);
    }

    void changeSelectedColor(Button button){
        button.setBackgroundColor(ResourcesCompat.getColor(fragment.getResources(), R.color.colorAccent, fragment.getActivity().getTheme()));
    }

    void changeNotSelecetedColor(Button button){
        button.setBackgroundColor(ResourcesCompat.getColor(fragment.getResources(), R.color.colorPrimaryDark, fragment.getActivity().getTheme()));
    }
}
