package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractFormIdentitas;
import com.tetapdirumah.selfcheck.contract.ContractHome;
import com.tetapdirumah.selfcheck.manager.DataManager;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;
import com.tetapdirumah.selfcheck.presenter.PresenterFormIdentitas;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewFormIdentitas extends AppCompatActivity implements ContractFormIdentitas.View {

    @BindView(R.id.form_btnStart)
    Button btnStart;
    @BindView(R.id.et_nama)
    TextInputEditText etNama;
    @BindView(R.id.et_kota)
    TextInputEditText etKota;

    private ContractFormIdentitas.Presenter presenter;
    DataManager dataManager;
    IDataManager iDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_form_identitas);

        ButterKnife.bind(this);

        dataManager = new DataManager(getApplicationContext());
        iDataManager = new DataManagerWrapper(getApplicationContext());

        presenter = new PresenterFormIdentitas(this, iDataManager);
        dataManager.clear();
        etNama.setText("");
        etKota.setText("");

        btnStart.setOnClickListener(v -> {
            if (!etNama.getText().toString().equals("") && !etKota.getText().toString().equals("")){
                presenter.data();
                nextPage();
                finish();
            } else {
                showToast("Nama atau asal kota tidak boleh kosong");
            }
        });
    }

    void showToast(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextPage() {
        //store data nama, umur ke sqlite
        Intent intent = new Intent(this, ViewForm.class);
        startActivity(intent);
        finish();
    }

    @Override
    public String nama() {
        String nama = etNama.getText().toString();
        return nama;
    }

    @Override
    public String kota() {
        String kota = etKota.getText().toString();
        return kota;
    }
}
