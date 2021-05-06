package com.idp.studentmanagement.objects;

public class User {

    private int id;
    private String login;
    private String password;
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;

    public User(String login, String password, UserType userType, String firstName, String lastName, String email, int phoneNumber) {
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String login, String password, UserType userType, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = "";
        this.phoneNumber = 0;
    }

    public User(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.userType = user.getUserType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() { return firstName + " " + lastName; }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String displayUser() {
        String content = "";
        content += "Nume: " + getName() + "\n";
        content += "Email: " + getEmail() + "\n";
        content += "Phone Number: " + getPhoneNumber() + "\n\n";
        return content;
    }

}
