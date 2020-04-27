package com.tetapdirumah.selfcheck.adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.progresviews.ProgressWheel;
import com.tetapdirumah.selfcheck.R;
import com.tetapdirumah.selfcheck.sqlite.DataDiagnosa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterRiwayatData extends RecyclerView.Adapter<AdapterRiwayatData.ViewHolder>{

    private static final String TAG = "AdapterRiwayatData";
    private List<DataDiagnosa> mData;

    public AdapterRiwayatData(List<DataDiagnosa> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.riwayat_data_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataDiagnosa diagnosa = mData.get(position);
        holder.setData(diagnosa);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.riwayat_pw_covid)
        ProgressWheel pwCovid;
        @BindView(R.id.riwayat_pw_flu)
        ProgressWheel pwFlu;
        @BindView(R.id.riwayat_pw_cold)
        ProgressWheel pwCold;
        @BindView(R.id.riwayat_layout)
        LinearLayout layout;
        @BindView(R.id.tv_nama)
        TextView tvNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(DataDiagnosa data){
            tvNama.setText(data.get_nama() + ", " + showDate(data.get_date_created()));
            setProgressbar(data.get_covid().toString(), pwCovid);
            setProgressbar(data.get_flu().toString(), pwFlu);
            setProgressbar(data.get_cold().toString(), pwCold);
        }

        public void setProgressbar(String s, ProgressWheel wheel){
            wheel.setStepCountText(s);
            float val = (Float.parseFloat(s) * 360) / 100;
            wheel.setPercentage((int)val);
        }

        public String showDate(String date){
            long milisecond = Long.parseLong(date);
            String resultDate = DateFormat.format("dd/MM/yyyy HH:mm:ss", new Date(milisecond)).toString();
            return resultDate;
        }
    }
}
