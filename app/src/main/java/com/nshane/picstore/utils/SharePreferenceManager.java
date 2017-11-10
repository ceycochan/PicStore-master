package com.nshane.picstore.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 郑州啊日天
 */
public class SharePreferenceManager {

    //定义用于SETTING_DOMAIN的xml文件名称，以及xml文件的所有键名与对应的默认键值


    public static class FBInfoXml {
        // xml文件名称
        public static final String XML_NAME = "FACEBOOK_USER_INFO";

        public static final KEY<String> KEY_FACEBOOK_TOKEN = new KEY<String>("fb_token", "");
        public static final KEY<String> KEY_FACEBOOK_UID = new KEY<String>("fb_uid", "d71d61ce99fb4b9aa5df37a461266188");//    3c7fcf7267114cd4a4b7196408c45ff8
        public static final KEY<String> KEY_FACEBOOK_NAME = new KEY<String>("fb_name", "");
        public static final KEY<String> KEY_FACEBOOK_ICON = new KEY<String>("fb_icon", "");
        public static final KEY<String> KEY_FACEBOOK_SEX = new KEY<String>("fb_sex", "");
        public static final KEY<String> KEY_FACEBOOK_COUNTRY = new KEY<String>("fb_country", "");
        public static final KEY<Integer> KEY_FACEBOOK_GOLD_NUM = new KEY<Integer>("fb_gold_num", 0);
        public static final KEY<Integer> KEY_FACEBOOK_FOLLOWED_NUM = new KEY<Integer>("fb_followed_num", 0);
        public static final KEY<Integer> KEY_FACEBOOK_LIKE_NUM = new KEY<Integer>("fb_like_num", 0);
        public static final KEY<Integer> KEY_FACEBOOK_BOTTLE_NUM = new KEY<Integer>("fb_bottle_num", 0);
    }

    public static class BGInfoXml {
        // xml文件名称
        public static final String XML_NAME = "BACKGROUND_INFO";
        public static final KEY<Integer> BACKGROUND = new KEY<Integer>("bg_main", 0);
    }

    public static class LikeInfoXml {
        // xml文件名称
        public static final String XML_NAME = "LIKE_INFO";
    }


    //获取
    public static String getString(Context ctx, String xmlName, String key, String defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    //设置
    public static void setString(Context ctx, String xmlName, String key, String value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static Long getLong(Context ctx, String xmlName, String key, Long defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    //设置
    public static void setLong(Context ctx, String xmlName, String key, Long value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public static int getInt(Context ctx, String xmlName, String key, int defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    //设置
    public static void setInt(Context ctx, String xmlName, String key, int value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static Boolean getBoolean(Context ctx, String xmlName, String key, Boolean defaultValue) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    //设置
    public static void setBoolean(Context ctx, String xmlName, String key, Boolean value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 用于设置key的名称和默认值,key值运用泛型
     */
    public static class KEY<T> {
        public final String key;// 键的名称
        public final T defaultValue;// 键的默认值

        public KEY(String key, T defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }
    }

}
