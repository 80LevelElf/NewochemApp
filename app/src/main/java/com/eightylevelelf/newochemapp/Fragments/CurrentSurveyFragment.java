package com.eightylevelelf.newochemapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eightylevelelf.newochemapp.Entities.VkEntities.Survey;
import com.eightylevelelf.newochemapp.VkEngine.Entities.RequestResult;
import com.eightylevelelf.newochemapp.VkEngine.VkRequestEngine;

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
        RequestResult<Survey> result = VkRequestEngine.getCurrentSurvey(getContext());
    }
}
