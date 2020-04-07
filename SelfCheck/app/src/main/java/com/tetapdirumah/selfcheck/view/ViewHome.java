package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.rey.material.app.Dialog;
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
                        call();
                        btnDial.close();
                        break;
                    case R.id.fab_browser:
                        browser();
                        btnDial.close();
                        break;
                    case R.id.fab_credit:
                        credit();
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
    }

    @Override
    public void call() {
        // call 119
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel: 119"));
        startActivity(callIntent);
    }

    @Override
    public void credit() {
        // open dialogbox credit
        com.rey.material.app.Dialog dialog = new Dialog(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_credit, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setTitle("Credit");
        dialog.cornerRadius(5);
        dialog.closeOptionsMenu();
        dialog.showDivider(true);
        dialog.backgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()));
        dialog.titleColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, getTheme()));
        dialog.contentMargin(20, 20, 20, 20);

        dialog.show();
    }

    @Override
    public void browser() {
        // open browser tetapdirumah.id
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse("http://tetapdirumah.id"));
        startActivity(browserIntent);
    }
}
