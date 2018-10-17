package com.igor.hamsteratlas.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public class ClientInfo {
    public static String getOsVersion() {
        return  "Android " + Build.VERSION.RELEASE;
    }
    public static String getClientVersion(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        String appName =  stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);

        String version;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            version = "";
        }
        return appName + " " + version;
    }

    public static String getDeviceInfo() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }
}
