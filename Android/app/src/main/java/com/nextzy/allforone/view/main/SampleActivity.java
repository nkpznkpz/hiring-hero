package com.nextzy.allforone.view.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nextzy.allforone.R;
import com.nextzy.lib.allforone.common.AISActivity;

/**
 * Created by Akexorcist on 7/9/15 AD.
 */
public class SampleActivity extends AISActivity implements View.OnClickListener {
    public static final String KEY_TARGET_PAGE = "target_page";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aisapp_activity_sample);

        String target = getIntent().getStringExtra(KEY_TARGET_PAGE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_title);

        TextView tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText(target);

        TextView tvSampleMessage = (TextView) findViewById(R.id.tv_sample_message);
        tvSampleMessage.setText(target);

        Button btnToolbarBack = (Button) toolbar.findViewById(R.id.btn_toolbar_back);
        btnToolbarBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_toolbar_back) {
            finish();
        }
    }
}
