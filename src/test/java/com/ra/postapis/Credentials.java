package com.ra.postapis;

public class Credentials {
    // copy fields from POSTMAN
    String username;
    String password;

    // ctr needed for Credentials
    // Why we need ctr - initialize the class - Objects as well as we can pass the values on the fly
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Generate - getters and setters
    // Never give direct access to the Class variables - only through these getters and Methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
