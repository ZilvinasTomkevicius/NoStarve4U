package com.example.zilvinastomkevicius.nostarve4u.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zilvinastomkevicius.nostarve4u.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecipeFragment extends Fragment {


    public AddRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_recipe, container, false);
    }

}
