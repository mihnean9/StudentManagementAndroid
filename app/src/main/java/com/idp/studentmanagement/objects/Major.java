package com.idp.studentmanagement.objects;

public class Major {

    private int id;
    private String name;
    private Faculty faculty;

    public Major(int id, String name, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
    }

    public Major(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String toString() { return name; }

    public String displayMajorDetailed() {
        return this.toString() + "\n" + this.faculty.toString();
    }
}
