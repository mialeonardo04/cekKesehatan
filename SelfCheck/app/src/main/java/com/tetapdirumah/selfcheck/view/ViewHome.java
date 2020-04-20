package com.tetapdirumah.selfcheck.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;
import com.rey.material.app.Dialog;
import com.rey.material.widget.Button;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.contract.ContractHome;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHome extends AppCompatActivity implements ContractHome.View {

    private static final String TAG = "ViewHome";

    int PERMISSION_ID = 44;
    boolean doubleBack = false;
    @BindView(R.id.btnSpeedDial)
    SpeedDialView btnDial;
    @BindView(R.id.btnStart)
    Button btnStart;
    @BindView(R.id.img_home)
    ImageView img;
    @BindView(R.id.tv_link)
    TextView tvLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_home);

        ButterKnife.bind(this);

        img.setImageBitmap(null);
        Glide.with(this)
                .load(R.drawable.img_person)
                .fitCenter()
                .into(img);

        speedDial();
        btnStart.setOnClickListener(v -> {
            goToActivity();
        });

        tvLink.setMovementMethod(LinkMovementMethod.getInstance());

        //getLocation();

        requestPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void speedDial() {
        btnDial.addActionItem(
               new SpeedDialActionItem.Builder(R.id.fab_phone,R.drawable.ic_phone_black_24dp)
                       .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, getTheme()))
                       .setLabel("Contact Center")
                       .create()
        );
        btnDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_browser,R.drawable.ic_public_black_24dp)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, getTheme()))
                        .setLabel("tetapdirumah.id")
                        .create()
        );
        btnDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_history, R.drawable.ic_history_black_24dp)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, getTheme()))
                        .setLabel("Riwayat")
                        .create()
        );
        btnDial.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_credit,R.drawable.ic_settings_black_48dp)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, getTheme()))
                        .setLabel("credit")
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
                    case R.id.fab_history:
                        goToRiwayat();
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
        Intent intent = new Intent(this, ViewKonfirmasi.class);
        startActivity(intent);
    }

    void goToRiwayat(){
        startActivity(new Intent(this, ViewRiwayat.class));
    }

    @Override
    public void call() {
        // call 119
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


        //tel: 119

    }

    void callWilayah(String s, Button button){
        button.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(s));
            startActivity(callIntent);
        });
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

    @Override
    public boolean  checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    @Override
    public void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Granted. Start getting the location information
                //getLocation();
            }
        }
    }

    @Override
    public boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void getLocation() {
        requestPermission();
    }

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            finish();
        }
        this.doubleBack = true;
        Toast.makeText(this,"Click back again to exit",Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack = false;
            }
        },2000);
    }
}
