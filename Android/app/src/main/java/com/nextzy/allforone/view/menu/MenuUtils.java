package com.nextzy.allforone.view.menu;

import com.nextzy.allforone.view.menu.model.Menu;
import com.nextzy.lib.allforone.util.StringUtils;
/**
 * Created by Akexorcist on 10/20/15 AD.
 */
public class MenuUtils {
    public static String getMenuName(Menu menu) {
        if (menu.getNameEn() != null &&
                !menu.getNameEn().isEmpty() &&
                menu.getNameTh() != null &&
                !menu.getNameTh().isEmpty()) {
            return StringUtils.getLocalizedMessage(menu.getNameEn(), menu.getNameTh());
        }
        return menu.getName();
    }
}
