package com.example.a707446.dominospizza;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.a707446.dominospizza.fragments.IngredientPizzaFragment;
import com.example.a707446.dominospizza.fragments.MenuPizzaFragment;

/**
 * Created by ilias afrass on 16/11/18
 **/

public class MainActivity extends AppCompatActivity implements MenuPizzaFragment.OnItemSelectedListener {

    // Declare static variable of "String" type to identify position in Bundle
    private static final String KEY_POSITION = "KEY_POSITION";

    // Declare two variable to be of a MenuPizzaFragment and IngredientPizzaFragment type
    private MenuPizzaFragment firstFragment;
    private IngredientPizzaFragment secondFragment;

    // Declare variable of "int" type to save a position
    private int position_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragments(savedInstanceState);
    }

    private void showFragments(Bundle savedInstanceState){
        // the savedInstanceState will always be null the first time an Activity is started
        if (savedInstanceState == null) {
            // Instance of MenuPizzaFragment
            firstFragment = new MenuPizzaFragment();
            // Add Fragment to FrameLayout (flContainer1), using FragmentManager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            ft.add(R.id.flContainer1, firstFragment);                               // add    Fragment to flContainer1
            ft.commit();                                                            // commit FragmentTransaction
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Instance of MenuPizzaFragment and IngrediantPizzaFragment
            firstFragment = new MenuPizzaFragment();
            secondFragment = new IngredientPizzaFragment();

            // Add Fragment to FrameLayout (flContainer1), using FragmentManager
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            ft.replace(R.id.flContainer1, firstFragment);                           // add    Fragment
            ft.commit();

            // Restore last position if possible
            if (savedInstanceState != null) {
                position_save = savedInstanceState.getInt(KEY_POSITION, 0);
            }
            // Load Pizza Ingredient Fragment
            Bundle args = new Bundle();
            args.putInt("position", position_save);
            secondFragment.setArguments(args);                                       // Communicate with Fragment using Bundle

            // Add Fragment to FrameLayout (flContainer2), using FragmentManager
            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();// begin  FragmentTransaction
            ft2.add(R.id.flContainer2, secondFragment);                              // add    Fragment
            ft2.commit();                                                            // commit FragmentTransaction

        }
    }

    @Override
    public void onPizzaItemSelected(int position) {
        Toast.makeText(this, "Appelé par Fragment Menu Pizza: position : " + position, Toast.LENGTH_SHORT).show();

        secondFragment = new IngredientPizzaFragment();

        // Load Pizza Ingredient Fragment
        position_save = position;
        Bundle args = new Bundle();
        args.putInt("position", position);
        secondFragment.setArguments(args);                      // Communicate with Fragment using Bundle


        //Orientation landscape
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer2, secondFragment) // replace flContainer
                    .addToBackStack(null)
                    .commit();
        } else {
            //Orientation Portrait
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer1, secondFragment) // replace flContainer
                    .addToBackStack(null)                       // adding the fragment in the Fragment Stack but not adding any TAG
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //Save position_save in Bundle when fragment is destroyed
        outState.putInt(KEY_POSITION, position_save);
        super.onSaveInstanceState(outState);
    }

}

