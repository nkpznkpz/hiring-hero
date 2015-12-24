package com.nextzy.allforone.config;

import android.content.Context;
import android.graphics.Color;

import com.nextzy.allforone.R;

/**
 * Created by Akexorcist on 7/9/15 AD.
 */
public class MenuConfig {

    public static int getMenuTextColor(Context context, String menuId) {
        if(menuId.contains("10")) {
            return Color.parseColor("#d79939");
        } else if(menuId.contains("20")) {
            return Color.parseColor("#00a3a9");
        } else if(menuId.contains("30")) {
            return Color.parseColor("#f48243");
        } else if(menuId.contains("40")) {
            return Color.parseColor("#a259a3");
        } else if(menuId.contains("50")) {
            return Color.parseColor("#fc6f51");
        } else if(menuId.contains("60")) {
            return Color.parseColor("#8fa0aa");
        } else if(menuId.contains("70")) {
            return Color.parseColor("#e285b0");
        } else if(menuId.contains("80")) {
            return Color.parseColor("#8fa0aa");
        }
        return context.getResources().getColor(R.color.ais_text_black);
    }

    public static int getMenuLevelTwoImageResource(String menuId) {
        if(menuId.contains("10")) {
            return R.drawable.aisapp_menu_lv3_01;
        } else if(menuId.contains("20")) {
            return R.drawable.aisapp_menu_lv3_02;
        } else if(menuId.contains("30")) {
            return R.drawable.aisapp_menu_lv3_03;
        } else if(menuId.contains("40")) {
            return R.drawable.aisapp_menu_lv3_04;
        } else if(menuId.contains("50")) {
            return R.drawable.aisapp_menu_lv3_05;
        } else if(menuId.contains("60")) {
            return R.drawable.aisapp_menu_lv3_06;
        } else if(menuId.contains("70")) {
            return R.drawable.aisapp_menu_lv3_07;
        } else if(menuId.contains("80")) {
            return R.drawable.aisapp_menu_lv3_08;
        }
        return 0;
    }

    public static int getMenuLevelOneImageResource(String id) {
        if(id.contains("100")) {
            return R.drawable.aisapp_menu_lv2_01;
        } else if (id.contains("200")) {
            return R.drawable.aisapp_menu_lv2_02;
        } else if (id.contains("300")) {
            return R.drawable.aisapp_menu_lv2_03;
        } else if (id.contains("400")) {
            return R.drawable.aisapp_menu_lv2_04;
        } else if (id.contains("500")) {
            return R.drawable.aisapp_menu_lv2_05;
        } else if (id.contains("600")) {
            return R.drawable.aisapp_menu_lv2_06;
        } else if (id.contains("700")) {
            return R.drawable.aisapp_menu_lv2_07;
        } else if (id.contains("800")) {
            return R.drawable.aisapp_menu_lv2_08;
        } else if (id.contains("900")) {
            return R.drawable.aisapp_menu_lv2_09;
        }
        return 0;
    }

}










