package com.igor.hamsteratlas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igor.hamsteratlas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HamstersFragment extends Fragment {


    public HamstersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hamsters, container, false);
        return v;
    }

}
