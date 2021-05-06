package com.idp.studentmanagement.objects;

import androidx.annotation.NonNull;

public class UserType {

    private int id;
    private String type;

    public UserType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public UserType(String type) {
        this.type = type;
        switch (type) {
            case "ADMIN":
                this.id = 1;
                break;

            case "SECRETARY":
                this.id = 2;
                break;

            case "STUDENT":
                this.id = 3;
                break;
        }
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @NonNull
    @Override
    public String toString() {
        return type;
    }
}
