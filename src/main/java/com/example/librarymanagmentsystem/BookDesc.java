package com.example.librarymanagmentsystem;

public class BookDesc {
    private int Id;
    private String name,desc;

    public BookDesc(int id, String name, String desc) {
        Id = id;
        this.name = name;
        this.desc = desc;
    }

    public BookDesc() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
