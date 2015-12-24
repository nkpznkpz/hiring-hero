package com.nextzy.allforone.view.menu.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayout;
import com.balysv.materialripple.MaterialRippleLayout;
import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuActivity;
import com.nextzy.allforone.view.menu.MenuUtils;
import com.nextzy.allforone.view.menu.model.Menu;
import com.nextzy.lib.allforone.util.AISUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuPageTwoFragment extends Fragment {
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_BUSY = 1;

    private MenuActivity activity;
    private LinearLayout layoutMenuContainer;
    private int status = STATUS_IDLE;

    private ArrayList<ExpandableLayout> arrExpandableMenu = new ArrayList<>();
    private int currentExpandableLayout = -1;

    public static MenuPageTwoFragment newInstance() {
        return new MenuPageTwoFragment();
    }

    public MenuPageTwoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MenuActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aisapp_fragment_menu_page_two, container, false);
        layoutMenuContainer = (LinearLayout) rootView.findViewById(R.id.layout_menu_frame);

        return rootView;
    }

    public void updateMenu(Menu menu) {
        layoutMenuContainer.removeAllViews();
        List<Menu> menuList = menu.getMenuListByBusinessType();
        for (Menu menuItem : menuList) {
            if (!menuItem.isContainMenuList()) {
                bindingMenuLevel2Layout(menuItem);
            } else {
                bindingExpandableMenuLevel2Layout(menuItem);
            }
        }
    }

    public void onMenuLevelThreeClick(Menu menu) {
        activity.onMenuLevelThreeClick(menu);
    }

    public void onMenuLevelFourClick(Menu menu) {
        activity.onMenuLevelFourClick(menu);
    }

    public void bindingMenuLevel2Layout(final Menu menu) {
        View viewMenu = LayoutInflater.from(activity).inflate(R.layout.aisapp_view_menu_level_three_header, layoutMenuContainer, false);

        MaterialRippleLayout layoutMenuHeader = (MaterialRippleLayout) viewMenu.findViewById(R.id.layout_menu_header);
        layoutMenuHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == STATUS_IDLE) {
                    debounce(v);
                    collapseOtherExpandableLayout();
                    onMenuLevelThreeClick(menu);
                }
            }
        });

        final TextView tvMenu = (TextView) viewMenu.findViewById(R.id.menu_text);
        tvMenu.setText(MenuUtils.getMenuName(menu));

        ImageView ivIcon = (ImageView) viewMenu.findViewById(R.id.menu_icon);
        ivIcon.setImageResource(AISUtils.getDrawableResourceByFilename(activity, menu.getImage()));

        layoutMenuContainer.addView(viewMenu);
    }

    public void bindingExpandableMenuLevel2Layout(final Menu menu) {
        View viewMenu = LayoutInflater.from(activity).inflate(R.layout.aisapp_view_menu_level_three_expandable, layoutMenuContainer, false);

        final ExpandableLayout expandableMenu = (ExpandableLayout) viewMenu.findViewById(R.id.expandable_layout);
        LinearLayout layoutMenuContent = (LinearLayout) viewMenu.findViewById(R.id.layout_menu_content);
        final int expandableIndex = arrExpandableMenu.size();
        arrExpandableMenu.add(expandableMenu);

        expandableMenu.setLayoutAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(expandableMenu.isOpened()) {
                    setExpandableLayoutEnabled(true);
                    currentExpandableLayout = -1;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        expandableMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableMenu.isOpened()) {
                    currentExpandableLayout = expandableIndex;
                    setExpandableLayoutEnabled(false);
                    collapseOtherExpandableLayout();
                }
            }
        });
        MaterialRippleLayout layoutMenuHeader = (MaterialRippleLayout) viewMenu.findViewById(R.id.layout_menu_header);
        layoutMenuHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expandableMenu.isOpened()) {
                    expandableMenu.hide();
                } else {
                    expandableMenu.show();
                }
//                expandableMenu.toggle();
                currentExpandableLayout = expandableIndex;
                setExpandableLayoutEnabled(false);
                collapseOtherExpandableLayout();
            }
        });

        TextView tvMenu = (TextView) viewMenu.findViewById(R.id.menu_text);
        tvMenu.setText(MenuUtils.getMenuName(menu));

        ImageView ivIcon = (ImageView) viewMenu.findViewById(R.id.menu_icon);
        ivIcon.setImageResource(AISUtils.getDrawableResourceByFilename(activity, menu.getImage()));

        // Add Sub Menu
        for (final Menu menuItem : menu.getMenuListByBusinessType()) {
            View viewSubMenu = LayoutInflater.from(activity).inflate(R.layout.aisapp_view_menu_level_three_sub, layoutMenuContent, false);
            viewSubMenu.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(status == STATUS_IDLE) {
                        debounce(v);
                        //collapseAllExpandableLayout();
                        onMenuLevelFourClick(menuItem);
                    }
                }
            });
            //int textColor = MenuConfig.getMenuTextColor(activity, id);
            TextView tvSubMenu = (TextView) viewSubMenu.findViewById(R.id.menu_text);
            tvSubMenu.setText("• " + MenuUtils.getMenuName(menuItem));
            String menuColor = menuItem.getColor();
            if (menuColor != null) {
                tvSubMenu.setTextColor(Color.parseColor(menuColor));
            }

            layoutMenuContent.addView(viewSubMenu);
        }
        addDivider(layoutMenuContent);

        layoutMenuContainer.addView(viewMenu);
    }

    public void setExpandableLayoutEnabled(boolean state) {
        for (ExpandableLayout expandableMenu : arrExpandableMenu) {
            expandableMenu.setEnabled(state);
        }
    }

    public void collapseOtherExpandableLayout() {
        for (int i = 0; i < arrExpandableMenu.size(); i++) {
            ExpandableLayout expandableLayout = arrExpandableMenu.get(i);
            if (i != currentExpandableLayout) {
                expandableLayout.hide();
            }
        }
    }

    public void collapseAllExpandableLayout() {
        for (ExpandableLayout expandableMenu : arrExpandableMenu) {
            expandableMenu.hide();
        }
    }

    public void debounce(final View view) {
        status = STATUS_BUSY;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                status = STATUS_IDLE;
            }
        }, 1000);
    }

    public void addDivider(ViewGroup viewGroup) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.aisapp_view_menu_divider, viewGroup, false);
        viewGroup.addView(view);
    }
}
