package nu.getmostreward;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

// Useful link for categories
// https://developers.google.com/android/reference/com/google/android/gms/location/places/Place
// #constant-summary
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient
        .OnConnectionFailedListener {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.i(TAG, "No Permission granted");
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            // https://developers.google.com/places/android-api/current-place#get-current
            @SuppressWarnings("MissingPermission")
            @Override
            public boolean onMyLocationButtonClick() {
                //TODO: Any custom actions

                Log.d(TAG, "On My Location Button Clicked");
                // Get a list of places based on the current location
                if(mGoogleApiClient.isConnected()) {
                    PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                            .getCurrentPlace(mGoogleApiClient, null);

                    if (result == null) {
                        Log.d(TAG, "result is null");
                        return false;
                    }

                    result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                        @Override
                        public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                            LatLng location;
                            for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                                Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                        placeLikelihood.getPlace().getName(),
                                        placeLikelihood.getLikelihood()));
                                location = placeLikelihood.getPlace().getLatLng();
                                mMap.addMarker(new MarkerOptions()
                                        .position(location)
                                        .title(String.valueOf(placeLikelihood.getPlace().getName())));
                            }
                            // Prevent memory leak
                            likelyPlaces.release();
                        }
                    });
                }else if(mGoogleApiClient.isConnecting()){
                    Log.d(TAG, "Still trying to connect to Google API Client");
                }else{
                    if(!mGoogleApiClient.getConnectionResult(Places.PLACE_DETECTION_API).isSuccess()){
                        Log.w(TAG, String.format("%s", mGoogleApiClient.getConnectionResult(Places.PLACE_DETECTION_API).getErrorMessage()));
                    }else{
                        Log.d(TAG, "I don't know what's going on");
                    }
                }

                return false;
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // TODO
        // When the connection failed, what to do
        Log.w(TAG, "connection failed");
    }

}
