package io.github.moonleeeaf.fuckwxappbrandinvokehiddenapiandnopermission;
import android.app.Application;
import android.net.wifi.WifiManager;
import android.view.View;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class Hook implements IXposedHookLoadPackage {
    
    public View fakeValue = null;
    
    public Application getApplication() throws ClassNotFoundException {
        return (Application) XposedHelpers.callStaticMethod(Class.forName("android.app.ActivityThread"), "currentApplication");
    }
    
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lp) throws Throwable {
        if ("com.tencent.mm".equals(lp.packageName)) {
            XposedBridge.hookAllMethods(
                WifiManager.class, //XposedHelpers.findClass("com.tencent.mm.plugin.appbrand.jsapi.wifi.wifisdk.internal.c",lp.classLoader),
                "getConfiguredNetworks",
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam arg0) throws Throwable {
                        return new ArrayList();
                    }
                }
            );
            
            XposedBridge.hookAllMethods(
                WifiManager.class, //XposedHelpers.findClass("com.tencent.mm.plugin.appbrand.jsapi.wifi.wifisdk.internal.c",lp.classLoader),
                "getScanResults",
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam arg0) throws Throwable {
                        return new ArrayList();
                    }
                }
            );
            
            XposedBridge.hookAllMethods(
                Class.class,
                "getField",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam mp) throws Throwable {
                        if (
                            "mServedView".equals(mp.args[0]) ||
                            "mNextServedView".equals(mp.args[0])
                        ) {
                            // fakeValue = new View(getApplication());
                            Field f = Hook.class.getDeclaredField("fakeValue");                 
                            mp.setResult(f);
                        }
                    }
                }
            );
            
            XposedBridge.hookAllMethods(
                Field.class,
                "get",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam mp) throws Throwable {
                        if ("fakeValue".equals(((Field)mp.thisObject).getName())) {
                            mp.setResult(new View(getApplication()));
                        }
                    }
                }
            );
        }
    }
    
}
