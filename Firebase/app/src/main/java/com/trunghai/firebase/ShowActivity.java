package com.trunghai.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    DatabaseReference mData;
    ListView lvTest;
    ArrayList<LayDuLieuListView> mangLayDuLieuListView;
    ShowAdapter adapter;
    Button btn;
    //;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mData= FirebaseDatabase.getInstance().getReference();
        lvTest = (ListView) findViewById(R.id.lvTest);
        mangLayDuLieuListView=new ArrayList<LayDuLieuListView>();


        adapter=new ShowAdapter(ShowActivity.this,R.layout.activity_view,mangLayDuLieuListView);


        lvTest.setAdapter(adapter);

        LoadData();
    }
    private void LoadData(){
       // mangLayDuLieuListView=new ArrayList<LayDuLieuListView>();
        mData.child("Du Lịch").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                LayDuLieuListView layDuLieuListView=dataSnapshot.getValue(LayDuLieuListView.class);
                mangLayDuLieuListView.add(new LayDuLieuListView(layDuLieuListView.Ten,layDuLieuListView.Hinh));
                adapter.notifyDataSetChanged();

                Toast.makeText(ShowActivity.this,"sadas", Toast.LENGTH_SHORT).show();

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
}
