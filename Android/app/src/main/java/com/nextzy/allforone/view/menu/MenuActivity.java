package com.nextzy.allforone.view.menu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.fragment.MenuPageFragment;
import com.nextzy.allforone.view.menu.fragment.MenuSiteFragment;
import com.nextzy.allforone.view.menu.model.AISMenu;
import com.nextzy.allforone.view.menu.model.Menu;
import com.nextzy.allforone.view.menu.renderer.MenuLevelOneRenderer;
import com.nextzy.allforone.view.urlscheme.URLSchemeActivity;
import com.nextzy.lib.allforone.common.AISActivity;
import com.nextzy.lib.allforone.config.Mobile;
import com.nextzy.lib.allforone.network.login.AISLoginManager;
import com.nextzy.lib.allforone.util.AnimationUtil;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akexorcist on 7/6/15 AD.
 */
public class MenuActivity extends AISActivity implements MenuLevelOneRenderer.OnMenuLevelOneClickListener, View.OnClickListener {
    public static final String KEY_TARGET_PAGE = "target_page";
    public static final String KEY_CURRENT_MENU = "key_current_menu";
    public static final String KEY_ALL_MENU = "key_all_menu";

    private Menu currentLevelOneMenu;
    private AISMenu menu;

    private Toolbar toolbar;
    private TextView tvToolbarTitle;
    private Button btnToolbarBack;
    private LinearLayout layoutMenuLevelOneContainer;
    private ArrayList<MenuLevelOneRenderer> menuOneRendererList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aisapp_activity_menu_page);

        toolbar = (Toolbar) findViewById(R.id.tb_title);
        toolbar.setTitle("");

        tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_title);
        btnToolbarBack = (Button) toolbar.findViewById(R.id.btn_toolbar_back);
        btnToolbarBack.setOnClickListener(this);
        AnimationUtil.fadeOut(btnToolbarBack, 0);

        layoutMenuLevelOneContainer = (LinearLayout) findViewById(R.id.layout_menu_level_one_container);

        menuOneRendererList = new ArrayList<>();

        setSupportActionBar(toolbar);

        menu = MenuBuilder.newInstance(this).getMenu();
        buildMenuLevelOne();

        // Initial First Menu
        performMenuLevelOneClick(0);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Menu menu = Parcels.unwrap(this.getIntent().getExtras().getParcelable(URLSchemeActivity.KEY_MENU));

            Log.d(TAG, String.format("Menu ID = %s\nTarget Page = %s\nActivity = %s",
                    menu.getId(),
                    menu.getTargetPage(),
                    menu.getActivity()));

            String menuId = menu.getId();
            int tabId = Integer.parseInt(menuId.substring(0, 1));

            performMenuLevelOneClick(tabId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(Mobile.isJustLoggedOut()) {
            Mobile.setJustLoggedOut(false);
            openActivity(MenuActivity.class);
            finish();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

//        menu = Parcels.unwrap(savedInstanceState.getParcelable(KEY_ALL_MENU));
//        currentLevelOneMenu = Parcels.unwrap(savedInstanceState.getParcelable(KEY_CURRENT_MENU));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putParcelable(KEY_ALL_MENU, Parcels.wrap(menu));
//        outState.putParcelable(KEY_CURRENT_MENU, Parcels.wrap(currentLevelOneMenu));
    }

    public void performMenuLevelOneClick(int position) {
        menuOneRendererList.get(position).performClick();
    }

    public void buildMenuLevelOne() {
        List<Menu> menuList = menu.getMenus();
        for (Menu menu : menuList) {
            if (menu.isMenuSupported()) {
                MenuLevelOneRenderer renderer = new MenuLevelOneRenderer(this, menu);
                renderer.setOnMenuLevelOneClickListener(this);
                menuOneRendererList.add(renderer);
                layoutMenuLevelOneContainer.addView(renderer.getView());
            }
        }
    }

    public Activity getActivityFromString(String strClass) {
        try {
            return (Activity) Class.forName(strClass).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onMenuLevelOneClick(final Menu menu, final MenuLevelOneRenderer renderer, boolean isAlreadySelected) {
        AnimationUtil.fadeOut(btnToolbarBack);
        if (!isAlreadySelected) {
            checkLoginRequired(menu, new AISLoginManager.OnLoginCallback() {
                @Override
                public void onLoginSuccess() {
                    renderer.setSelected(true);
                    currentLevelOneMenu = menu;
                    if (menu.getLevel() != 4)
                        tvToolbarTitle.setText(MenuUtils.getMenuName(menu));
                    if (menu.isContainUrl() || menu.isContainMenuList())
                        clearSelector(menu);
                    if (menu.isContainUrl()) {
                        tvToolbarTitle.setText(MenuUtils.getMenuName(menu));
                        Fragment fragment = MenuSiteFragment.newInstance(menu.getUrl());
                        replaceFragment(fragment);
                        showButtonBack();
                    } else if (menu.getLevel() == 1) {
                        Fragment fragment = MenuPageFragment.newInstance(menu);
                        replaceFragment(fragment);
                    } else if (menu.getLevel() == 4) {
                        renderer.setSelected(false);
                        openTargetActivity(menu);
                    }
                }

                @Override
                public void onLoginFailed() {
                    renderer.setSelected(false);
                    showAlertDialog(getString(R.string.aisapp_login_failed));
                }
            });
        } else {
            if (!menu.isContainUrl()) {
                goToLevelTwo();
            }
        }
    }

    public void onMenuLevelTwoClick(final Menu menu) {
        // Always Level 2 or Level 4
        checkLoginRequired(menu, new AISLoginManager.OnLoginCallback() {
            @Override
            public void onLoginSuccess() {
                if (menu.isContainUrl()) {
                    tvToolbarTitle.setText(MenuUtils.getMenuName(menu));
                    Fragment fragment = MenuSiteFragment.newInstance(menu.getUrl());
                    replaceFragment(fragment);
                    showButtonBack();
                } else if (menu.getLevel() == 2) {
                    goToLevelThree(menu);
                } else if (menu.getLevel() == 4) {
                    openTargetActivity(menu);
                }

                if (menu.isContainDefaultMenu()) {
                    onDefaultMenu(menu);
                }
            }

            @Override
            public void onLoginFailed() {
                onBackPressed();
            }
        });
    }

    public void onDefaultMenu(final Menu targetMenu) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openTargetActivity(targetMenu.getMenuListByBusinessType().get(targetMenu.getDefaultMenu()));
            }
        }, 200);
    }

    public void onMenuLevelThreeClick(final Menu menu) {
        // Always Level 4
        checkLoginRequired(menu, new AISLoginManager.OnLoginCallback() {
            @Override
            public void onLoginSuccess() {
                openTargetActivity(menu);
            }

            @Override
            public void onLoginFailed() {
                showAlertDialog(getString(R.string.aisapp_login_failed));
            }
        });
    }

    public void onMenuLevelFourClick(final Menu menu) {
        // Always Level 4
        checkLoginRequired(menu, new AISLoginManager.OnLoginCallback() {
            @Override
            public void onLoginSuccess() {
                openTargetActivity(menu);
            }

            @Override
            public void onLoginFailed() {
                showAlertDialog(getString(R.string.aisapp_login_failed));
            }
        });
    }

    public void checkLoginRequired(final Menu menu, AISLoginManager.OnLoginCallback callback) {
        if (menu.isLoginRequired()) {
            AISLoginManager.login(this, callback);
        } else {
            callback.onLoginSuccess();
        }
    }

    public void goToLevelTwo(Menu menu) {
        tvToolbarTitle.setText(MenuUtils.getMenuName(menu));
    }

    public void goToLevelTwo() {
        goToLevelTwo(currentLevelOneMenu);
        MenuPageFragment menuFragment = getMenuFragment();
        if (menuFragment != null)
            menuFragment.goToMenuLevelTwo();
    }

    public void goToLevelThree(Menu menu) {
        tvToolbarTitle.setText(MenuUtils.getMenuName(menu));
        MenuPageFragment menuFragment = getMenuFragment();
        if (menuFragment != null)
            menuFragment.goToMenuLevelThree(menu);
    }

    public void openTargetActivity(Menu menu) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TARGET_PAGE, menu.getTargetPage());
        openActivity(getActivityFromString(menu.getActivity()).getClass(), bundle);
    }

    public void clearSelector(Menu menu) {
        for (int i = 0; i < menuOneRendererList.size(); i++) {
            MenuLevelOneRenderer renderer = menuOneRendererList.get(i);
            if (!renderer.getMenu().getId().equals(menu.getId())) {
                renderer.setSelected(false);
            }
        }
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        boolean onFragmentBackPressed = false;
        if (currentLevelOneMenu.isContainMenuList()) {
            MenuPageFragment fragment = getMenuFragment();
            if (fragment != null) {
                onFragmentBackPressed = fragment.onBackPressed();
                Log.w("Check", "onFragmentBackPressed " + onFragmentBackPressed);
            }
        }
        Log.e("Check", "onFragmentBackPressed " + onFragmentBackPressed);
        if (!onFragmentBackPressed) {
            showConfirmExitDialog();
        }
    }

    public MenuPageFragment getMenuFragment() {
        try {
            return ((MenuPageFragment) getSupportFragmentManager().findFragmentById(R.id.layout_fragment_container));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MenuSiteFragment getSiteFragment() {
        try {
            return ((MenuSiteFragment) getSupportFragmentManager().findFragmentById(R.id.layout_fragment_container));
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_toolbar_back) {
            onToolbarBackPressed();
        }
    }

    public void onToolbarBackPressed() {
        MenuSiteFragment fragment = getSiteFragment();
        if (fragment != null && fragment.canGoBack()) {

            fragment.onBackPressed();
        } else if (fragment != null && !fragment.canGoBack()) {
            onBackPressed();
//            getSupportFragmentManager().popBackStack();
//            goToLevelTwo(currentLevelOneMenu);
//            hideButtonBack();
        } else if (currentLevelOneMenu.isContainMenuList()) {
            goToLevelTwo();
        }
    }

    public void showButtonBack() {
        btnToolbarBack.setEnabled(true);
        btnToolbarBack.setClickable(true);
        AnimationUtil.fadeIn(btnToolbarBack);
    }

    public void hideButtonBack() {
        btnToolbarBack.setEnabled(false);
        btnToolbarBack.setClickable(false);
        AnimationUtil.fadeOut(btnToolbarBack);
    }
}
