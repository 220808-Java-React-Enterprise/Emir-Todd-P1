package com.revature.iers.dtos.requests;

public class DeleteUserRequest {

    private String username;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(String userId) {
        this.username = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DeleteUserRequest{" +
                "userId='" + username + '\'' +
                '}';
    }
}
