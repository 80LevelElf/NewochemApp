package com.eightylevelelf.newochemapp.VkEngine;

import android.content.Context;

import com.eightylevelelf.newochemapp.Entities.Survey;
import com.eightylevelelf.newochemapp.Helpers.ResourceHelper;
import com.eightylevelelf.newochemapp.R;
import com.eightylevelelf.newochemapp.VkEngine.Entities.RequestError;
import com.eightylevelelf.newochemapp.VkEngine.Entities.RequestResult;
import com.eightylevelelf.newochemapp.VkEngine.Entities.ResultHolder;
import com.eightylevelelf.newochemapp.VkEngine.Helpers.MapHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rustam on 30.07.2016.
 */
public class VkRequestEngine {

    public static RequestResult<Survey> getCurrentSurvey(final Context context)
    {
        final ResultHolder<RequestResult<Survey>> holder = new ResultHolder<>();

        //Prepare request. Assume that we have at least one survey in top 10 posts
        // (it's not a so good code, but it's always true)
        VKRequest request = VKApi.wall().
                get(VKParameters.from("domain", "newochem", VKApiConst.COUNT, 10));

        //Execute request
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                //Try to find first survey and return it
                try
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
                    boolean searchNext = true;
                    JSONArray results = getResults(response);
                    for (int resultIndex = 0; resultIndex < results.length() && searchNext; resultIndex++)
                    {
                        JSONObject item = results.getJSONObject(resultIndex);
                        JSONArray attachments = item.optJSONArray("attachments");
                        if (attachments == null)
                            break;

                        for (int attIndex = 0; attIndex < attachments.length(); attIndex++)
                        {
                            JSONObject currentAttachment = attachments.getJSONObject(attIndex);
                            if (currentAttachment.getString("type").equals("poll"))
                            {
                                JSONObject survey = currentAttachment.getJSONObject("poll");
                                holder.setResult(new RequestResult<>(MapHelper.getSurvey(survey)));

                                searchNext = false; //end search
                                break;
                            }
                        }
                    }
                }
                catch (JSONException exception)
                {
                    holder.setResult(new RequestResult<Survey>
                            (new RequestError(ResourceHelper.get
                                    (context, R.string.error_json_problem), exception)));
                }
            }

            @Override
            public void onError(VKError error) {
                holder.setResult(new RequestResult<Survey>
                        (new RequestError(ResourceHelper.get(context, R.string.error_vk_access), error)));
            }
        });

        //Strange behavior
        if (holder.getResult() == null)
            return new RequestResult<>
                    (new RequestError(ResourceHelper.get(context, R.string.error_cant_find_survey)));

        //All works fine
        return holder.getResult();
    }

    private static JSONArray getResults(VKResponse response) throws JSONException {
        return response.json.getJSONObject("response").getJSONArray("items");
    }
}
