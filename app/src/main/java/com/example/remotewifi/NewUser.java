package com.example.remotewifi;

public class NewUser {
    public String id, username, email, password;

    public NewUser() {
    }

    public NewUser(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
