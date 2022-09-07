package com.revature.iers.models;
public class User {
    private String id;
    private String username;

    private String email;
    private String password;

    private String given_name;

    private String surName;

    private Boolean is_active;
    private String role_id;

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String role_id) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public User(String id, String username, String password, String role_id, Boolean is_active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.is_active = is_active;
    }

    public User(String id, String username, String password, String role_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public User(String username, Boolean is_active) {
        this.username = username;
        this.is_active = is_active;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String id, String username, String email, String password, String given_name, String surName, Boolean is_active, String role_id) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.given_name = given_name;
        this.surName = surName;
        this.is_active = is_active;
        this.role_id = role_id;
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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surName='" + surName + '\'' +
                ", is_active=" + is_active +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}
