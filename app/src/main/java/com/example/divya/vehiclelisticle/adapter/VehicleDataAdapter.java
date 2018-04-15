package com.example.divya.vehiclelisticle.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divya.vehiclelisticle.R;
import com.example.divya.vehiclelisticle.activity.SharedItemActivity;
import com.example.divya.vehiclelisticle.model.Vehicle;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleDataAdapter extends RecyclerView.Adapter<VehicleDataAdapter.ViewHolder> {

    private ArrayList<Vehicle> mVehicleList;
    private final Activity activity;

    public VehicleDataAdapter(Activity activity, ArrayList<Vehicle> vehicleList) {
        this.activity = activity;
        mVehicleList = vehicleList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mVersion.setText(mVehicleList.get(position).getVersion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Pair<View, String>[] pairs = createSafeTransitionParticipants(activity, false,
                        new Pair<>(((ImageView) holder.itemView.findViewById(R.id.square_blue)), activity.getString(R.string.square_blue_name)),
                        new Pair<>(((TextView) holder.itemView.findViewById(R.id.version)), activity.getString(R.string.sample_blue_title)));
                final Vehicle vehicle = mVehicleList.get(holder.getAdapterPosition());
                startActivity(SharedItemActivity.class, pairs, vehicle);
            }
        });


    }

    /**
     * Create the transition participants required during a activity transition while
     * avoiding glitches with the system UI.
     *
     * @param activity         The activity used as start for the transition.
     * @param includeStatusBar If false, the status bar will not be added as the transition
     *                         participant.
     * @return All transition participants.
     */
    public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
                                                                        boolean includeStatusBar, @Nullable Pair... otherParticipants) {
        // Avoid system UI glitches as described here:
        View decor = activity.getWindow().getDecorView();
        View statusBar = null;
        if (includeStatusBar) {
            statusBar = decor.findViewById(android.R.id.statusBarBackground);
        }
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);

        // Create pair of transition participants.
        List<Pair> participants = new ArrayList<>(3);
        addNonNullViewToTransitionParticipants(statusBar, participants);
        addNonNullViewToTransitionParticipants(navBar, participants);
        // only add transition participants if there's at least one none-null element
        if (otherParticipants != null && !(otherParticipants.length == 1
                && otherParticipants[0] == null)) {
            participants.addAll(Arrays.asList(otherParticipants));
        }
        return participants.toArray(new Pair[participants.size()]);
    }

    private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
        if (view == null) {
            return;
        }
        participants.add(new Pair<>(view, view.getTransitionName()));
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, Vehicle vehicle) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("vehicle", vehicle);
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override
    public int getItemCount() {
        return mVehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mVersion;

        public ViewHolder(View view) {
            super(view);
            mVersion = (TextView) view.findViewById(R.id.version);
        }
    }

}

