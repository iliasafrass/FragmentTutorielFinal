package com.example.a707446.dominospizza.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a707446.dominospizza.R;

/**
 * Created by ilias afrass on 16/11/18
 **/

public class RatingFragment extends Fragment {

    private TextView textView;
    private float rating = 0;

    public RatingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Get back arguments
            if (getArguments() != null) {
                rating = getArguments().getFloat("rating", 0);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView = (TextView) view.findViewById(R.id.textview);

        // update view
        updateView(rating);
    }

    // Activity is calling this to update view on Fragment
    public void updateView(float rating) {
        if (rating != 0)
            if (rating < 2.5)
                textView.setText("votre évaluation est de : " + rating + " / 5.0" + " :(");
            else
                textView.setText("votre évaluation est de : " + rating + " / 5.0" + " :)");
        else
            textView.setText("J'attends votre évaluation :)");
    }


}
