package com.illusionbox.www.offerme;

import android.app.DialogFragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    ArrayList<String> nearby;
    ArrayList<Restaurant> nearbyRestaurants;
    HashMap<String,MarkerOptions> nearbyMarks;
    RestaurantAdapter theAdapter;
    ListView theListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        /*String[] nearbyRestaurants = {"KaMuKo", "MC Donalds", "KFC", "Dinemore",
                "Bay Leaf", "Oro", "Pizza Hut", "Domino's"};*/

        nearby = new ArrayList<>();
        nearbyRestaurants = new ArrayList<>();
        nearbyMarks = new HashMap<>();
        nearby.add("Getting Location...");

        theAdapter = new RestaurantAdapter(this, nearbyRestaurants);

        theListView = (ListView) findViewById(R.id.listView);
        theListView.setAdapter(theAdapter);
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String restaurantSelected = "Pinpointing your location!";
                Toast.makeText(MapsActivity.this, restaurantSelected, Toast.LENGTH_SHORT).show();
            }
        });

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
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        Location dummyLocation = new Location("");
        dummyLocation.setLatitude(7.279107);
        dummyLocation.setLongitude(80.698602);


        final HashMap<String,Restaurant> restaurants = new HashMap<>();
        restaurants.put("KaMuKo", new Restaurant("KaMuKo", "The best Korean cuisine in town!", dummyLocation.getLatitude(), dummyLocation.getLongitude(), Restaurant.FINE_DINING, "https://raw.githubusercontent.com/janithajc/share/master/KaMuKo.offer"));
        restaurants.put("McDonalds", new Restaurant("McDonalds", "Enjoy Mc Burgers!", dummyLocation.getLatitude() + 0.01, dummyLocation.getLongitude() + 0.01, Restaurant.PUB, "https://raw.githubusercontent.com/janithajc/share/master/mcDonalds.offer"));
        restaurants.put("KFC", new Restaurant("KFC", "Best Fried Chicken!", dummyLocation.getLatitude() + 0.015, dummyLocation.getLongitude() - 0.015, Restaurant.PUB, "https://raw.githubusercontent.com/janithajc/share/master/mcDonalds.offer"));
        restaurants.put("Dinemore", new Restaurant("Dinemore", "Submarines will make you weep!", dummyLocation.getLatitude() + 0.02, dummyLocation.getLongitude() + 0.02, Restaurant.FINE_DINING,"https://raw.githubusercontent.com/janithajc/share/master/mcDonalds.offer"));
        restaurants.put("Bay Leaf", new Restaurant("Bay Leaf", "Great food huge prices!", dummyLocation.getLatitude(), dummyLocation.getLongitude() + 0.03, Restaurant.FINE_DINING,""));
        restaurants.put("Oro", new Restaurant("Oro", "Thin Pizza with big pricetags!", dummyLocation.getLatitude() + 0.02, dummyLocation.getLongitude() - 0.01, Restaurant.FINE_DINING,""));
        restaurants.put("Pizza Hut", new Restaurant("Pizza Hut", "Great pizza with great crust!", dummyLocation.getLatitude() - 0.01, dummyLocation.getLongitude() - 0.01, Restaurant.PIZZA,"https://raw.githubusercontent.com/janithajc/share/master/pizzaHut.offer"));
        restaurants.put("Domino's", new Restaurant("Domino's", "Great pizza with a lot of cheese!", dummyLocation.getLatitude() + 0.03, dummyLocation.getLongitude() + 0.15, Restaurant.PIZZA,""));

        final MarkerOptions [] resMarker = new MarkerOptions[restaurants.size()];/*{new MarkerOptions().position(new LatLng(dummyLocation.getLatitude(), dummyLocation.getLongitude())).title("KaMuKo"),
                new MarkerOptions().position(new LatLng(dummyLocation.getLatitude()+0.01, dummyLocation.getLongitude()+0.01)).title("McDonalds"),
                new MarkerOptions().position(new LatLng(dummyLocation.getLatitude()+0.015, dummyLocation.getLongitude()-0.015)).title("KFC"),
                new MarkerOptions().position(new LatLng(dummyLocation.getLatitude()+0.02, dummyLocation.getLongitude()+0.02)).title("Dinemore"),
                new MarkerOptions().position(new LatLng(dummyLocation.getLatitude()+0, dummyLocation.getLongitude()+0.03)).title("Bay Leaf"),
                new MarkerOptions().position(new LatLng(dummyLocation.getLatitude()+0.02, dummyLocation.getLongitude()-0.01)).title("Oro"),
                new MarkerOptions().position(new LatLng(dummyLocation.getLatitude()-0.01, dummyLocation.getLongitude()-0.01)).title("Pizza Hut"),
                new MarkerOptions().position(new LatLng(dummyLocation.getLatitude()+0.03, dummyLocation.getLongitude()-0.03)).title("Domino's")};*/

        int i=0;
        for(Restaurant r:restaurants.values()){
           /* r.addOffer(new Offer("50% off!", "Get 50% off on all pizzas today!", "localhost", true, R.drawable.iblauncher));
            r.addOffer(new Offer("30% off!", "Get 30% off on mac and cheese today!", "localhost", true, R.drawable.iblauncher));
            r.addOffer(new Offer("20% off!", "Get 20% off on Biritza's today!", "localhost", true, R.drawable.iblauncher));
            r.addOffer(new Offer("40% off!", "Get 40% off on coffee today!", "localhost", true, R.drawable.iblauncher));
            r.addOffer(new Offer("10% off!", "Get 10% off on Tandoori Chicken Pizza today!", "localhost", true, R.drawable.iblauncher));*/
            resMarker[i] = new MarkerOptions().position(r.getPosititon()).title(r.getName());
            i++;
        }

        Toast.makeText(MapsActivity.this, "Locating your device...", Toast.LENGTH_LONG).show();

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(final Location location) {
                mMap.clear();
                nearby.clear();
                nearbyRestaurants.clear();
                nearbyMarks.clear();

                for(MarkerOptions a:resMarker){
                    float []  results = new float[1];
                    Location.distanceBetween(a.getPosition().latitude,a.getPosition().longitude,location.getLatitude(),location.getLongitude(),results);
                    String distance = "Distance: " + ((int)results[0]/1000 > 0 ? (int)results[0]/1000+" km ":"") + (int)results[0]%1000 + " m";
                    /*double latDist = Math.abs(a.getPosition().latitude - location.getLatitude());
                    double longDist = Math.abs(a.getPosition().longitude - location.getLongitude());
                    double dist = Math.sqrt(latDist*latDist + longDist*longDist);*/
                    if(results[0] < 2000)
                    {
                        a.snippet(distance);
                        //nearby.add(a.getTitle());
                        nearbyRestaurants.add(restaurants.get(a.getTitle()));
                        nearbyMarks.put(a.getTitle(), a);
                        mMap.addMarker(a);
                    }
                    else
                    {
                        a.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        a.snippet(distance);
                        mMap.addMarker(a);
                    }
                }

                theAdapter.notifyDataSetChanged();
                theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Restaurant res = (Restaurant)parent.getItemAtPosition(position);
                        String resTitle = res.getName();
                        String restaurantSelected = "Locating " + resTitle;
                        Toast.makeText(MapsActivity.this, restaurantSelected, Toast.LENGTH_SHORT).show();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(res.getPosititon(), 19));
                    }
                });
                theListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Restaurant res = (Restaurant)parent.getItemAtPosition(position);
                       /* MyDialogFragment d = new MyDialogFragment();
                        d.setValues(res.getName(), res.getDescription());
                        d.show(getFragmentManager(), "theDialog");*/
                        Intent secondScreenIntent = new Intent(MapsActivity.this, SecondScreen.class);
                        final int result = 1;
                        secondScreenIntent.putExtra("title", res.getName());
                        secondScreenIntent.putExtra("description", res.get_longDescription());
                        secondScreenIntent.putExtra("image",res.get_imgResource());
                        secondScreenIntent.putExtra("offersLink", res.getOffers());
                        secondScreenIntent.putExtra("resLat", res.getPosititon().latitude);
                        secondScreenIntent.putExtra("resLng", res.getPosititon().longitude);
                        secondScreenIntent.putExtra("latitude", location.getLatitude());
                        secondScreenIntent.putExtra("longitude", location.getLongitude());
                        startActivity(secondScreenIntent);
                        return true;
                    }
                });
            }
        });
    }
}
