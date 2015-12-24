package com.nextzy.allforone.view.menu;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.nextzy.allforone.view.menu.model.AISMenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Akexorcist on 7/8/15 AD.
 */
public class MenuBuilder {
    public static final String FILE_MENU_CONFIG = "menu/ais_menu.json";

    private Context context;

    public static MenuBuilder newInstance(Context context) {
        return new MenuBuilder(context);
    }

    public MenuBuilder(Context context) {
        this.context = context;
    }

    public AISMenu getMenu() {
        return getMenuFromJSON(context);
    }

    private AISMenu getMenuFromJSON(Context context) {
        String jsonMenuConfig = getStringFromAsset(context);

        Gson gson = new Gson();
        return gson.fromJson(jsonMenuConfig, AISMenu.class);
    }

    private String getStringFromAsset(Context context) {
        AssetManager assetManager = context.getAssets();
        String strMenuConfig = null;
        try {
            InputStream inputStream = assetManager.open(FILE_MENU_CONFIG);
            strMenuConfig = readTextFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strMenuConfig;
    }

    private String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }
}
