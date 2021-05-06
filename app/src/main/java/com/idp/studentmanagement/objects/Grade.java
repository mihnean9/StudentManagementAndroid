package com.idp.studentmanagement.objects;

public class Grade {
    private Clasa clasa;
    private Student student;
    private int grade;

    public Grade(Clasa clasa, Student student, int grade) {
        this.clasa = clasa;
        this.student = student;
        this.grade = grade;
    }

    public Clasa getClasa() {
        return clasa;
    }

    public void setClasa(Clasa clasa) {
        this.clasa = clasa;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String displayGrade() {
        String content;
        content = clasa.displaySubject();
        content += "Nota: " + grade + "\n\n";
        return content;
    }
}
