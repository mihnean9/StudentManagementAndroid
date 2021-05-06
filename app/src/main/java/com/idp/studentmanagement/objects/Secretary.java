package com.idp.studentmanagement.objects;


public class Secretary {

    int id;
    private User user;
    private Faculty faculty;

    public Secretary(User user, Faculty faculty) {
        this.user = user;
        this.faculty = faculty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
