package com.nextzy.allforone.view.menu.model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.nextzy.lib.allforone.config.Mobile;

import org.parceler.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 7/8/15 AD.
 */

@Parcel(parcelsIndex = false)
public class Menu {
    int level;
    String id;
    String name;
    String nameEn;
    String nameTh;
    String image;
    String color;
    String url;

    @SerializedName("business_type")
    List<String> businessType;

    @SerializedName("login_required")
    boolean loginRequired;

    String activity;

    @SerializedName("target_page")
    String targetPage;

    @SerializedName("default_menu")
    int defaultMenu = -1;

    @SerializedName("menu")
    List<Menu> menus;

    public Menu() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameTh() {
        return nameTh;
    }

    public void setNameTh(String nameTh) {
        this.nameTh = nameTh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isContainUrl() {
        return url != null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getBusinessType() {
        return businessType;
    }

    public void setBusinessType(List<String> businessType) {
        this.businessType = businessType;
    }

    public boolean isLoginRequired() {
        return loginRequired;
    }

    public void setLoginRequired(boolean loginRequired) {
        this.loginRequired = loginRequired;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setTargetPage(String targetPage) {
        this.targetPage = targetPage;
    }

    public String getTargetPage() {
        return targetPage;
    }

    public int getDefaultMenu() {
        return defaultMenu;
    }

    public boolean isContainDefaultMenu() {
        return defaultMenu != -1;
    }

    public void setDefaultMenu(int defaultMenu) {
        this.defaultMenu = defaultMenu;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getMenuList() {
        return menus;
    }

    public List<Menu> getMenuListByBusinessType() {
        ArrayList<Menu> menuList = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.isMenuSupported())
                menuList.add(menu);
        }
        return menuList;
    }

    public void setMenuList(List<Menu> menus) {
        this.menus = menus;
    }

    public void removeMenu(int index) {
        if (menus != null && menus.size() > 0)
            menus.remove(index);
    }

    public boolean isContainMenuList() {
        return menus != null && menus.size() > 0;
    }

    public boolean isMenuSupported() {
        boolean isBos = Mobile.isBos();
        boolean isCorporate = Mobile.isCorporate();
        String businessType;
        if (Mobile.isPostpaid()) {
            businessType = "POST";
        } else if (Mobile.isPrepaid()) {
            businessType = "PRE";
        } else {
            businessType = "UNKNOWN";
        }
        for (String type : this.businessType) {
            if (type.contains("ALL")) {
                return true;
            }
            if (type.contains(businessType)) {
                if (isBos && type.contains("BOS") && isCorporate && type.contains("CORP")) {
                    return true;
                }
                if (isBos && type.contains("BOS") && !isCorporate && !type.contains("CORP")) {
                    return true;
                }
                if (!isBos && !type.contains("BOS") && isCorporate && type.contains("CORP")) {
                    return true;
                }
                if (!isBos && !type.contains("BOS") && !isCorporate && !type.contains("CORP")) {
                    return true;
                }
            }
            if (type.contains("ESTATEMENT") && Mobile.isEStatement()) {
                return true;
            }
        }
        return false;
    }
}
