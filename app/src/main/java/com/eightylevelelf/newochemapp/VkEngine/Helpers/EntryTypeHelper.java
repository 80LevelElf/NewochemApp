package com.eightylevelelf.newochemapp.VkEngine.Helpers;

import com.eightylevelelf.newochemapp.VkEngine.Entities.WallEntryType;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Parents on 14.08.2016.
 */
public class EntryTypeHelper {
    public static WallEntryType get(JSONObject jsonEntry) throws JSONException {
        if (isSurvey(jsonEntry))
            return WallEntryType.Survey;

        return WallEntryType.Unknown;
    }

    private static boolean isSurvey(JSONObject jsonEntry) throws JSONException {
        JSONObject pollAttachment = JSONWallHelper.getAttachment
                (jsonEntry.optJSONArray("attachments"), "poll");

        return (pollAttachment != null);
    }
}
