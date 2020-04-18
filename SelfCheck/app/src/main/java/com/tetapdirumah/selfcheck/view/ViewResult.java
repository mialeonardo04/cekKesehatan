package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.progresviews.ProgressWheel;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractResult;
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.manager.LoadingManager;
import com.tetapdirumah.selfcheck.model.FormDiagnose;
import com.tetapdirumah.selfcheck.presenter.PresenterResult;

import java.util.ArrayList;

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
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.btn119)
    Button btn119;
    @BindView(R.id.sp_loading)
    ProgressBar spLoading;

    ContractResult.Presenter presenter;

    LoadingManager loadingManager;

    DataManager dataManager;
    IDataManager iDataManager;

    String covid, cold, flu;

    SharedPreferences pref;


    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);

        dataManager = new DataManager(this);
        iDataManager = new DataManagerWrapper(this);
        presenter = new PresenterResult(this, iDataManager, this);

        ButterKnife.bind(this);
        pref = getSharedPreferences("dataManager", 0);

        tvLink.setMovementMethod(LinkMovementMethod.getInstance());

        btn119.setVisibility(View.INVISIBLE);

        Sprite doubleBounce = new DoubleBounce();
        spLoading.setIndeterminateDrawable(doubleBounce);

        btnNext.setText("BAGIKAN");

        tvMessage.setVisibility(View.INVISIBLE);

        btnNext.setOnClickListener(v -> {
            shareTo();
        });

        loadingManager = new LoadingManager(this, spLoading, 0f, 100f);

        btnBack.setText("DIAGNOSA LAGI");

        btnBack.setOnClickListener(v -> {
            dataManager.clear();
            onBackPressed();
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoadingAnimation();
    }

    void shareTo(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "Hasil Diagnosa";
        String stringCovid = "Covid-19 : " + covid + "%";
        String stringFlu = "Flu : " + flu + "%";
        String stringCold = "Cold : " + cold + "%";
        String text = "Hasil diagnosa yang telah dilakukan" +
                "\n" + stringCovid +
                "\n" + stringFlu +
                "\n" + stringCold +
                "\nUnduh aplikasi di link: ";
        intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    @Override
    public void initializeData() {

        covid = pref.getString("covid", "");
        flu = pref.getString("flu","");
        cold = pref.getString("cold","");

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
        String koordinat = pref.getString("latitude","") + ", " + pref.getString("longitude", "");
        formDiagnose.set_nama(pref.getString("nama", ""));
        formDiagnose.set_telp(pref.getString("no_telp", ""));
        formDiagnose.set_usia(pref.getString("usia", ""));
        formDiagnose.set_kota(pref.getString("kota", ""));
        formDiagnose.set_kecataman(pref.getString("kecamatan", ""));
        formDiagnose.set_koordinat(koordinat);
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
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(s);
    }

    @Override
    public void btnShow() {
        btn119.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingAnimation() {
        animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                float value = 0f + (100f - 0f) * interpolatedTime;
                spLoading.setProgress((int)value);

                if (value == 100f){
                    spLoading.setVisibility(View.INVISIBLE);
                    presenter.postData();
                }
            }
        };
        animation.setDuration(1000);
        spLoading.setAnimation(animation);
    }

    @Override
    public void disposeLoadingAnimation() {
        spLoading.setVisibility(View.INVISIBLE);
    }

}
