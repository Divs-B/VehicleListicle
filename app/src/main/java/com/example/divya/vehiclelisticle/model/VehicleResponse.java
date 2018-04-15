package com.example.divya.vehiclelisticle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleResponse {

        @SerializedName("vehicles")
        @Expose
        private List<Vehicle> vehicles = null;

        public List<Vehicle> getVehicles() {
            return vehicles;
        }

        public void setVehicles(List<Vehicle> vehicle) {
            this.vehicles = vehicle;
        }
}
