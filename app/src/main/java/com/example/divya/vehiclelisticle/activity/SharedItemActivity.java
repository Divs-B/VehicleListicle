package com.example.divya.vehiclelisticle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;


import com.example.divya.vehiclelisticle.R;
import com.example.divya.vehiclelisticle.fragment.SharedItemFragment;
import com.example.divya.vehiclelisticle.model.Vehicle;


public class SharedItemActivity extends AppCompatActivity {
    static final String EXTRA = "vehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vehicle vehicle = (Vehicle) getIntent().getExtras().getSerializable(EXTRA);
        bindData(vehicle);
        setupWindowAnimations();
        setupLayout(vehicle);
        setupToolbar();
    }

    private void bindData(Vehicle vehicle) {
        android.databinding.ViewDataBinding binding = android.databinding.DataBindingUtil.setContentView(this, R.layout.activity_shareditem);
        binding.setVariable(1,vehicle);
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(Vehicle vehicle) {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        SharedItemFragment sharedItemFragment = SharedItemFragment.newInstance(vehicle);
        sharedItemFragment.setReenterTransition(slideTransition);
        sharedItemFragment.setExitTransition(slideTransition);
        sharedItemFragment.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedItemFragment)
                .commit();
    }

    void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}
