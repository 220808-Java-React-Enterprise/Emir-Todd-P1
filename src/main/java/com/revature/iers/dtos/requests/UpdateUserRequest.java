package com.revature.iers.dtos.requests;

public class UpdateUserRequest {

    private String email;
    private String password;

    private String given_name;

    private String surName;

    private boolean is_active;
    private String role = "EMPLOYEE";

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String email, String password, String given_name, String surName, boolean is_active, String role) {
        this.email = email;
        this.password = password;
        this.given_name = given_name;
        this.surName = surName;
        this.is_active = is_active;
        this.role = role;
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surName='" + surName + '\'' +
                ", is_active=" + is_active +
                ", role='" + role + '\'' +
                '}';
    }
}
