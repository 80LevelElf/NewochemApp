package com.eightylevelelf.newochemapp.VkEngine;

import android.content.Context;
import android.util.Log;

import com.eightylevelelf.newochemapp.Entities.VkEntities.Survey;
import com.eightylevelelf.newochemapp.Entities.VkEntities.WallEntry;
import com.eightylevelelf.newochemapp.Settings.LogSettings;
import com.eightylevelelf.newochemapp.Helpers.ResourceHelper;
import com.eightylevelelf.newochemapp.R;
import com.eightylevelelf.newochemapp.Settings.VkEngineSettings;
import com.eightylevelelf.newochemapp.VkEngine.Entities.WallEntryType;
import com.eightylevelelf.newochemapp.VkEngine.Entities.RequestError;
import com.eightylevelelf.newochemapp.VkEngine.Entities.RequestResult;
import com.eightylevelelf.newochemapp.VkEngine.Entities.ResultHolder;
import com.eightylevelelf.newochemapp.VkEngine.Helpers.JSONWallHelper;
import com.eightylevelelf.newochemapp.VkEngine.Helpers.MapHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

/**
 * Created by Rustam on 30.07.2016.
 */
public class VkRequestEngine {

    public static RequestResult<Survey> getCurrentSurvey(final Context context)
    {
        return getCurrentSurvey(context, 0);
    }

    private static RequestResult<Survey> getCurrentSurvey(final Context context, final int currentIteration)
    {
        final ResultHolder<RequestResult<Survey>> holder = new ResultHolder<>();

        VKRequest request = VKApi.wall().
                get(VKParameters.from(
                        "domain", "newochem",
                        VKApiConst.COUNT, VkEngineSettings.CountOfGettingSurveys));

        //Execute request
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                //Try to find first survey and return it
                try
                {
                    JSONArray jsonResponse = JSONWallHelper.getResults(response);

                    Map<WallEntryType, List<WallEntry>> results = MapHelper.getWallPosts(jsonResponse);
                    List<WallEntry> allSurveys = results.get(WallEntryType.Survey);

                    if (allSurveys == null || allSurveys.size() == 0)
                    {
                        if (currentIteration < VkEngineSettings.MaxCountOfGetSurveyRequests)
                        {
                            //One more attempt
                            holder.setResult(getCurrentSurvey(context, currentIteration + 1));
                        }
                        else
                        {
                            Log.e(LogSettings.VK_ACCESS_ERROR, "Exceeded max count of get survey attempts.");
                            holder.setResult(new RequestResult<Survey>
                                    (new RequestError(ResourceHelper.get(context, R.string.error_vk_access))));
                        }

                        return;
                    }

                    holder.setResult(new RequestResult<>((Survey) allSurveys.get(0)));
                }
                catch (JSONException exception)
                {
                    Log.e(LogSettings.VK_PARSE_ERROR, "Can't parse survey response: " + exception.getMessage());
                    holder.setResult(new RequestResult<Survey>
                            (new RequestError(ResourceHelper.get
                                    (context, R.string.error_json_problem), exception)));
                }
            }

            @Override
            public void onError(VKError error) {
                Log.e(LogSettings.VK_ACCESS_ERROR, "Can't get access to VK: " + error.errorMessage);
                holder.setResult(new RequestResult<Survey>
                        (new RequestError(ResourceHelper.get(context, R.string.error_vk_access), error)));
            }
        });

        //Strange behavior
        if (holder.getResult() == null)
        {
            Log.e(LogSettings.VK_ACCESS_ERROR, "Strange behavior of getting access to VK.");
            return new RequestResult<>(new RequestError(ResourceHelper.get(context, R.string.error_cant_find_survey)));
        }

        //All works fine
        return holder.getResult();
    }
}