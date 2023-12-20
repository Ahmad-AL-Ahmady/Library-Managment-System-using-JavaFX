package com.example.librarymanagmentsystem;

public class Staff {
    protected int id;
    protected String name, phone;

    public Staff() {
    }

    public Staff(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Staff(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", staffName=" + name + ", staffPhone=" + phone + '}';
    }

}
