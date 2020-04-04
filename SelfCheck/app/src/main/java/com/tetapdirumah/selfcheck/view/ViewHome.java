package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractHome;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHome extends AppCompatActivity implements ContractHome.View {

    @BindView(R.id.btnSpeedDial)
    SpeedDialView btnDial;
    @BindView(R.id.btnStart)
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_home);

        ButterKnife.bind(this);

        speedDial();
        btnStart.setOnClickListener(v -> {
            goToActivity();
        });
    }

    @Override
    public void speedDial() {
        btnDial.addActionItem(
               new SpeedDialActionItem.Builder(R.id.fab_phone,R.drawable.ic_phone_black_24dp)
                       .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, getTheme()))
                       .create()
        );
        btnDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_browser,R.drawable.ic_public_black_24dp)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, getTheme()))
                        .create()
        );
        btnDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_credit,R.drawable.ic_settings_black_48dp)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, getTheme()))
                        .create()
        );

        btnDial.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()){
                    case R.id.fab_phone:
                        Toast.makeText(getApplicationContext(), "phone", Toast.LENGTH_SHORT).show();
                        btnDial.close();
                        break;
                    case R.id.fab_browser:
                        Toast.makeText(getApplicationContext(), "web", Toast.LENGTH_SHORT).show();
                        btnDial.close();
                        break;
                    case R.id.fab_credit:
                        Toast.makeText(getApplicationContext(), "credit", Toast.LENGTH_SHORT).show();
                        btnDial.close();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void goToActivity() {
        Intent intent = new Intent(getApplicationContext(), ViewFormIdentitas.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void call() {
        // call 119
    }

    @Override
    public void credit() {
        // open dialogbox credit

    }

    @Override
    public void browser() {
        // open browser tetapdirumah.id

    }
}
