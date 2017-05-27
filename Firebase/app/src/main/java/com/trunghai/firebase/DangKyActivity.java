package com.trunghai.firebase;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Admin on 03/05/2017.
 */

public class DangKyActivity extends Activity{
    EditText edtTDN,edtMK,edtNS,edtSDT,edtEmail;
    Button btnDK;
    ArrayList<DangKy> mangDangKy;
    DatabaseReference mData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        mData= FirebaseDatabase.getInstance().getReference();
        mangDangKy=new ArrayList<DangKy>();

        edtTDN= (EditText) findViewById(R.id.edtUsername);
        edtMK= (EditText) findViewById(R.id.edtPassword);
        edtNS= (EditText) findViewById(R.id.edtNgaysinh);
        edtSDT= (EditText) findViewById(R.id.edtSDT);
        edtEmail= (EditText) findViewById(R.id.edtEmail);
        btnDK= (Button) findViewById(R.id.btnDangKy);

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean kt=false;
                for(int i=0;i<mangDangKy.size();i++)
                {
                    if(mangDangKy.get(i).Username.equals(edtTDN.getText().toString()))
                    {
                        kt=true;
                    }
                }
                if (kt == false)
                {
                    DangKy DK=new DangKy(edtTDN.getText().toString(),edtMK.getText().toString(),edtNS.getText().toString(),edtSDT.getText().toString(),edtEmail.getText().toString());
                    mData.child("Đăng Nhập").push().setValue(DK, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if(databaseError==null)
                                Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(DangKyActivity.this, "Đã xảy ra lỗi khi đăng ký!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if (kt == true) {
                    Toast.makeText(DangKyActivity.this, "Đăng Ký Thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        mData.child("Đăng Nhập").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {//như 1 vòng for

                DangKy dangky=dataSnapshot.getValue(DangKy.class);//đọc tất cả dữ liệu trừ key từ ngọn Đăng Nhập
                mangDangKy.add(dangky);
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
        //Toast.makeText(this, "sadsadsaasdwqewqeqewqe", Toast.LENGTH_SHORT).show();
    }
}
