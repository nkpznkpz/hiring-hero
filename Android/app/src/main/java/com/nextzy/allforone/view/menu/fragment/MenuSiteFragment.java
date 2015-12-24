package com.nextzy.allforone.view.menu.fragment;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuActivity;
import com.nextzy.lib.allforone.util.AnimationUtil;

public class MenuSiteFragment extends Fragment {
    private static final String KEY_URL = "url";
    private MenuActivity menuActivity;
    private WebView wvAisSite;
    private RoundCornerProgressBar pbAisSite;
    private boolean canGoBack = false;

    public static MenuSiteFragment newInstance(String url) {
        MenuSiteFragment fragment = new MenuSiteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuActivity = (MenuActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aisapp_fragment_menu_site, container, false);

        String url = getArguments().getString(KEY_URL);
        pbAisSite = (RoundCornerProgressBar) rootView.findViewById(R.id.aisapp_pb_ais_site);
        wvAisSite = (WebView) rootView.findViewById(R.id.aisapp_wv_ais_site);
        WebSettings webSettings = wvAisSite.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        menuActivity.hideButtonBack();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) ;
        webSettings.setPluginState(WebSettings.PluginState.ON);
        wvAisSite.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                handleCanGoBack();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                AnimationUtil.fadeIn(pbAisSite);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                AnimationUtil.fadeOut(pbAisSite);
            }
        });
        wvAisSite.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pbAisSite.setProgress(newProgress);
            }
        });
        wvAisSite.loadUrl(url);

        return rootView;
    }

    public boolean onBackPressed() {
        if (wvAisSite != null && wvAisSite.canGoBack()) {
            wvAisSite.goBack();
            return true;
        }
        return false;
    }

    public void handleCanGoBack() {
        if(wvAisSite.canGoBack() && !canGoBack) {
            canGoBack = true;
            menuActivity.showButtonBack();
        } else if(!wvAisSite.canGoBack() && canGoBack) {
            canGoBack = false;
            menuActivity.hideButtonBack();
        }
    }

    public boolean canGoBack() {
        return wvAisSite != null && wvAisSite.canGoBack();
    }

}
