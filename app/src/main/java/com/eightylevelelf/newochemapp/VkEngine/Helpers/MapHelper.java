package com.eightylevelelf.newochemapp.VkEngine.Helpers;

import com.eightylevelelf.newochemapp.Entities.VkEntities.Survey;
import com.eightylevelelf.newochemapp.Entities.VkEntities.WallEntry;
import com.eightylevelelf.newochemapp.VkEngine.Entities.WallEntryType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rustam on 31.07.2016.
 */
public class MapHelper {

    public static Map<WallEntryType, List<WallEntry>> getWallPosts(JSONArray jsonWallPosts) throws JSONException {

        Map<WallEntryType, List<WallEntry>> resultList = new HashMap<>();

        for (int resultIndex = 0; resultIndex < jsonWallPosts.length(); resultIndex++)
        {
            JSONObject item = jsonWallPosts.getJSONObject(resultIndex);

            switch (EntryTypeHelper.get(item))
            {
                case Survey:
                    if (!resultList.containsKey(WallEntryType.Survey))
                        resultList.put(WallEntryType.Survey, new ArrayList<WallEntry>());

                    resultList.get(WallEntryType.Survey).add(getSurvey(item));
                    break;
            }
        }

        return resultList;
    }

    public static Survey getSurvey(JSONObject surveyJSON) throws JSONException {
        //We assume such structure of JSON:
        //-item
        //-----attachments
        //----------attachment[0]
        //-------------- "type" = "photo"
        //-------------- "photo" = ...
        //----------attachment[1]
        //-------------- "type" = "poll"
        //-------------- "poll" = ...

        Survey survey = new Survey(
                surveyJSON.getLong("id"),
                surveyJSON.getString("text"),
                new Date(surveyJSON.getLong("date"))
        );

        JSONObject surveyAttachment = JSONWallHelper
                .getAttachment(surveyJSON.getJSONArray("attachments"), "poll");
        JSONArray answers = surveyAttachment.getJSONObject("poll").getJSONArray("answers");

        survey.setAnswerList(getSurveyAnswerList(answers));

        return survey;
    }

    private static List<Survey.Answer> getSurveyAnswerList(JSONArray answersJSON) throws JSONException {
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
