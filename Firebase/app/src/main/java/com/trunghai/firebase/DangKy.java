package com.trunghai.firebase;

/**
 * Created by Admin on 03/05/2017.
 */

public class DangKy {
    public String Username;
    public String Password;
    public String NgaySinh;
    public String SDT;
    public String Email;

    public DangKy() {
    }

    public DangKy(String username, String password, String ngaySinh, String SDT, String email) {
        Username = username;
        Password = password;
        NgaySinh = ngaySinh;
        this.SDT = SDT;
        Email = email;
    }
}
