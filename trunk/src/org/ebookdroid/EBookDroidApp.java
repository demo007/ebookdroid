package org.ebookdroid;

import org.ebookdroid.core.cache.CacheManager;
import org.ebookdroid.core.log.EmergencyHandler;
import org.ebookdroid.core.log.LogContext;
import org.ebookdroid.core.settings.SettingsManager;
import org.ebookdroid.core.utils.AndroidVersion;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class EBookDroidApp extends Application {

    public static final LogContext LCTX = LogContext.ROOT;

    public static Context context;

    public static String APP_VERSION;

    public static String APP_PACKAGE;

    public static File EXT_STORAGE;

    @Override
    public void onCreate() {
        super.onCreate();

        this.init();

        EmergencyHandler.init(this);
        LogContext.init(this);
        SettingsManager.init(this);
        CacheManager.init(this);
    }

    protected void init() {
        context = getApplicationContext();

        final PackageManager pm = getPackageManager();
        try {
            final PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            APP_VERSION = pi.versionName;
            APP_PACKAGE = pi.packageName;
            EXT_STORAGE = Environment.getExternalStorageDirectory();

            LCTX.i(getString(pi.applicationInfo.labelRes) + " (" + APP_PACKAGE + ")" + " v" + APP_VERSION + "("
                    + pi.versionCode + ")");

            LCTX.i("Root             dir: " + Environment.getRootDirectory());
            LCTX.i("Data             dir: " + Environment.getDataDirectory());
            LCTX.i("External storage dir: " + Environment.getExternalStorageDirectory());
            LCTX.i("Files            dir: " + getFilesDir().getAbsolutePath());
            LCTX.i("Cache            dir: " + getCacheDir().getAbsolutePath());
            //LCTX.i("External cache   dir: " + getExternalCacheDir().getAbsolutePath());

            LCTX.i("VERSION     : " + AndroidVersion.VERSION);
            LCTX.i("BOARD       : " + Build.BOARD);
            LCTX.i("BRAND       : " + Build.BRAND);
            //LCTX.i("CPU_ABI     : " + Build.CPU_ABI);
            //LCTX.i("CPU_ABI2    : " + Build.CPU_ABI2);
            LCTX.i("DEVICE      : " + Build.DEVICE);
            LCTX.i("DISPLAY     : " + Build.DISPLAY);
            LCTX.i("FINGERPRINT : " + Build.FINGERPRINT);
            //LCTX.i("HARDWARE    : " + Build.HARDWARE);
            LCTX.i("ID          : " + Build.ID);
            //LCTX.i("MANUFACTURER: " + Build.MANUFACTURER);
            LCTX.i("MODEL       : " + Build.MODEL);
            LCTX.i("PRODUCT     : " + Build.PRODUCT);
            
        } catch (final NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
