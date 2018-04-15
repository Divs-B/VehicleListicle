package com.example.divya.vehiclelisticle.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divya.vehiclelisticle.R;
import com.example.divya.vehiclelisticle.model.Vehicle;


public class SharedItemFragment extends Fragment {

    private static final String EXTRA = "vehicle";

    public static SharedItemFragment newInstance(Vehicle vehicle) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA, vehicle);
        SharedItemFragment fragment = new SharedItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shareditemfragment, container, false);
        final Vehicle vehicle = (Vehicle) getArguments().getSerializable(EXTRA);
        final TextView txtview = (TextView) view.findViewById(R.id.vehicle_desc);
        StringBuilder vehicleStringBuilder = new StringBuilder();
        vehicleStringBuilder.append("Vehicle Id - " + vehicle.getVehicleId())
                .append("\n")
                .append("Country - " + vehicle.getCountry())
                .append("\n")
                .append("Color - " + vehicle.getColor())
                .append("\n")
                .append("Type - " + vehicle.getType())
                .append("\n")
                .append("By-Default - " + vehicle.getDefaultText())
                .append("\n");
        txtview.setText(vehicleStringBuilder);

        return view;
    }

}
