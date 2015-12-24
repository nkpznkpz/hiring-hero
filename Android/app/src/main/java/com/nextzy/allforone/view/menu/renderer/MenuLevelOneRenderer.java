package com.nextzy.allforone.view.menu.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuUtils;
import com.nextzy.allforone.view.menu.model.Menu;
import com.nextzy.lib.allforone.util.AISUtils;

/**
 * Created by Akexorcist on 7/10/15 AD.
 */
public class MenuLevelOneRenderer {
    private Context context;
    private View view;
    private ImageView ivMenuLevelOne;
    private Menu menu;
    private OnMenuLevelOneClickListener listener;
    private boolean isSelected = false;

    public MenuLevelOneRenderer(Context context, Menu menu) {
        this.context = context;
        this.menu = menu;
    }

    public void setOnMenuLevelOneClickListener(OnMenuLevelOneClickListener listener) {
        this.listener = listener;
    }

    public View getView() {
        view = LayoutInflater.from(context).inflate(R.layout.aisapp_view_menu_level_one, null);
        ViewGroup.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        view.setLayoutParams(param);

        MaterialRippleLayout rippleMenuLevelOne = (MaterialRippleLayout) view.findViewById(R.id.mtl_menu_level_one);
        ivMenuLevelOne = (ImageView) view.findViewById(R.id.iv_menu_level_one);
        setSelected(false);

        TextView tvMenuLevelOne = (TextView) view.findViewById(R.id.tv_menu_level_one);
        tvMenuLevelOne.setText(MenuUtils.getMenuName(menu));

        rippleMenuLevelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performClick();
            }
        });
        return view;
    }

    public void performClick() {
        if (listener != null && !isSelected) {
            if (menu.isContainMenuList() || menu.isContainUrl())
                setSelected(true);
            listener.onMenuLevelOneClick(menu, this, false);
        } else if (listener != null && isSelected) {
            listener.onMenuLevelOneClick(menu, this, true);
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public void setSelected(boolean state) {
        isSelected = state;

        if (state) {
            ivMenuLevelOne.setImageResource(AISUtils.getMipmapResourceByFilename(context, menu.getImage()));
            ivMenuLevelOne.setBackgroundResource(R.drawable.shape_menu_lv1_icon_bg);
        } else {
            ivMenuLevelOne.setImageResource(AISUtils.getMipmapResourceByFilename(context, menu.getImage() + "_white"));
            ivMenuLevelOne.setBackgroundResource(0);
        }
    }

    public interface OnMenuLevelOneClickListener {
        void onMenuLevelOneClick(Menu menu, MenuLevelOneRenderer renderer, boolean isAlreadySelected);
    }
}
