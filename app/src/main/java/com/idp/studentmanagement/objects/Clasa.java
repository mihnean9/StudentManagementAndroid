package com.idp.studentmanagement.objects;

public class Clasa {
    private int id;
    private String name;
    private int creditPoints;
    private Character type;
    private int year;
    private int semester;
    private Major major;

    public Clasa(int id, String name, int creditPoints, Character type, int year, int semester, Major major) {
        this.id = id;
        this.name = name;
        this.creditPoints = creditPoints;
        this.type = type;
        this.year = year;
        this.semester = semester;
        this.major = major;
    }

    public Clasa(String name, int creditPoints, Character type, int year, int semester, Major major) {
        this.name = name;
        this.creditPoints = creditPoints;
        this.type = type;
        this.year = year;
        this.semester = semester;
        this.major = major;
    }

    public int getId() {
        return id;
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

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String displaySubject() {
        String content;
        content = "Materia: " + name + "\n";
        content += "Tipul: " + type + "\n";
        content += "Credite: " + creditPoints + "\n";
        content += "An scolar: " + year + " semestrul " + semester + "\n";
        return content;
    }
}
