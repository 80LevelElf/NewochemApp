package com.eightylevelelf.newochemapp.Helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Rustam on 02.07.2016.
 */
public final class UrlHelper {
    public static void openUrl(Activity parentActivity, String url)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        parentActivity.startActivity(browserIntent);
    }
}
