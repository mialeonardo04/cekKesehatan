package com.tetapdirumah.selfcheck.sqlite;

public class DataDiagnosa {

    int _id;
    String _nama;
    String _covid;
    String _flu;
    String _cold;
    String _date_created;

    public DataDiagnosa() {
    }

    public DataDiagnosa(String _nama, String _covid, String _flu, String _cold, String _date_created) {
        this._nama = _nama;
        this._covid = _covid;
        this._flu = _flu;
        this._cold = _cold;
        this._date_created = _date_created;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nama() {
        return _nama;
    }

    public void set_nama(String _nama) {
        this._nama = _nama;
    }

    public String get_covid() {
        return _covid;
    }

    public void set_covid(String _covid) {
        this._covid = _covid;
    }

    public String get_flu() {
        return _flu;
    }

    public void set_flu(String _flu) {
        this._flu = _flu;
    }

    public String get_cold() {
        return _cold;
    }

    public void set_cold(String _cold) {
        this._cold = _cold;
    }

    public String get_date_created() {
        return _date_created;
    }

    public void set_date_created(String _date_created) {
        this._date_created = _date_created;
    }
}
