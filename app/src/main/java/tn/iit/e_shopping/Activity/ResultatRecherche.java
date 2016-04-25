package tn.iit.e_shopping.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import tn.iit.e_shopping.R;

public class ResultatRecherche extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String[] split;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_recherche);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String j =(String) b.get("magasin0");
        split = j.split("/");
        Toast.makeText(ResultatRecherche.this, split[0]+" /"+split[1] , Toast.LENGTH_SHORT).show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera

        LatLng sfax = new LatLng(34.738135, 10.760847);
        LatLng monoprix = new LatLng(34.739935, 10.760247);

        mMap.addMarker(new MarkerOptions().position(monoprix).title("monoprix (" +split[1]+"$)"));

        mMap.addMarker(new MarkerOptions().position(sfax).title("mon position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sfax));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(monoprix));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
    }
}
