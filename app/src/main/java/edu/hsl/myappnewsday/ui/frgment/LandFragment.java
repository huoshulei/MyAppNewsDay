package edu.hsl.myappnewsday.ui.frgment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.hsl.myappnewsday.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandFragment extends Fragment {


    public LandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_land, container, false);
    }

}
