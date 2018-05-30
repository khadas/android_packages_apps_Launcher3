package com.android.launcher3;

import android.content.ComponentName;
import android.content.Context;

import com.android.launcher3.util.ResourceBasedOverride;

public class AppFilter implements ResourceBasedOverride {
    private static boolean mIsGoLauncher = false;

    public static AppFilter newInstance(Context context) {
        String gmsProp = Utilities.getSystemProperty("ro.com.google.gmsversion", "");
        mIsGoLauncher = null != gmsProp && gmsProp.contains(".go");
        return Overrides.getObject(AppFilter.class, context, R.string.app_filter_class);
    }

    public boolean shouldShowApp(ComponentName app) {
        if (null != app && mIsGoLauncher){
            String packageName = app.getPackageName();
            String className = app.getClassName();
            if("com.android.documentsui".equals(packageName)
                && "com.android.documentsui.LauncherActivity".equals(className)){
                return false;
            }
        }
        return true;
    }
}
