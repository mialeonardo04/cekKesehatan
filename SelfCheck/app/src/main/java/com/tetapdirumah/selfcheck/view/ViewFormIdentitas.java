package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputEditText;
import com.rey.material.widget.Button;
import com.rey.material.widget.TextView;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractFormIdentitas;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewFormIdentitas extends AppCompatActivity implements ContractFormIdentitas.View {
    private static final String TAG = "ViewFormIdentitas";

    @BindView(R.id.form_btnStart)
    Button btnStart;
    @BindView(R.id.et_nama)
    TextInputEditText etNama;
    @BindView(R.id.et_telp)
    TextInputEditText etTelp;
    @BindView(R.id.et_kota)
    TextInputEditText etKota;
    @BindView(R.id.et_usia)
    TextInputEditText etUsia;
    @BindView(R.id.et_kecamatan)
    TextInputEditText etKecamatan;
    @BindView(R.id.et_koordinat)
    TextView etKoordinat;
    @BindView(R.id.btnPlus)
    Button btnPlus;
    @BindView(R.id.tv_link)
    android.widget.TextView tvLink;

    IDataManager iDataManager;

    String longitude = "0";
    String latitude = "0";
    String kota = "0";
    String kecamatan = "0";
    String usia = "0";
    String telp = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_form_identitas);

        ButterKnife.bind(this);

        iDataManager = new DataManagerWrapper(getApplicationContext());

        tvLink.setMovementMethod(LinkMovementMethod.getInstance());

        btnStart.setOnClickListener(v -> {
            if (!etNama.getText().toString().equals("")){
                updateSession();
                nextPage();
                finish();
            } else {
                showToast("Nama tidak boleh kosong");
            }
        });

        btnPlus.setOnClickListener(v -> {
            getLocation();
        });
    }

    void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextPage() {
        Intent intent = new Intent(this, ViewForm.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getLocation() {
        FusedLocationProviderClient mClient = LocationServices.getFusedLocationProviderClient(this);
        mClient.getLastLocation().addOnSuccessListener(location -> {
            Log.d(TAG, "Lat: " + location.getLatitude());
            Log.d(TAG, "Long: " + location.getLongitude());
            longitude = String.valueOf(location.getLongitude());
            latitude = String.valueOf(location.getLatitude());
            etKoordinat.setText(location.getLongitude() + ", " + location.getLatitude());
        });
    }

    public void updateSession(){
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
        iDataManager.updateNama(etNama.getText().toString());
        iDataManager.updateTelp(telp);
        iDataManager.updateUsia(usia);
        iDataManager.updateKota(kota);
        iDataManager.updateKecamatan(kecamatan);
        iDataManager.updateLongitude(longitude);
        iDataManager.updateLatitude(latitude);
    }
}
