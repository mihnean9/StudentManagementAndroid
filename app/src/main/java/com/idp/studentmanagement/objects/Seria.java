package com.idp.studentmanagement.objects;

public class Seria {

    private int id;
    private String name;

    public Seria(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Seria(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
