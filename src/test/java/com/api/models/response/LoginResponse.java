package com.api.models.response;

public class LoginResponse {

    private boolean success;
    private String token;
    private User user;

    public LoginResponse(boolean success, String token, User user) {
        this.success = success;
        this.token = token;
        this.user = user;
    }

    public LoginResponse() {
    }

    // Getters and Setters
    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
