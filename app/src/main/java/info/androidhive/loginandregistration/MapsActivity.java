package info.androidhive.loginandregistration;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
/*        mMap.setBuildingsEnabled(true);
        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
        UiSettings mapSettings;
        mapSettings = mMap.getUiSettings();
        mapSettings.setTiltGesturesEnabled(true);
        mapSettings.setZoomControlsEnabled(true);

        LatLng MUSEUM = new LatLng(38.8874245, -77.0200729);
        Marker museum = mMap.addMarker(new MarkerOptions()
                .position(MUSEUM)
                .title("Museum")
                .snippet("National Air and Space Museum"));

        LatLng MUSEUM1 = new LatLng(35.8874245, -70.0200729);
        Marker museum1 = mMap.addMarker(new MarkerOptions()
                .position(MUSEUM1)
                .title("Museum")
                .snippet("National Air and Space Museum"));*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */






    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }














    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));

        // Enable MyLocation Layer of Google Map
        mMap.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Get latitude of the current location
        double latitude = myLocation.getLatitude();

        // Get longitude of the current location
        double longitude = myLocation.getLongitude();

        // Create a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Show the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        /*LatLng myCoordinates = new LatLng(latitude, longitude);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(myCoordinates, 12);//
        mMap.animateCamera(yourLocation);*/


        Marker marker1 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("You are here!").snippet("Consider yourself located"));
        Marker marker2 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude+0.03, longitude+0.022)).title("khaled fares???????!").snippet("Consider yourself located"));
        Marker marker3 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude+0.02, longitude-0.03)).title("khaled fares!").snippet("Consider yourself located"));
        Marker marker4 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude-0.01, longitude+0.01)).title("khaled fares!").snippet("Consider yourself located"));
        Marker marker5 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude+0.03, longitude-0.01)).title("khaled fares!").snippet("Consider yourself located"));
        Marker marker6 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude-0.00, longitude+0.02)).title("khaled fares!").snippet("Consider yourself located"));
        Marker marker7 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude+0.03, longitude-0.02)).title("khaled fares!").snippet("Consider yourself located"));
        Marker marker8 = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude-0.02, longitude+0.02)).title("khaled fares!").snippet("Consider yourself located"));

        marker1.setIcon(BitmapDescriptorFactory.defaultMarker(200));
        double d2 = distance(latitude,longitude,latitude+0.03, longitude+0.022);
        double d3 = distance(latitude,longitude,latitude+0.02, longitude-0.03);
        double d4 = distance(latitude,longitude,latitude-0.01, longitude+0.01);
        double d5 = distance(latitude,longitude,latitude+0.025,longitude-0.01);

        String mark;
        double min=d2;
        if (d3 < min)
            min=d3;
        if (d4 < min)
            min=d4;
        if (d5 < min)
            min=d5;

        if(min==d2)
            marker2.setIcon(BitmapDescriptorFactory.defaultMarker(100));
        if(min==d3)
            marker3.setIcon(BitmapDescriptorFactory.defaultMarker(100));
        if(min==d4)
            marker4.setIcon(BitmapDescriptorFactory.defaultMarker(100));
        if(min==d5)
            marker5.setIcon(BitmapDescriptorFactory.defaultMarker(100));








        LocationListener lsnr = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }
}