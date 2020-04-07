package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.progresviews.ProgressWheel;
import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractResult;
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.model.FormDiagnose;
import com.tetapdirumah.selfcheck.presenter.PresenterResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewResult extends AppCompatActivity implements ContractResult.View {

    private static final String TAG = "ViewResult";

    @BindView(R.id.result_tv_message)
    TextView tvMessage;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.result_pw_covid)
    ProgressWheel pwCovid;
    @BindView(R.id.result_pw_flu)
    ProgressWheel pwFlu;
    @BindView(R.id.result_pw_cold)
    ProgressWheel pwCold;

    ContractResult.Presenter presenter;

    DataManager dataManager;
    IDataManager iDataManager;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);

        dataManager = new DataManager(this);
        iDataManager = new DataManagerWrapper(this);
        presenter = new PresenterResult(iDataManager, this);

        ButterKnife.bind(this);
        pref = getSharedPreferences("dataManager", 0);

        btnNext.setVisibility(View.INVISIBLE);

        btnBack.setText("DIAGNOSA LAGI");

        presenter.postData();

        btnBack.setOnClickListener(v -> {
            dataManager.clear();
            startActivity(new Intent(this, ViewForm.class));
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initializeData();
    }

    @Override
    public void initializeData() {

        String covid = pref.getString("covid", "");
        String flu = pref.getString("flu","");
        String cold = pref.getString("cold","");

        float percCovid = (Float.parseFloat(covid) * 360) / 100;
        float percFlu = (Float.parseFloat(flu) * 360) / 100;
        float percCold = (Float.parseFloat(cold) * 360) / 100;

        pwCovid.setStepCountText(covid + "%");
        pwFlu.setStepCountText(flu + "%");
        pwCold.setStepCountText(cold + "%");

        pwCovid.setPercentage((int)percCovid);
        pwFlu.setPercentage((int)percFlu);
        pwCold.setPercentage((int)percCold);
    }

    @Override
    public FormDiagnose getDiagnose() {
        FormDiagnose formDiagnose = new FormDiagnose();
        formDiagnose.set_nama(pref.getString("nama", ""));
        formDiagnose.set_kota(pref.getString("kota", ""));
        formDiagnose.set_batuk(Integer.parseInt(pref.getString("batuk", "")));
        formDiagnose.set_demam(Integer.parseInt(pref.getString("demam", "")));
        formDiagnose.set_hidung(Integer.parseInt(pref.getString("hidung", "")));
        formDiagnose.set_tenggorokan(Integer.parseInt(pref.getString("tenggorokan", "")));
        formDiagnose.set_kepala(Integer.parseInt(pref.getString("kepala", "")));
        formDiagnose.set_pegal(Integer.parseInt(pref.getString("pegal", "")));
        formDiagnose.set_sesak(Integer.parseInt(pref.getString("sesak", "")));
        formDiagnose.set_bersin(Integer.parseInt(pref.getString("bersin", "")));
        formDiagnose.set_lelah(Integer.parseInt(pref.getString("lelah", "")));
        formDiagnose.set_diare(Integer.parseInt(pref.getString("diare", "")));
        return formDiagnose;
    }

    @Override
    public void showMessage(String s) {
        tvMessage.setText(s);
    }
}
