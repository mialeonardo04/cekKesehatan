package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.rey.material.widget.Button;
import com.rey.material.widget.CheckBox;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.manager.DataManagerWrapper;
import com.tetapdirumah.selfcheck.manager.IDataManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewKonfirmasi extends AppCompatActivity {

    @BindView(R.id.ck_setuju)
    CheckBox ck;
    @BindView(R.id.btnMulai)
    Button btnMulai;
    @BindView(R.id.tv_link)
    TextView tvLink;

    IDataManager iDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_konfirmasi);

        ButterKnife.bind(this);

        iDataManager = new DataManagerWrapper(this);

        tvLink.setMovementMethod(LinkMovementMethod.getInstance());

        btnMulai.setOnClickListener(v -> {
            if (ck.isChecked()){
                startActivity(new Intent(this, ViewFormIdentitas.class));
            } else {
                startActivity(new Intent(this, ViewForm.class));
            }
            iDataManager.createData("guest", "0");
        });
    }
}
