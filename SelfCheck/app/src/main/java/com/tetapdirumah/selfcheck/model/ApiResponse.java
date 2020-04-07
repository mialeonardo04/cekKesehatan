package com.tetapdirumah.selfcheck.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("status")
    private String _status;
    @SerializedName("data")
    private T _item;

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public T get_item() {
        return _item;
    }

    public void set_item(T _item) {
        this._item = _item;
    }
}
