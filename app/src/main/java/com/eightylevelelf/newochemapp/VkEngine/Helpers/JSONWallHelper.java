package com.eightylevelelf.newochemapp.VkEngine.Helpers;

import android.support.annotation.Nullable;

import com.eightylevelelf.newochemapp.VkEngine.Entities.RequestResult;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Parents on 31.07.2016.
 */
public class JSONWallHelper {

    public static JSONArray getResults(VKResponse response) throws JSONException {
        return response.json.getJSONObject("response").getJSONArray("items");
    }

    public static JSONObject getAttachment(@Nullable JSONArray attachmentArray, String attachmentType) throws JSONException {
        if (attachmentArray == null)
            return null;

        for (int attIndex = 0; attIndex < attachmentArray.length(); attIndex++)
        {
            JSONObject currentAttachment = attachmentArray.getJSONObject(attIndex);
            if (currentAttachment.getString("type").equals(attachmentType))
                return currentAttachment;
        }

        return null;
    }
}
