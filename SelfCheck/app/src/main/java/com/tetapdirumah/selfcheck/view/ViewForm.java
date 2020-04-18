package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.adapter.AdapterTab;
import com.tetapdirumah.selfcheck.contract.ContractForm;
import com.tetapdirumah.selfcheck.fragments.Batuk;
import com.tetapdirumah.selfcheck.fragments.Bersin;
import com.tetapdirumah.selfcheck.fragments.Demam;
import com.tetapdirumah.selfcheck.fragments.Diare;
import com.tetapdirumah.selfcheck.fragments.Hidung;
import com.tetapdirumah.selfcheck.fragments.Kepala;
import com.tetapdirumah.selfcheck.fragments.Lelah;
import com.tetapdirumah.selfcheck.fragments.Pegal;
import com.tetapdirumah.selfcheck.fragments.Sesak;
import com.tetapdirumah.selfcheck.fragments.Tenggorokan;
import com.tetapdirumah.selfcheck.manager.DisableSwipeManager;
import com.tetapdirumah.selfcheck.presenter.PresenterForm;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewForm extends AppCompatActivity implements ContractForm.View {

    private static final String TAG = "ViewForm";

    private AdapterTab adapterTab;

    ContractForm.Presenter presenter;

    @BindView(R.id.layout_vp)
    DisableSwipeManager vp;
    @BindView(R.id.tab_layout)
    TabLayout tb;
    @BindView(R.id.tv_link)
    TextView tvLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_form);

        ButterKnife.bind(this);

        presenter = new PresenterForm(this);


        presenter.initialize();

        tvLink.setMovementMethod(LinkMovementMethod.getInstance());

        vp.setOnTouchListener((View v, MotionEvent event) -> {
            return true;
        });

        LinearLayout tabStrip = ((LinearLayout)tb.getChildAt(0));
        for (int i = 0; i <tabStrip.getChildCount(); i++){
            tabStrip.getChildAt(i).setOnTouchListener((v, event) -> {
                return true;
            });
        }

    }

    @Override
    public void initializeFragment() {
        adapterTab = new AdapterTab(getSupportFragmentManager());
        adapterTab.addFragment(new Batuk(), "Batuk Kering");
        adapterTab.addFragment(new Demam(), "Demam");
        adapterTab.addFragment(new Hidung(), "Hidung Meler");
        adapterTab.addFragment(new Tenggorokan(), "Tenggorokan Sakit");
        adapterTab.addFragment(new Sesak(), "Sesak Nafas");
        adapterTab.addFragment(new Kepala(), "Sakit Kepala");
        adapterTab.addFragment(new Pegal(), "Badan Pegal");
        adapterTab.addFragment(new Bersin(), "Bersin");
        adapterTab.addFragment(new Lelah(), "Lelah");
        adapterTab.addFragment(new Diare(), "Diare");
        vp.setAdapter(adapterTab);
        vp.setPagingEnabled(false);
        tb.setupWithViewPager(vp);
    }

    @Override
    public void changePage(int id, boolean smoothScroll) {
        vp.setCurrentItem(id, smoothScroll);
    }


}
