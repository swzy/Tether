package com.example.android.tether;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoreFragment extends Fragment {
    TextView mInstruction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_store_top, container, false);
        // mInstruction = v.findViewById(R.id.store_car_text);

        // Inflate the layout for this fragment
        return v;
    }
}
