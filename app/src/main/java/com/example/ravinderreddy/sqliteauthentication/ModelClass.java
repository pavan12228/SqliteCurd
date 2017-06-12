package com.example.ravinderreddy.sqliteauthentication;

/**
 * Created by Ravinder Reddy on 11-06-2017.
 */

public class ModelClass
{
    String name, email,mobilenum,dob,phnum,pwd,confirmpwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhnum() {
        return phnum;
    }

    public void setPhnum(String phnum) {
        this.phnum = phnum;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirmpwd() {
        return confirmpwd;
    }

    public void setConfirmpwd(String confirmpwd) {
        this.confirmpwd = confirmpwd;
    }
}
