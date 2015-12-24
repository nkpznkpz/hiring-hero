package com.nextzy.allforone;

import com.nextzy.lib.allforone.util.AuthFailedPreference;
import com.nextzy.lib.allforone.common.AISApplication;
import com.nextzy.lib.allforone.config.Mobile;

/**
 * Created by phonbopit on 7/22/15.
 */
public class MainApplication extends AISApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Mobile.init(this);
        Mobile.clear();
        AuthFailedPreference.init(this);
    }
}
