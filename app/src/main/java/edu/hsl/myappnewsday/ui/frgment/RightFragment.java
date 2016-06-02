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
public class RightFragment extends Fragment {
    private static final String TAG = "RightFragment";


    public RightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right, container, false);

        return view;
    }


}
