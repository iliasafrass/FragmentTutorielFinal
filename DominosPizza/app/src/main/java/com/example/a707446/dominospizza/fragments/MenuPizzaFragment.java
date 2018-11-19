package com.example.a707446.dominospizza.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a707446.dominospizza.DB.Pizza;
import com.example.a707446.dominospizza.R;

/**
 * Created by ilias afrass on 16/11/18
 **/

public class MenuPizzaFragment extends Fragment {

    ArrayAdapter<String> itemsAdapter;
    private OnItemSelectedListener mListener;

    public MenuPizzaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init itemsAdapter
        itemsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Pizza.Menupizza);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_pizza, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ListView lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);

        //Set onItemClickListener to List lvItems
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Here is handled the list click

                /*
                 Communicate with Activity using Listener
                 go to activity to load pizza details fragment
                 */
                mListener.onPizzaItemSelected(position);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            //Call the method that creating OnItemSelectedListener after being attached to parent activity
            mListener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemSelectedListener");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment (MainActivity) to allow an interaction in this fragment to be communicated
     * to the activity.
     */
    public interface OnItemSelectedListener {
        void onPizzaItemSelected(int position);
    }

}
