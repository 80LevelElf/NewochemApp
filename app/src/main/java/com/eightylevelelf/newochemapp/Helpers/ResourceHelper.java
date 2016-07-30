package com.eightylevelelf.newochemapp.Helpers;

import android.content.Context;

/**
 * Created by Rustam on 31.07.2016.
 */
public class ResourceHelper {
    public static String get(Context context, int resourceId)
    {
        return context.getResources().getString(resourceId);
    }
}
