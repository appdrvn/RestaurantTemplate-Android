package com.appdrvn.templateone.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appdrvn.templateone.R;
import com.appdrvn.templateone.adapters.RestaurantAdapter;
import com.appdrvn.templateone.delegates.Constants;
import com.appdrvn.templateone.delegates.DialogsDelegates;
import com.appdrvn.templateone.dialogs.RestaurantDialog;
import com.appdrvn.templateone.models.Restaurant;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

/**
 * Created by kelvynlaw on 10/09/2017.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    LatLng mLastLatLng;
    private static final float ZOOM_LEVEL = 16;
    boolean mIsConnected = false;
    Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, null);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rootView.findViewById(R.id.my_location).setOnClickListener(myLocationClickListener);

        initGoogleApi();

        return rootView;
    }

    View.OnClickListener myLocationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getMyLocation();
        }
    };


    private void initGoogleApi() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {
                            mIsConnected = true;
                            if (mMap != null) {
//                                firstStart = false;
                                getMyLocation();
                            }
                        }

                        @Override
                        public void onConnectionSuspended(int i) {

                        }
                    })
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }

    }

    public void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Dexter.withActivity(getActivity())
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            getMyLocation();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                    }).check();
        } else {
            if (!checkLocationService(getActivity())) {
                DialogsDelegates.showConfirmation(getActivity(), getResources().getString(R.string.location_error_prompt), "", getResources().getString(R.string.yes), getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        }
                    }
                });
                return;
            }

            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(1000);

            try {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        mLastLatLng = new LatLng(location.getLatitude(),
                                location.getLongitude());
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLastLatLng, mMap.getCameraPosition().zoom < ZOOM_LEVEL ? ZOOM_LEVEL : mMap.getCameraPosition().zoom));
                        loadData(mLastLatLng);
                        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    private void loadData(LatLng mLastLatLng) {
        ArrayList<Restaurant> restaurants = Restaurant.createDummies(5);
        for(int i=0;i<restaurants.size();i++){
            plotMarker(restaurants.get(i));
        }

    }

    public static boolean checkLocationService(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return gps_enabled || network_enabled;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setPadding(0,getResources().getDimensionPixelSize(R.dimen.status_bar_height),0,0);
        mMap.setOnCameraMoveListener(cameraMoveListener);
        mMap.setOnMarkerClickListener(markerClickListener);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Dexter.withActivity(getActivity())
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            mMap.setMyLocationEnabled(true);
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                            mMap.getUiSettings().setMapToolbarEnabled(false);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                    }).check();
        } else {
            if (!checkLocationService(getActivity())) {
                DialogsDelegates.showConfirmation(getActivity(), getResources().getString(R.string.location_error_prompt), "", getResources().getString(R.string.yes), getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        }
                    }
                });
            }else {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mMap.getUiSettings().setMapToolbarEnabled(false);
            }
        }
        if (mIsConnected) {
            getMyLocation();
        }
        loadData(mMap.getCameraPosition().target);
    }

    GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            Restaurant restaurant = gson.fromJson(marker.getSnippet(),Restaurant.class);
            RestaurantDialog bankInfoDialog = RestaurantDialog.newInstance(restaurant);
            bankInfoDialog.show(getChildFragmentManager().beginTransaction(), "dialog");
            return false;
        }
    };

    GoogleMap.OnCameraMoveListener cameraMoveListener = new GoogleMap.OnCameraMoveListener() {
        @Override
        public void onCameraMove() {
            loadData(mMap.getCameraPosition().target);
        }
    };

    private void plotMarker(Restaurant restaurant) {
        String json = gson.toJson(restaurant);

        mMap.addMarker(new MarkerOptions()
                .position(restaurant.latLng)
                .snippet(json)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_green)));

    }
}
