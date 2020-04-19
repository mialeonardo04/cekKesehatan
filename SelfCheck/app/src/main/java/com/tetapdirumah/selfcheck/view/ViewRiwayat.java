package com.tetapdirumah.selfcheck.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.adapter.AdapterRiwayatData;
import com.tetapdirumah.selfcheck.contract.ContractRiwayat;
import com.tetapdirumah.selfcheck.presenter.PresenterRiwayat;
import com.tetapdirumah.selfcheck.sqlite.DataDiagnosa;
import com.tetapdirumah.selfcheck.sqlite.HandlerDiagnosa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewRiwayat extends AppCompatActivity implements ContractRiwayat.View {

    private static final String TAG = "ViewRiwayat";

    @BindView(R.id.rc_layout)
    RecyclerView rvLayout;
    @BindView(R.id.tv_link)
    TextView tvLink;

    private AdapterRiwayatData adapter;
    private ContractRiwayat.Presenter presenter;
    private HandlerDiagnosa hData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        ButterKnife.bind(this);
        hData = new HandlerDiagnosa(this);

        tvLink.setMovementMethod(LinkMovementMethod.getInstance());

        presenter = new PresenterRiwayat(this, hData);

        Log.d(TAG, "jumlah riwayat: " + hData.countCart());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
    }

    @Override
    public void initializeData(List<DataDiagnosa> dataDiagnosas) {
        adapter = new AdapterRiwayatData(dataDiagnosas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getApplicationContext(), RecyclerView.VERTICAL, false
        );
        rvLayout.setLayoutManager(layoutManager);
        rvLayout.setItemAnimator(new DefaultItemAnimator());
        rvLayout.setAdapter(adapter);
    }

}
