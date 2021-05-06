package com.idp.studentmanagement.objects;

public class Student {
    private User user;
    private long sin;
    private char father_initial;
    private long cnp;
    private Grupa grupa;

    public Student(User user, long sin, char father_initial, long cnp, Grupa grupa) {
        this.user = user;
        this.sin = sin;
        this.father_initial = father_initial;
        this.cnp = cnp;
        this.grupa = grupa;
    }

    public Student(Student student) {
        this.user = new User(student.getUser());
        this.user = student.getUser();
        this.sin = student.getSin();
        this.father_initial = student.getFather_initial();
        this.cnp = student.getCnp();
        this.grupa = student.getGrupa();
    }

    public long getSin() {
        return sin;
    }

    public char getFather_initial() {
        return father_initial;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSin(long sin) {
        this.sin = sin;
    }

    public void setFather_initial(char father_initial) {
        this.father_initial = father_initial;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public long getCnp() {
        return cnp;
    }

    public User getUser() {
        return user;
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public String displayStudent() {
        String content = "";
        content += "SIN: " + sin + "\n";
        content += "CNP: " + cnp + "\n";
        content += "FI: " + father_initial + "\n";
        content += "Grupa: " + grupa.toString() + "\n";
        content += user.displayUser();
        return content;
    }

}
