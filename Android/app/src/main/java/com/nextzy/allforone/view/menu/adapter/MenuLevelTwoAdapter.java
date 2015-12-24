package com.nextzy.allforone.view.menu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuUtils;
import com.nextzy.allforone.view.menu.MenuViewLevelOneHolder;
import com.nextzy.allforone.view.menu.model.Menu;
import com.nextzy.lib.allforone.util.AISUtils;

import java.util.List;

/**
 * Created by Akexorcist on 7/6/15 AD.
 */
public class MenuLevelTwoAdapter extends RecyclerView.Adapter<MenuViewLevelOneHolder> {
    private Context context;
    private List<Menu> menu;
    private OnItemClickListener listener;

    public MenuLevelTwoAdapter(Context context, List<Menu> menu) {
        this.context = context;
        this.menu = menu;
    }

    @Override
    public MenuViewLevelOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aisapp_view_menu_level_two, parent, false);
        return new MenuViewLevelOneHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewLevelOneHolder holder, final int position) {
        final Menu menuItem = menu.get(position);
        holder.tvMenuTitle.setText(MenuUtils.getMenuName(menuItem));
        int imgResId = AISUtils.getMipmapResourceByFilename(context, menuItem.getImage());
        holder.ivMenuImage.setImageResource(imgResId);
        holder.ivMenuImage.setBackgroundResource(setImageBackgroundResource(imgResId));
        holder.layoutMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClickListener(v, position, menuItem);
                }
            }
        });
    }

    private int setImageBackgroundResource(int imgResId) {
        if(imgResId == R.mipmap.aisapp_ic_menu_lv2_eservice) {
            return R.drawable.shape_menu_lv2_icon_bg_eservice;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_other1) {
            return R.drawable.shape_menu_lv2_icon_bg_other1;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_other2) {
            return R.drawable.shape_menu_lv2_icon_bg_other2;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_other3) {
            return R.drawable.shape_menu_lv2_icon_bg_other3;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_other4) {
            return R.drawable.shape_menu_lv2_icon_bg_other4;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_other5) {
            return R.drawable.shape_menu_lv2_icon_bg_other5;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_other6) {
            return R.drawable.shape_menu_lv2_icon_bg_other6;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_privilege) {
            return R.drawable.shape_menu_lv2_icon_bg_privilege;
        } else if(imgResId == R.mipmap.aisapp_ic_menu_lv2_roaming) {
            return R.drawable.shape_menu_lv2_icon_bg_roaming;
        }
        return R.color.light_gray;
    }

    @Override
    public int getItemCount() {
        return (menu != null) ? menu.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int position, Menu menu);
    }
}