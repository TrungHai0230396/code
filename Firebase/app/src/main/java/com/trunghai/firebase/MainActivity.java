package com.trunghai.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.trunghai.firebase.R.id.Username;
import static com.trunghai.firebase.R.id.btnListView;
import static com.trunghai.firebase.R.id.btnTaoTaiKhoan;
import static com.trunghai.firebase.R.id.middle;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mData;
    EditText txtUsername,txtPassword;
    Button btnDangNhap,btnTaoTaiKhoan,btnListView;
    ArrayList<DangNhap> mangDangNhap;
    ImageView Image;
    ArrayList<MieuTa> mangMieuTa;
    TextView txt;
    String b="";
    int i=0;
    int j=50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mangDangNhap=new ArrayList<DangNhap>();
        mangMieuTa=new ArrayList<MieuTa>();
        mData=FirebaseDatabase.getInstance().getReference();
//        DangNhap dangnhap=new DangNhap("admin1","admin1");
//          mData.child("Đăng Nhập").push().setValue(dangnhap);
        //final ProgressDialog dialog=ProgressDialog.show(this,"Loading...","",true);
       txt = (TextView) findViewById(R.id.textView);

        txtUsername= (EditText) findViewById(R.id.txtUsername);
        txtPassword= (EditText) findViewById(R.id.txtPassword);
        btnDangNhap= (Button) findViewById(R.id.btnDangNhap);
        btnTaoTaiKhoan= (Button) findViewById(R.id.btnTaoTaiKhoan);
        btnListView= (Button) findViewById(R.id.btnListView);
       // Image= (ImageView) findViewById(R.id.imageView);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mangDangNhap.size()==0)
                    Toast.makeText(MainActivity.this, "Đang load dữ liệu", Toast.LENGTH_SHORT).show();
                else
                {

                    boolean kt=false;
                    for(int i=0;i<mangDangNhap.size();i++)
                    {
                        if(mangDangNhap.get(i).Username.equals(txtUsername.getText().toString()))
                        {
                            kt=true;
                        }
                    }
                    if (kt == true)
                    {
                        Toast.makeText(MainActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                         Intent i=new Intent(MainActivity.this,MapsActivity.class);
                          startActivity(i);
                    }
                    else if (kt == false) {
                        Toast.makeText(MainActivity.this, "Đăng Nhập Thất bại", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,DangKyActivity.class);
                startActivity(i);
                //mData.child("Đăng Nhập").child(dataSnapshot.getKey()).child("MieuTa").push().setValue(new MieuTa("aaaa","bbbb"));

            }
        });
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);
            }
        });
        mData.child("Đăng Nhập").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {//như 1 vòng for
                DangNhap dangNhap=dataSnapshot.getValue(DangNhap.class);//đọc tất cả dữ liệu trừ key từ ngọn Đăng Nhập
                String User=dangNhap.Username;
                mangDangNhap.add(dangNhap);
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


      //  DuLich dn=new DuLich("Dinh độc lập","10.7797838","106.6968061");
      //  mData.child("Du Lịch").push().setValue(dn);
    }
}
