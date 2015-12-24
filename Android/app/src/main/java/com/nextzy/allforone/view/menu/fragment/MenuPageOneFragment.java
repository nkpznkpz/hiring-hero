package com.nextzy.allforone.view.menu.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextzy.allforone.R;
import com.nextzy.allforone.view.menu.MenuActivity;
import com.nextzy.allforone.view.menu.adapter.MenuLevelTwoAdapter;
import com.nextzy.allforone.view.menu.model.Menu;

import org.parceler.Parcels;

public class MenuPageOneFragment extends Fragment implements MenuLevelTwoAdapter.OnItemClickListener {
    private static final String KEY_MENU = "key_menu";
    private MenuActivity activity;
    private MenuPageFragment parentFragment;
    private RecyclerView rvMenuContent;

    public static MenuPageOneFragment newInstance(Menu menu) {
        MenuPageOneFragment fragment = new MenuPageOneFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_MENU, Parcels.wrap(menu));
        fragment.setArguments(bundle);
        return fragment;
    }

    public MenuPageOneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentFragment = (MenuPageFragment) getParentFragment();
        activity = (MenuActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aisapp_fragment_menu_page_one, container, false);

        Menu menu = Parcels.unwrap(getArguments().getParcelable(KEY_MENU));

        rvMenuContent = (RecyclerView) rootView.findViewById(R.id.rv_menu_content);
        MenuLevelTwoAdapter adapter = new MenuLevelTwoAdapter(activity, menu.getMenuListByBusinessType());
        setRecyclerViewColumnCount();
        rvMenuContent.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onItemClickListener(View view, int position, Menu menu) {
        parentFragment.onMenuLevelTwoSelected(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRecyclerViewColumnCount();
    }

    private void setRecyclerViewColumnCount() {
        int columnCount = getResources().getInteger(R.integer.menu_column_count);
        rvMenuContent.setLayoutManager(new GridLayoutManager(getActivity(), columnCount, GridLayoutManager.VERTICAL, false));
    }
}
