package com.idp.studentmanagement.objects;

public class Token {

    private String jwt;

    public Token (String jwt) {
        this.jwt = jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
