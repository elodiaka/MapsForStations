package joke.mapsforstations;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /* global variables here  */
    private GoogleMap mMap;
    private Button mButton;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findUIElements();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*magic happens here*/
                Toast.makeText(MapsActivity.this, "Congrats, you pressed a Button!", Toast.LENGTH_SHORT).show();
                double userLat = 52.5506291;
                double userLang = 13.4379155;
                LatLng userPosition = new LatLng(userLat,userLang);
                mMap.addMarker(new MarkerOptions().position(userPosition).title("Your position"));

                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(userPosition, 10);
                mMap.animateCamera(cameraUpdate);
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the ap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng berlin = new LatLng(52.5219184,13.411026);


        marker = mMap.addMarker(new MarkerOptions().position(berlin).title("Marker in Berlin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(berlin, 10.0f));
        onLongClickMakeMarker();
    }

    private void findUIElements() {
        mButton = findViewById(R.id.button);
    }

    private void onLongClickMakeMarker() {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (marker != null){marker.remove();}
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title("User set position"));

            }
        });
    }

/*    private void onLongClickDestroyMarker() {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("User set position"));

            }
        });

    } */
















}
//EOF