package com.example.abhi.placesaroundme;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    LocationManager locationManager;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        }
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider){
            updateWithNewLocation(null);
        }

        public void onProviderEnabled(String provider){ }
        public void onStatusChanged(String provider, int status,
                                    Bundle extras){ }
    };


    private void updateWithNewLocation(Location location) {

        String latLongString;
        String addressString = "No address found";

        if (location != null) {

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            mMap.clear();
            LatLng myLocation=new LatLng(latitude, longitude);
            addressString=getAddress(latitude, longitude);
            Marker m1= mMap.addMarker(new MarkerOptions().position(myLocation).title("You are here").icon(BitmapDescriptorFactory.fromResource(R.drawable.mapmarker)));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10));

            //UserDBOpetarions dop=new UserDBOpetarions(getApplicationContext());
            //dop.execute("map");

           /* while(UserDBOpetarions.userPostList==null)
            {

            }*/
            MyUserDBOperations mydb=new MyUserDBOperations(getApplicationContext());
             int res= mydb.getMapLocations();

           if(MyUserDBOperations.userPostList!=null) {
                for(int i=0; i<MyUserDBOperations.userPostList.size(); i++)
                {
                    UserPosts user=MyUserDBOperations.userPostList.get(i);
                  if( UserDataTable.MAIN_ISALL || !UserDataTable.MAIN_ISALL && UserDataTable.MAIN_GROUPID.equals(user.group_id)) {
                      double userlatitude = user.getLatitude();
                      double userlongitude = user.getLongitude();

                      double maxDistance = UserDataTable.getDistance();

                      Location newLocation = new Location(locationManager.GPS_PROVIDER);
                      newLocation.setLatitude(userlatitude);
                      newLocation.setLongitude(userlongitude);
                      if (location.distanceTo(newLocation) <= maxDistance) {

                          if (user.getCategory().equals(UserDataTable.MAIN_CATEGORY) || UserDataTable.MAIN_CATEGORY.equals("All")) {
                              addressString = getAddress(userlatitude, userlongitude);
                              LatLng userLatLng = new LatLng(userlatitude, userlongitude);

                              if (user.getCategory().equals("Restuarant")) {
                                  Marker userMarker = mMap.addMarker(new MarkerOptions().position(userLatLng).title(addressString).icon(BitmapDescriptorFactory.fromResource(R.drawable.rest)));

                              } else if (user.getCategory().equals("Shopping")) {
                                  Marker userMarker = mMap.addMarker(new MarkerOptions().position(userLatLng).title(addressString).icon(BitmapDescriptorFactory.fromResource(R.drawable.shop)));

                              } else if (user.getCategory().equals("Park")) {
                                  Marker userMarker = mMap.addMarker(new MarkerOptions().position(userLatLng).title(addressString).icon(BitmapDescriptorFactory.fromResource(R.drawable.park)));

                              } else {
                                  Marker userMarker = mMap.addMarker(new MarkerOptions().position(userLatLng).title(addressString));

                              }
                          }
                      }
                  }
                }

            }
            else
            {
               // Toast.makeText(getApplicationContext(), "UserDBOpetarions.userPostList= =null", Toast.LENGTH_LONG).show();

            }

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker)
                {
                    Intent i =new Intent(getApplicationContext(),DetailActivity.class);
                    LatLng markerlatLng =marker.getPosition();
                    double markerLat=markerlatLng.latitude;
                    double markerLng=markerlatLng.longitude;

                    i.putExtra("markerLat",markerLat);
                    i.putExtra("markerLng",markerLng);

                    startActivity(i);
                    return true;
                }
            });

        } else {
            latLongString = "No location found";
        }
    }
    public String getAddress(double latitude,double longitude)
    {
        String addressString="No address found";
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        try {

            // Call the getFromLocation method, passing in the newly
            // received location and limiting the results to a single address.
            List<Address> addresses = gc.getFromLocation(latitude, longitude, 1);

            StringBuilder sb = new StringBuilder();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);

                // Extract each line in the street address, as well as the
                // locality, postcode, and country, and append this
                // information to an existing Text View string.
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                    sb.append(address.getAddressLine(i)).append("\n");

                sb.append(address.getLocality()).append("\n");
                sb.append(address.getPostalCode()).append("\n");
                sb.append(address.getCountryName());
            }
            addressString = sb.toString();
            return addressString;
        } catch (IOException e) {}
        return addressString;
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
    private void setUpMap() {
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)getSystemService(context);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        provider = locationManager.getBestProvider(criteria, true);

        if(provider==null)
        {
            provider=locationManager.GPS_PROVIDER;
        }
        Location  Location = locationManager.getLastKnownLocation(provider);

        locationManager.requestLocationUpdates(provider, 2000, 10,
                locationListener);


        mMap.addMarker(new MarkerOptions().position(new LatLng(Location.getLatitude(), Location.getLongitude())).title("You are here"));

        //mMap.addMarker(new MarkerOptions().position(new LatLng(37.478132, -121.925867)).title("You are here"));

        LatLng myLocation=new LatLng(Location.getLatitude(), Location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,10));
        updateWithNewLocation(Location);

    }
}
