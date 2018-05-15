package com.example.win10.aybu.Model;

public class User {

    private String username;
    private String password;
    private String email;
    private String department;

    public User(){
    }

    public User(String username, String password, String email, String department ){
        this.username = username;
        this.password = password;
        this.email = email;
        this.department = department;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
