package app.devzeus.dictvip.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class X_Entry implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if(!lpparam.packageName.equals("com.ticktalk.dictionary")) {
            XposedBridge.log("TArget app not found. Module is inactive");
        }else {
            XposedBridge.log("Target app found. Hook started");
            XposedHelpers.findAndHookMethod("com.appgroup.premium.PremiumHelperBase", lpparam.classLoader,
                    "isUserPremium", new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            param.setResult(true);
                            XposedBridge.log("Hook complete. Dictionary app is now premium");
                        }
                    });
        }
    }
}
