package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.progresviews.ProgressWheel;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputEditText;
import com.rey.material.app.Dialog;
import com.rey.material.widget.Button;
import com.rey.material.widget.CheckBox;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractResult;
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.manager.LoadingManager;
import com.tetapdirumah.selfcheck.model.FormDiagnose;
import com.tetapdirumah.selfcheck.model.FormUpdate;
import com.tetapdirumah.selfcheck.presenter.PresenterResult;
import com.tetapdirumah.selfcheck.sqlite.DataDiagnosa;
import com.tetapdirumah.selfcheck.sqlite.HandlerDiagnosa;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewResult extends AppCompatActivity implements ContractResult.View {

    private static final String TAG = "ViewResult";

    @BindView(R.id.result_tv_message)
    TextView tvMessage;
    @BindView(R.id.btnLagi)
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
    @BindView(R.id.btnShare)
    Button btnShare;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.layout_form)
    LinearLayout layoutForm;
    @BindView(R.id.tv_form)
    TextView tvForm;
    @BindView(R.id.ck_setuju)
    CheckBox ckSetuju;
    @BindView(R.id.et_telp)
    TextInputEditText etTelp;
    @BindView(R.id.et_kota)
    TextInputEditText etKota;
    @BindView(R.id.et_usia)
    TextInputEditText etUsia;
    @BindView(R.id.et_kecamatan)
    TextInputEditText etKecamatan;

    ContractResult.Presenter presenter;

    HandlerDiagnosa db;

    String telp = "0";
    String kota = "0";
    String usia = "0";
    String kecamatan = "0";

    String id;

    LoadingManager loadingManager;

    DataManager dataManager;
    IDataManager iDataManager;

    String covid, cold, flu;

    SharedPreferences pref;

    String koordinat;
    String longitude, latitude;

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

        db = new HandlerDiagnosa(this);

        btn119.setVisibility(View.INVISIBLE);

        Sprite doubleBounce = new DoubleBounce();
        spLoading.setIndeterminateDrawable(doubleBounce);

        tvMessage.setVisibility(View.INVISIBLE);

        btnShare.setOnClickListener(v -> {
            shareTo();
        });

        loadingManager = new LoadingManager(this, spLoading, 0f, 100f);

        btnBack.setOnClickListener(v -> {
            dataManager.clear();
            onBackPressed();
            finish();
        });

        if (ckSetuju.isChecked()){
            onCheckTrue();
        } else {
            onCheckFalse();
        }

        ckSetuju.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                onCheckTrue();
            } else {
                onCheckFalse();
            }
        });

        btn119.setOnClickListener(v -> {
            showDialogContactCenter();
        });

        presenter.postData();

        btnSimpan.setOnClickListener(v -> {
            saveData(pref.getString("nama", ""), String.valueOf(covid), String.valueOf(flu), String.valueOf(cold), String.valueOf(new Date().getTime()));
            presenter.updateData();
        });

        getCurrentCoordinat();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ckSetuju.isChecked()){
            onCheckTrue();
        } else {
            onCheckFalse();
        }

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
                "\nUnduh aplikasi di link: https://tiny.cc/selfCheckerPTIKUNS";
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
    public void setId(String s) {
        this.id = s;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public FormDiagnose getDiagnose() {
        FormDiagnose formDiagnose = new FormDiagnose();
        String koordinat = pref.getString("latitude","") + ", " + pref.getString("longitude", "");
        formDiagnose.set_nama("guest");
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

    public String getCurrentCoordinat(){

        FusedLocationProviderClient mClient = LocationServices.getFusedLocationProviderClient(this);
        mClient.getLastLocation().addOnSuccessListener(location -> {
            Log.d(TAG, "Lat: " + location.getLatitude());
            Log.d(TAG, "Long: " + location.getLongitude());
            longitude = String.valueOf(location.getLongitude());
            latitude = String.valueOf(location.getLatitude());
            koordinat = location.getLongitude() + ", " + location.getLatitude();
        });
        return koordinat;
    }

    @Override
    public FormUpdate formUpadate() {
        FormUpdate formUpdate = new FormUpdate();
        if (etUsia.getText().toString().equals("")){
            usia = "0";
        } else {
            usia = etUsia.getText().toString();
        }

        if (etKota.getText().toString().equals("")){
            kota = "0";
        } else {
            kota = etKota.getText().toString();
        }

        if (etKecamatan.getText().toString().equals("")){
            kecamatan = "0";
        } else {
            kecamatan = etKecamatan.getText().toString();
        }

        if (etTelp.getText().toString().equals("")){
            telp = "0";
        } else {
            telp = etTelp.getText().toString();
        }
        formUpdate.set_nama(pref.getString("nama",""));
        formUpdate.set_telp(telp);
        formUpdate.set_usia(usia);
        formUpdate.set_kota(kota);
        formUpdate.set_kecamatan(kecamatan);
        formUpdate.set_koordinat(getCurrentCoordinat());
        return formUpdate;
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
    public void goToHistory() {
        startActivity(new Intent(this, ViewRiwayat.class));
        finish();
    }

    public void showDialogContactCenter(){
        Dialog dialog = new Dialog(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.call_center_layout, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.closeOptionsMenu();
        dialog.contentMargin(20);
        dialog.show();

        Button btn119 = view.findViewById(R.id.btnNasional);
        Button btnJak = view.findViewById(R.id.btnJakarta);
        Button btnJbr = view.findViewById(R.id.btnJabar);
        Button btnJtg = view.findViewById(R.id.btnJateng);
        Button btnJtm = view.findViewById(R.id.btnJatim);
        Button btnYgy = view.findViewById(R.id.btnYogya);
        Button btnBtn = view.findViewById(R.id.btnBanten);

        callWilayah("tel: 119", btn119);
        callWilayah("tel: 112", btnJak);
        callWilayah("tel: 08112093306", btnJbr);
        callWilayah("tel: 0243580713", btnJtg);
        callWilayah("tel: 1500117", btnJtm);
        callWilayah("tel: 0274555585", btnYgy);
        callWilayah("tel: 085215779659", btnBtn);
    }

    void callWilayah(String s, Button button){
        button.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(s));
            startActivity(callIntent);
        });
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

    @Override
    public void saveData(String nama, String covid, String flu, String cold, String date_created) {
        DataDiagnosa dataDiagnosa = new DataDiagnosa();
        dataDiagnosa.set_nama(nama);
        dataDiagnosa.set_covid(covid);
        dataDiagnosa.set_flu(flu);
        dataDiagnosa.set_cold(cold);
        dataDiagnosa.set_date_created(date_created);

        db.addData(dataDiagnosa);

        Log.d(TAG, "saveData: data saved");
    }

    void onCheckTrue(){
        layoutForm.setVisibility(View.VISIBLE);
        tvForm.setVisibility(View.INVISIBLE);
    }

    void onCheckFalse(){
        layoutForm.setVisibility(View.INVISIBLE);
        tvForm.setVisibility(View.VISIBLE);
    }

}
