package com.nextzy.allforone.network.sample;

import com.nextzy.lib.allforone.network.Blank;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Akexorcist on 5/9/15 AD.
 */
public interface AISNetworkInterface {

    @GET(URL.URL_GET_OTP)
    void getOTP(Callback<Blank> callback);



}
