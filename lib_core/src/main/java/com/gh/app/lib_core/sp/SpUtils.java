package com.gh.app.lib_core.sp;

import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

import java.io.*;
import java.util.Base64;

public class SpUtils {

    private static String TAG = "HARMONY_DEMO";

    private static Preferences getSP(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context.getApplicationContext());
        return databaseHelper.getPreferences(TAG);
    }

    public static void setString(Context context, String key, String value) {
        getSP(context).putString(key, value).flush();
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getSP(context).getString(key, defaultValue);
    }

    public static void setBoolean(Context context, String key, Boolean value) {
        getSP(context).putBoolean(key, value).flush();
    }

    public static Boolean getBoolean(Context context, String key, Boolean defaultValue) {
        return getSP(context).getBoolean(key, defaultValue);
    }

    public static void setInt(Context context, String key, int value) {
        getSP(context).putInt(key, value).flush();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getSP(context).getInt(key, defaultValue);
    }

    public static void setObject(Context context, String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.getEncoder().encode(baos.toByteArray()));
            setString(context, key, objectVal);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T getObject(Context context, String key, Class<T> clazz) {
        String objectVal = getString(context, key, null);
        if (objectVal != null) {
            byte[] buffer = Base64.getDecoder().decode(objectVal);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
