package com.example.a707446.dominospizza.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.dominospizza.DB.Pizza;
import com.example.a707446.dominospizza.R;

/**
 * Created by ilias afrass on 16/11/18
 **/

public class IngredientPizzaFragment extends Fragment {
    private int position = 0;
    private TextView tvTitre;
    private TextView tvIngredient;
    private RatingBar ratingBar;
    private float rating_save;

    public IngredientPizzaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Get back arguments
            if (getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_ingredient_pizza, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Set values for view here
        tvTitre = (TextView) view.findViewById(R.id.tvTitre);
        tvIngredient = (TextView) view.findViewById(R.id.tvIngredient);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        showView(savedInstanceState);
        // update view
        updateView(position);
    }

    private void showView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            RatingFragment ratingFragment = new RatingFragment();
            //Add Fragment to FrameLayout (containerIngredient), using FragmentManager
            fragmentTransaction.add(R.id.containerIngredient, ratingFragment);                               // add    Fragment
            fragmentTransaction.commit();
            addListenerOnRatingBar();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (savedInstanceState != null) {
                rating_save = savedInstanceState.getFloat("key_rating", 0);
            }
            RatingFragment ratingFragment = new RatingFragment();
            Bundle args = new Bundle();
            args.putFloat("rating", rating_save);
            ratingFragment.setArguments(args);          //Communicate with Fragment using Bundle
            //Add Fragment to FrameLayout (containerIngredient), using FragmentManager
            fragmentTransaction.replace(R.id.containerIngredient, ratingFragment);                               // add    Fragment
            fragmentTransaction.commit();

            addListenerOnRatingBar();
        }
    }

    // Activity is calling this to update view on Fragment
    public void updateView(int position) {
        tvTitre.setText(Pizza.Menupizza[position]);
        tvIngredient.setText(Pizza.pizzaIngredients[position]);
    }

    public void addListenerOnRatingBar() {
        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                Toast.makeText(getContext(), "évaluation = " + rating, Toast.LENGTH_SHORT).show();
                RatingFragment ratingFragment = new RatingFragment();
                rating_save = rating;
                Bundle args = new Bundle();
                args.putFloat("rating", rating);
                ratingFragment.setArguments(args);          // Communicate with Fragment using Bundle

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerIngredient, ratingFragment) // replace flContainer
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Save rating_save in Bundle when fragment is destroyed
        outState.putFloat("key_rating", rating_save);
        super.onSaveInstanceState(outState);
    }
}
