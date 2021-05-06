package com.idp.studentmanagement.objects;

public enum UserTypes {
    ADMIN("ADMIN"),
    SECRETARY("SECRETARY"),
    STUDENT("STUDENT");

    private String type;

    UserTypes(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }
}
