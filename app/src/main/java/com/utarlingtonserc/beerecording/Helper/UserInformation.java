package com.utarlingtonserc.beerecording.Helper;

public class UserInformation {
    public String uid;
    public String name;
    public String address;
    public String email;
    public String mobile;
    public String password;


    public void UserInformation(){
    }

    public UserInformation(String uid, String name, String address, String email, String mobile, String password) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }
}
