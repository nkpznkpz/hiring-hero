package com.nextzy.allforone.network.sample;

import com.akexorcist.localizationactivity.LanguageSetting;
import com.nextzy.lib.allforone.config.BaseURL;
import com.nextzy.lib.allforone.config.Mobile;
import com.nextzy.lib.allforone.network.DynamicEndpoint;
import com.nextzy.lib.allforone.network.NetworkConnection;

import java.util.Locale;

import retrofit.RequestInterceptor;

/**
 * Created by Akexorcist on 7/29/15 AD.
 */
public class AISNetworkService {

    private static AISNetworkInterface service;
    private static DynamicEndpoint endpoint;
    private static RequestInterceptor interceptor;

    public static AISNetworkInterface getConnection() {
        if(endpoint == null) {
            createEndpoint(BaseURL.URL_BASE);
        }
        if(interceptor == null) {
            interceptor = createInterceptor();
        }
        if(service == null) {
            service = getServiceInstance();
        }
        return service;
    }

    private static RequestInterceptor createInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("X-MSISDNResult", Mobile.getMobileNumber());
                request.addHeader("X-Language", LanguageSetting.getLanguage().toUpperCase(Locale.getDefault()));
            }
        };
    }

    private static void createEndpoint(String url) {
        endpoint = new DynamicEndpoint(url);
    }

    public static void setEndpoint(String url) {
        if(endpoint == null)
            createEndpoint(url);
        else
            endpoint.setUrl(url);
    }

    private static AISNetworkInterface getServiceInstance() {
        return NetworkConnection.createJSONServiceInstance(AISNetworkInterface.class, endpoint, interceptor);
    }
}
