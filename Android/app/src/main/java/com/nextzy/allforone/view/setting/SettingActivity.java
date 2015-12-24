package com.nextzy.allforone.view.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nextzy.allforone.R;
import com.nextzy.lib.allforone.common.AISActivity;
import com.nextzy.lib.allforone.util.AISUtils;
import com.nextzy.lib.allforone.view.term.AISTermActivity;

public class SettingActivity extends AISActivity implements View.OnClickListener {

    private static final String LANGUAGE_TH = "th";
    private static final String LANGUAGE_EN = "en";

    private LinearLayout layoutSettingLanguageToggle;
    private LinearLayout layoutTermCondition;
    private TextView tvSettingLanguageEn;
    private TextView tvSettingLanguageTh;
    private TextView tvAppVersion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aisapp_activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_title);

        TextView tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText(R.string.aisapp_menu_setting);


        Button btnToolbarBack = (Button) toolbar.findViewById(R.id.btn_toolbar_back);
        btnToolbarBack.setOnClickListener(this);

        layoutSettingLanguageToggle = (LinearLayout) findViewById(R.id.layout_setting_language_toggle);
        layoutSettingLanguageToggle.setOnClickListener(this);

        layoutTermCondition = (LinearLayout) findViewById(R.id.aisapp_layout_term_condition);
        layoutTermCondition.setOnClickListener(this);

        tvSettingLanguageEn = (TextView) findViewById(R.id.tv_setting_language_en);
        tvSettingLanguageTh = (TextView) findViewById(R.id.tv_setting_language_th);
        tvAppVersion = (TextView) findViewById(R.id.aisapp_tv_app_version);
        tvAppVersion.setText(String.format("%s %s", getString(R.string.aisapp_setting_app_version), AISUtils.getAppVersion(this)));

        updateLanguageButton();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.layout_setting_language_toggle) {
            toggleLanguage();
        } else if (id == R.id.btn_toolbar_back) {
            finish();
        } else if (id == R.id.aisapp_layout_term_condition) {
            openTermActivity();
        }
    }

    private void toggleLanguage() {
        String language = getLanguage();
        if (language.equalsIgnoreCase(LANGUAGE_EN)) {
            setLanguageTh();
        } else if (language.equalsIgnoreCase(LANGUAGE_TH)) {
            setLanguageEn();
        }
    }

    private void setLanguageTh() {
        setLanguage(LANGUAGE_TH);
        updateLanguageButton();
    }

    private void setLanguageEn() {
        setLanguage(LANGUAGE_EN);
        updateLanguageButton();
    }

    private void updateLanguageButton() {
        String language = getLanguage();
        if (language.equalsIgnoreCase(LANGUAGE_EN)) {
            tvSettingLanguageEn.setEnabled(false);
            tvSettingLanguageTh.setEnabled(true);
        } else if (language.equalsIgnoreCase(LANGUAGE_TH)) {
            tvSettingLanguageEn.setEnabled(true);
            tvSettingLanguageTh.setEnabled(false);
        }
    }

    private void openTermActivity() {
        openActivity(AISTermActivity.class);
    }
}
