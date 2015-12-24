package com.nextzy.allforone.view.menu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuActivity;

public class MenuSupportFragment extends Fragment {
    private MenuActivity activity;

    public static MenuSupportFragment newInstance() {
        MenuSupportFragment fragment = new MenuSupportFragment();
        return fragment;
    }

    public MenuSupportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MenuActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aisapp_fragment_menu_support, container, false);

        return rootView;
    }
}
