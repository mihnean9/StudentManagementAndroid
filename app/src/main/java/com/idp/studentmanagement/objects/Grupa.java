package com.idp.studentmanagement.objects;

public class Grupa {

    int id;
    private Seria seria;
    private int number;
    private Major major;

    public Grupa(int id, Seria seria, int number, Major major) {
        this.id = id;
        this.seria = seria;
        this.number = number;
        this.major = major;
    }

    public Grupa(Seria seria, int number, Major major) {
        this.seria = seria;
        this.number = number;
        this.major = major;
    }

    public int getId() { return id; }

    public Seria getSeria() {
        return seria;
    }

    public void setSeria(Seria seria) {
        this.seria = seria;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String toString() { return number + seria.getName(); }

    public String displayGrupaDetailed() {
        return this.toString() + "\n" + major.getFaculty().getName() + "\n" +
                major.getName() + "\n";
    }

    public boolean equals(Grupa grupa) {
        return this.toString().equals(grupa.toString());
    }
}
