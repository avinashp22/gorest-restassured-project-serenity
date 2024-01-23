package com.gorest.model;

public class UserPojo {

    private Integer usersId;
    private String name;
    private String email;
    private String gender;
    private String status;

    public Integer getUsersId() {return usersId;}

    public void setUsersId(Integer usersId) {this.usersId = usersId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}