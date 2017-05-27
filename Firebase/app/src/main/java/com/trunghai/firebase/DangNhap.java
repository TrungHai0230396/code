package com.trunghai.firebase;

import java.util.HashMap;

/**
 * Created by Admin on 06/05/2017.
 */

public class DangNhap {
    public String Username;
    public String Password;
    public String Hinh;
    public String Hinh1;
    public String MieuTa1;


    public DangNhap() {
    }
    public DangNhap(String Username)
    {
        this.Username=Username;
    }
    public DangNhap(String username, String password, String hinh) {
        Username = username;
        Password = password;
        Hinh = hinh;

    }

}
