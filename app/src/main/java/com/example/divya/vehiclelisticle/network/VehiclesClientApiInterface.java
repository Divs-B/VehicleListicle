package com.example.divya.vehiclelisticle.network;

import com.example.divya.vehiclelisticle.model.Vehicle;
import com.example.divya.vehiclelisticle.model.VehicleResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VehiclesClientApiInterface {

    /* Get list of vehicles */
    @GET("vehicles")
    Observable<VehicleResponse> getVehicles();
}