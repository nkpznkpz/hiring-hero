package com.nextzy.allforone.network.sample;

import com.nextzy.lib.allforone.network.Blank;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akexorcist on 5/18/15 AD.
 */
public class AISServiceManager {

    public static void callService(final OnCallServiceCallback callback) {
        AISNetworkService.getConnection().getOTP(new Callback<Blank>() {
            @Override
            public void success(Blank blank, Response response) {
                callback.onCallServiceSuccess(blank);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }

    public interface OnCallServiceCallback {
        void onCallServiceSuccess(Blank blank);
        void onCallServiceFailed(Exception exception);
    }
}
