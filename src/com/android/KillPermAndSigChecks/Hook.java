package com.android.KillPermAndSigChecks;

import java.lang.reflect.Method;

import android.content.Context;
import android.util.Log;
import com.saurik.substrate.*;

public class Hook {
	private static String _TAG = "KillPermAndSigChecks";
	   static void initialize() {

            MS.hookClassLoad("android.content.ContextWrapper", new MS.ClassLoadHook() {
                public void classLoaded(Class<?> _class) {
                    hookMethod(_class, "checkCallingOrSelfPermission", 
                    		new Class<?>[] {String.class}, 
                    		android.content.pm.PackageManager.PERMISSION_GRANTED);
                    hookMethod(_class, "checkCallingOrSelfUriPermission", 
                    		new Class<?>[] {android.net.Uri.class, Integer.TYPE}, 
                    		android.content.pm.PackageManager.PERMISSION_GRANTED);
                    hookMethod(_class, "checkCallingUriPermission", 
                    		new Class<?>[] {android.net.Uri.class, Integer.TYPE}, 
                    		android.content.pm.PackageManager.PERMISSION_GRANTED);
                    hookMethod(_class, "checkPermission", 
                    		new Class<?>[] {String.class, Integer.TYPE, Integer.TYPE}, 
                    		android.content.pm.PackageManager.PERMISSION_GRANTED);
                    hookMethod(_class, "checkUriPermission", 
                    		new Class<?>[] {android.net.Uri.class, Integer.TYPE, 
                    						Integer.TYPE, Integer.TYPE}, 
                    		android.content.pm.PackageManager.PERMISSION_GRANTED);
                    hookMethod(_class, "checkUriPermission", 
                    		new Class<?>[] {android.net.Uri.class, String.class, String.class, 
                    						Integer.TYPE, Integer.TYPE, Integer.TYPE}, 
                    		android.content.pm.PackageManager.PERMISSION_GRANTED);
                }
            });
            
/*
            MS.hookClassLoad("com.android.server.DevicePolicyManagerService", new MS.ClassLoadHook() {
                public void classLoaded(Class<?> _class) {
                    hookMethod(_class, "enforceCrossUserPermission", 
                    		new Class<?>[] {Integer.TYPE}, 
                    		null);
                }
            });
*/
            
            // TODO hook these methods only per app as it breaks the system for now
            /*MS.hookClassLoad("java.security.Signature", new MS.ClassLoadHook() {
                public void classLoaded(Class<?> _class) {
                    hookMethod(_class, "verify", 
                    		new Class<?>[] {byte[].class}, 
                    		true);
                    hookMethod(_class, "verify", 
                    		new Class<?>[] {byte[].class, Integer.TYPE, Integer.TYPE}, 
                    		true);
               }
            });*/
            
            // TODO, need to hook getPackage() and return a PM with no checks
            /*MS.hookClassLoad("android.content.pm.PackageManager", new MS.ClassLoadHook() {
                public void classLoaded(Class<?> _class) {
                // implement the methods below with the PM:
                    "checkSignatures", new Class<?>[] {Integer.TYPE, Integer.TYPE}, 
                    		-> android.content.pm.PackageManager.SIGNATURE_MATCH
                    "checkSignatures", new Class<?>[] {String.class, String.class}, 
                    		-> android.content.pm.PackageManager.SIGNATURE_MATCH);
                    "checkPermission", new Class<?>[] {String.class, String.class}, 
                    		-> android.content.pm.PackageManager.PERMISSION_GRANTED);
                }
            });*/
        }

	protected static void hookMethod(Class<?> _class, String methodName,
									Class<?>[] params, final Object ret) {
        Method method;
        
        try {
            method = _class.getMethod(methodName, params);
        } catch (NoSuchMethodException e) {
            method = null;
            Log.i(_TAG, "NoSuchMethod: " + methodName + " with " + 
            			params.length + " args" + ", Error: " + e);
	        for (int j = 0; j < params.length; j++) 
	        	Log.i(_TAG, "Arg "+ (j+1) +" type: " + params[j]);
        }
 
        if (method != null) {
        	Log.i(_TAG, "Hooking " + methodName + "() with " + params.length + " args");
            MS.hookMethod(_class, method,
    			new MS.MethodAlteration<Context, Object>() {
                public Object invoked(final Context hooked,
                		final Object... args) throws Throwable {
                			return ret;
                }
        		});
        }
	}
}
