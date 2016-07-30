package com.eightylevelelf.newochemapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

/**
 * Created by Parents on 30.07.2016.
 */
public class CurrentSurveyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TestLoad();
        return super.onCreateView( inflater, container, savedInstanceState);
    }

    private void TestLoad()
    {
        VKRequest request = VKApi.wall().get(VKParameters.from("domain", "newochem",
                VKApiConst.COUNT, 10, VKApiConst.FILTERS, "owner"));

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }
        });
    }
}
