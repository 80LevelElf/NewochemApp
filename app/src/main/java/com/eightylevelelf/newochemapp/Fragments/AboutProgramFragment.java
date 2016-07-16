package com.eightylevelelf.newochemapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.eightylevelelf.newochemapp.Helpers.UrlHelper;
import com.eightylevelelf.newochemapp.R;

/**
 * Created by Parents on 29.06.2016.
 */
public class AboutProgramFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_program, container, false);

        Button groupButton = (Button) view.findViewById(R.id.about_program_group_button);
        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UrlHelper.openUrl(AboutProgramFragment.this.getActivity(), "https://vk.com/newochem");
            }
        });

        Button sourceButton = (Button) view.findViewById(R.id.about_program_source_button);
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UrlHelper.openUrl(AboutProgramFragment.this.getActivity(), "https://github.com/80LevelElf/NewochemApp");
            }
        });

        return view;
    }
}
