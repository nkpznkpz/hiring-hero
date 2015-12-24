package com.nextzy.allforone.view.menu.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Akexorcist on 7/8/15 AD.
 */
@Parcel(parcelsIndex = false)
public class AISMenu {
    @SerializedName("menu")
    List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
