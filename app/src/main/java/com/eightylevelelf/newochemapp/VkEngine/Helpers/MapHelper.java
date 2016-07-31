package com.eightylevelelf.newochemapp.VkEngine.Helpers;

import com.eightylevelelf.newochemapp.Entities.Survey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rustam on 31.07.2016.
 */
public class MapHelper {
    public static Survey getSurvey(JSONObject surveyJSON)
    {
        //We assume such structure of JSON:
        //-item
        //-----attachments
        //----------attachment[0]
        //-------------- "type" = "photo"
        //-------------- "photo" = ...
        //----------attachment[1]
        //-------------- "type" = "poll"
        //-------------- "poll" = ...
    }

    private static List<Survey.Answer> getAnswerList(JSONArray answersJSON) throws JSONException {
        List<Survey.Answer> resultList = new ArrayList<>();

        for (int i = 0; i < answersJSON.length(); i++)
        {
            JSONObject currentAnswer = answersJSON.getJSONObject(i);

            resultList.add(new Survey.Answer
                    (currentAnswer.getString("text"), currentAnswer.getDouble("rate")));
        }

        return resultList;
    }
}
