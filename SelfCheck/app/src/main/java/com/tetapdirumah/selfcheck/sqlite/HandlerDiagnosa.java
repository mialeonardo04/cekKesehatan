package com.tetapdirumah.selfcheck.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HandlerDiagnosa extends SQLiteOpenHelper {

    public static final int db_version = 1;

    public static final String db_name = "selfChekcer";

    public static final String table_name = "t_riwayat";

    public static final String key_id = "id_riwayat";
    public static final String key_nama = "nama";
    public static final String key_covid = "covid";
    public static final String key_flu = "flu";
    public static final String key_cold = "cold";
    public static final String key_date = "date";
    public static final String[] COLUMNS = {key_id, key_nama, key_covid, key_flu, key_cold, key_date};

    public HandlerDiagnosa(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: table t_riwayat created");
        String query = "CREATE TABLE " + table_name + "("
                + key_id + " INTEGER PRIMARY KEY,"
                + key_nama + " STRING,"
                + key_covid + " STRING,"
                + key_flu + " STRING,"
                + key_cold + " STRING,"
                + key_date + " STRING)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public void addData(DataDiagnosa dataDiagnosa){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d(TAG, "addData begin");
        ContentValues values = new ContentValues();

        values.put(key_nama, dataDiagnosa.get_nama());
        values.put(key_covid, dataDiagnosa.get_covid());
        values.put(key_flu, dataDiagnosa.get_flu());
        values.put(key_cold, dataDiagnosa.get_cold());
        values.put(key_date, dataDiagnosa.get_date_created());

        db.insert(table_name, null, values);
        db.close();
    }

    public List<DataDiagnosa> getData(){
        List<DataDiagnosa> data = new LinkedList<>();
        String query = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        DataDiagnosa dataDiagnosa = null;

        if (cursor.moveToFirst()){
            do {
                dataDiagnosa = new DataDiagnosa();
                dataDiagnosa.set_id(Integer.parseInt(cursor.getString(0)));
                dataDiagnosa.set_nama(cursor.getString(1));
                dataDiagnosa.set_covid(cursor.getString(2));
                dataDiagnosa.set_flu(cursor.getString(3));
                dataDiagnosa.set_cold(cursor.getString(4));
                dataDiagnosa.set_date_created(cursor.getString(5));
                data.add(dataDiagnosa);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void deleteRiwayat(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, null, null);
        db.close();
    }

    public long countCart(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, table_name);
        db.close();
        return count;
    }
}
