package com.rohitm.today.mapdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=19.1436,72.8246"));
        startActivity(intent);*/
        //Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));


        getLatAndLong("The Address, Kanakia Rd, opposite RBK School, Chandan Shanti, Mira Road, Mira Bhayandar, Maharashtra 401105");
    }

    private void getLatAndLong(String string) {
        if (Geocoder.isPresent()) {
            try {
                //String location = "theNameOfTheLocation";
                Geocoder gc = new Geocoder(this);
                List<Address> addresses = gc.getFromLocationName(string, 3); // get the found Address Objects

                List<LatLng> ll = new ArrayList<>(addresses.size()); // A list to save the coordinates if they are available
                for (Address a : addresses) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                    }
                }
                double lat = ll.get(0).latitude;
                double lng = ll.get(0).longitude;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lng));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                // handle the exception
            }
        }
    }
}
