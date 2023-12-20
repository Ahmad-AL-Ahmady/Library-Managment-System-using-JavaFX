package com.example.librarymanagmentsystem;

public class Admin extends Staff{
    private String  password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephone(String telephone) {
        this.phone = telephone;
    }

    public Admin() {
    }

    public Admin(String name, String password, String telephone) {
        this.name = name;
        this.password = password;
        this.phone = telephone;
    }


    @Override
    public String toString() {
        return "Admin{" + "adminId=" + id + ", adminName=" + name + ", password=" + password + ", adminPhone=" + phone + '}';
    }

}
