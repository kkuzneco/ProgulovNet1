package com.progulov.progulovnet;

public class LecturerModel {
    public String name;
   // public String department;
    public int id;

    LecturerModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getid() {
        return id;
    }

    public void setUid(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
