package com.example.android.tether;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Store_RButton_Fragment extends Fragment implements View.OnClickListener {
    Button mButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_store_bottom, container, false);
        mButton = v.findViewById(R.id.view_car_button);
        mButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getContext(), RetrieveActivity.class);
        startActivity(i);
    }
}
