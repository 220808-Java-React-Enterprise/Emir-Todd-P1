package com.revature.iers.dtos.requests;

public class NewUserRequest {
    private String id;
    private String username;

    private String email;
    private String password;
    private String passwordConfirm;

    private String given_name;

    private String surName;

    public NewUserRequest() {

    }

    public NewUserRequest(String id, String username, String email, String password, String passwordConfirm, String given_name, String surName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.given_name = given_name;
        this.surName = surName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}


