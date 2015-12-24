package com.nextzy.allforone.view.splashscreen;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.nextzy.lib.allforone.util.AuthFailedPreference;
import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuActivity;
import com.nextzy.lib.allforone.common.AISActivity;
import com.nextzy.lib.allforone.config.Mobile;
import com.nextzy.lib.allforone.util.AISUtils;

public class SplashScreenActivity extends AISActivity {
    TextView tvAppVersion;

    private Handler handler;
    private Runnable runnable;
    private long delay_time;
    private long time = 1000L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aisapp_activity_splashscreen);

        Mobile.clear();
        AuthFailedPreference.clearIdCardAuth();

        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
        tvAppVersion.setText(getString(R.string.aisapp_app_version) + " " + AISUtils.getAppVersion(this));

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                openActivity(MenuActivity.class, true);
            }
        };
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }
}
