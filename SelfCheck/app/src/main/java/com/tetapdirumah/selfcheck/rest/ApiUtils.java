package com.tetapdirumah.selfcheck.rest;

public class ApiUtils {
    public static final String BASE_URL = "http://igaraleon.com:3000/";

    public static ApiInterface getAPIService(){
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
