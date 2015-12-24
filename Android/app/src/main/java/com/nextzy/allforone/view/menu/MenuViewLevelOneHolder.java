package com.nextzy.allforone.view.menu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nextzy.allforone.R;

/**
 * Created by Akexorcist on 7/6/15 AD.
 */
public class MenuViewLevelOneHolder extends RecyclerView.ViewHolder {
    public TextView tvMenuTitle;
    public ImageView ivMenuImage;
    public LinearLayout layoutMenuButton;

    public MenuViewLevelOneHolder(View view) {
        super(view);
        tvMenuTitle = (TextView) view.findViewById(R.id.tv_menu_title);
        ivMenuImage = (ImageView) view.findViewById(R.id.iv_menu_image);
        layoutMenuButton = (LinearLayout) view.findViewById(R.id.layout_menu_button);
    }
}
