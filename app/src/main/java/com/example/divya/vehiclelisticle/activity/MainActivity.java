package com.example.divya.vehiclelisticle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.divya.vehiclelisticle.R;
import com.example.divya.vehiclelisticle.Util;
import com.example.divya.vehiclelisticle.adapter.VehicleDataAdapter;
import com.example.divya.vehiclelisticle.model.Vehicle;
import com.example.divya.vehiclelisticle.model.VehicleResponse;
import com.example.divya.vehiclelisticle.network.VehiclesClientApiInterface;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://private-6d86b9-vehicles5.apiary-mock.com/";
    private RecyclerView mRecyclerView;
    private CompositeDisposable mCompositeDisposable;
    private VehicleDataAdapter mAdapter;
    private ArrayList<Vehicle> mVehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            mCompositeDisposable = new CompositeDisposable();
            initRecyclerView();
            if (Util.isConnected(this)) {
                loadVehicleJSON();
            } else {
                Toast.makeText(this, R.string.no_network, Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            e.getMessage();
        }

    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadVehicleJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        VehiclesClientApiInterface apiService = retrofit.create(VehiclesClientApiInterface.class);

        Observable<VehicleResponse> observable = apiService.getVehicles().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<VehicleResponse>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Error in fetching data. " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(VehicleResponse response) {
                ArrayList list = new ArrayList<Vehicle>();
                List<Vehicle> vehicles = response.getVehicles();
                for(int i = 0; i < vehicles.size(); i++){
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(vehicles.get(i).getVehicleId());
                    vehicle.setVersion(vehicles.get(i).getVersion());
                    vehicle.setCountry(vehicles.get(i).getCountry());
                    vehicle.setColor(vehicles.get(i).getColor());
                    vehicle.setType(vehicles.get(i).getType());
                    vehicle.setDefaultText(vehicles.get(i).getDefaultText());
                    list.add(vehicle);
                }
                setListLayout(list);
            }
        });
    }

    private void setListLayout(ArrayList list){
        mVehicleList = new ArrayList<>(list);
        mAdapter = new VehicleDataAdapter(MainActivity.this, mVehicleList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}

