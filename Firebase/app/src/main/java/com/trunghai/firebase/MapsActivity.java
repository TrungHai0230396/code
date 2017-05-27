package com.trunghai.firebase;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference mData;
    ArrayList<DuLich> mangdulich;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        dialog=new ProgressDialog(this);
//        dialog.setMessage("Loading...");
//        dialog.show();
//        new Background().execute();
//        Toast.makeText(this, "Load", Toast.LENGTH_SHORT).show();

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
      //   Add a marker in Sydney and move the camera

      //  Toast.makeText(MapsActivity.this,"2132131234124123213", Toast.LENGTH_SHORT).show();
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));

//        new Background().execute();
        mangdulich=new ArrayList<DuLich>();
        mData= FirebaseDatabase.getInstance().getReference();
        mData.child("Du Lịch").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {//như 1 vòng for
                DuLich dulich=dataSnapshot.getValue(DuLich.class);//đọc tất cả dữ liệu trừ key từ ngọn Đăng Nhập
                mangdulich.add(dulich);
              //  Toast.makeText(MapsActivity.this, dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                LatLng a = new LatLng(Double.parseDouble(dulich.X),Double.parseDouble(dulich.Y));
                mMap.addMarker(new MarkerOptions().position(a).title(dulich.Ten));
                Picasso.with(MapsActivity.this).load(dulich.Hinh);
             //   mMap.moveCamera(CameraUpdateFactory.newLatLng(a));
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private class Background extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MapsActivity.this, "Stop", Toast.LENGTH_SHORT).show();
            for(int i=0;i<4;i++)
                Toast.makeText(MapsActivity.this,i+"",Toast.LENGTH_SHORT).show();
            for(int i=0;i<mangdulich.size();i++)
            {
                LatLng a = new LatLng(Double.parseDouble(mangdulich.get(i).X),Double.parseDouble(mangdulich.get(i).Y));
                mMap.addMarker(new MarkerOptions().position(a).title(mangdulich.get(i).Ten));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(a));
            }
        }
    }
}
