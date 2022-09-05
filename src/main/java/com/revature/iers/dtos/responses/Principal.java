package com.revature.iers.dtos.responses;
public class Principal {

    private String id;
    private String username;
    private String role_id;

    public Principal() {

    }

    public Principal(String id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role_id = role;
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

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role_id + '\'' +
                '}';
    }

}
