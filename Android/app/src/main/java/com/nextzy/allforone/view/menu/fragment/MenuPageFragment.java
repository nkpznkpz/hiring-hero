package com.nextzy.allforone.view.menu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuActivity;
import com.nextzy.allforone.view.menu.adapter.MenuPagerAdapter;
import com.nextzy.allforone.view.menu.model.Menu;
import com.nextzy.lib.allforone.transformer.FadePagerTransformer;
import com.nextzy.lib.allforone.widget.NonSwipeableViewPager;

import org.parceler.Parcels;


public class MenuPageFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private static final String KEY_MENU = "key_menu";

    private NonSwipeableViewPager vpMenuList;
    private MenuActivity activity;
    private Menu menu;

    public static MenuPageFragment newInstance(Menu menu) {
        MenuPageFragment fragment = new MenuPageFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_MENU, Parcels.wrap(menu));
        fragment.setArguments(bundle);
        return fragment;
    }

    public MenuPageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MenuActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aisapp_fragment_menu_service, container, false);

        menu = Parcels.unwrap(getArguments().getParcelable(KEY_MENU));
        MenuPagerAdapter adapter = new MenuPagerAdapter(getChildFragmentManager(), menu);
        vpMenuList = (NonSwipeableViewPager) rootView.findViewById(R.id.vp_menu_list);
        vpMenuList.setPageTransformer(true, new FadePagerTransformer());
        vpMenuList.addOnPageChangeListener(this);
        vpMenuList.setAdapter(adapter);

        return rootView;
    }

    public void goToMenuLevelThree() {
        vpMenuList.setCurrentItem(MenuPagerAdapter.FRAGMENT_MENU_PAGE_TWO);
    }

    public void goToMenuLevelThree(Menu menu) {
        goToMenuLevelThree();
        MenuPageTwoFragment fragment = (MenuPageTwoFragment) getActiveFragment(vpMenuList, MenuPagerAdapter.FRAGMENT_MENU_PAGE_TWO);
        fragment.updateMenu(menu);
    }

    public void goToMenuLevelTwo() {
        vpMenuList.setCurrentItem(MenuPagerAdapter.FRAGMENT_MENU_PAGE_ONE);
    }

    public void onMenuLevelTwoSelected(Menu menu) {
        activity.onMenuLevelTwoClick(menu);
    }

    public boolean onBackPressed() {
        if(vpMenuList.getCurrentItem() != 0) {
            vpMenuList.setCurrentItem(vpMenuList.getCurrentItem() - 1);
            if(vpMenuList.getCurrentItem() == 0)
                activity.goToLevelTwo(menu);
            return true;
        }
        return false;
    }

    public Fragment getActiveFragment(ViewPager container, int position) {
        String name = "android:switcher:" + container.getId() + ":" + position;
        return getChildFragmentManager().findFragmentByTag(name);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    @Override
    public void onPageSelected(int position) {
        if(position == MenuPagerAdapter.FRAGMENT_MENU_PAGE_ONE) {
            activity.hideButtonBack();
        } if(position == MenuPagerAdapter.FRAGMENT_MENU_PAGE_TWO) {
            activity.showButtonBack();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
 }
}
