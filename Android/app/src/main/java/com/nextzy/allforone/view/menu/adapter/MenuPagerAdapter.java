package com.nextzy.allforone.view.menu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nextzy.allforone.view.menu.fragment.MenuPageOneFragment;
import com.nextzy.allforone.view.menu.fragment.MenuPageTwoFragment;
import com.nextzy.allforone.view.menu.model.Menu;

/**
 * Created by Akexorcist on 7/6/15 AD.
 */
public class MenuPagerAdapter  extends FragmentPagerAdapter {
    public static final int FRAGMENT_MENU_PAGE_ONE = 0;
    public static final int FRAGMENT_MENU_PAGE_TWO = 1;
    private final int NUM_ITEMS = 2;

    private Menu menu;

    public MenuPagerAdapter(FragmentManager fm, Menu menu) {
        super(fm);
        this.menu = menu;
    }

    public int getCount() {
        return NUM_ITEMS;
    }

    public Fragment getItem(int position) {
        if(position == FRAGMENT_MENU_PAGE_ONE)
            return MenuPageOneFragment.newInstance(menu);
        else if(position == FRAGMENT_MENU_PAGE_TWO)
            return MenuPageTwoFragment.newInstance();
        return null;
    }
}