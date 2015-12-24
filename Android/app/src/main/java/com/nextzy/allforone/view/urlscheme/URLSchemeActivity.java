package com.nextzy.allforone.view.urlscheme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.nextzy.allforone.view.menu.MenuActivity;
import com.nextzy.allforone.view.menu.MenuBuilder;
import com.nextzy.allforone.view.menu.model.AISMenu;
import com.nextzy.allforone.view.menu.model.Menu;
import com.nextzy.lib.allforone.common.AISActivity;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by phonbopit on 7/29/15.
 */
public class URLSchemeActivity extends AISActivity {

    private final String KEY_TARGET_PAGE = "target_page";
    private final String KEY_ACTIVITY = "activity";
    private final String KEY_ID = "menu_id";

    public static final String KEY_MENU = "key_menu";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // for BaseURL Scheme ex. aisapp://ais.co.th?menu_id=1000
        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {

            Bundle bundle = handleURLScheme(data);
            openActivity(MenuActivity.class, bundle);
            finish();

        }

    }

    private Bundle handleURLScheme(Uri uri) {

        String id = uri.getQueryParameter(KEY_ID);

        Bundle bundle = new Bundle();
        Menu menu = findMenuById(id);

        bundle.putParcelable(KEY_MENU, Parcels.wrap(menu));
        return bundle;

    }

    private Menu findMenuById(String id) {

        Menu menu = new Menu();

        AISMenu aisMenus = MenuBuilder.newInstance(getApplicationContext()).getMenu();
        List<Menu> menus = aisMenus.getMenus();

        for (Menu rootMenu : menus) {

            if (rootMenu.getId().equals(id)) {
                return rootMenu;
            }

            if (rootMenu.isContainMenuList()) {

                List<Menu> menuLevel2List = rootMenu.getMenuList();

                for (Menu menuLevel2 : menuLevel2List) {
                    if (menuLevel2.getId().equals(id)) {
                        return menuLevel2;
                    }

                    if (menuLevel2.isContainMenuList()) {

                        List<Menu> menuLevel4List = menuLevel2.getMenuList();
                        for (Menu menuLevel4 : menuLevel4List) {
                            if (menuLevel4.getId().equals(id)) {
                                return menuLevel4;
                            }

                            if (menuLevel4.isContainMenuList()) {
                                List<Menu> menuLevel5List = menuLevel4.getMenuList();
                                for (Menu menuLevel5 : menuLevel5List) {
                                    if (menuLevel5.getId().equals(id)) {
                                        return menuLevel5;
                                    }
                                }
                            }

                        }

                    }

                }
            }

        }

        return menu;
    }
}
