package com.nextzy.allforone.view.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nextzy.allforone.R;
import com.nextzy.lib.allforone.common.AISActivity;
import com.nextzy.lib.allforone.network.push.PushNotificationManager;

public class SamplePushActivity extends AISActivity {

    public static final String KEY_TARGET_PAGE = "key_target_page";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aisapp_activity_sample_push);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_title);
        toolbar.setTitle("");

        String target = getIntent().getStringExtra(KEY_TARGET_PAGE);

        ToggleButton tgPush = (ToggleButton) findViewById(R.id.tg_push);

        // TODO: get/save preference for Push (enable/disable)
        tgPush.setChecked(true);

        PushNotificationManager.subscribeChannel(PushNotificationManager.CHANNEL_TEST);

        tgPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PushNotificationManager.subscribeChannel(PushNotificationManager.CHANNEL_TEST);
                } else {
                    PushNotificationManager.unsubscribeChannel(PushNotificationManager.CHANNEL_TEST);
                }
            }
        });

        TextView tvToolbarSample = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tvToolbarSample.setText(target);
    }
}
