package com.saiyandapalli.mdbsocials;

import java.io.Serializable;


public class Social implements Serializable{
    Integer interest;
    String firebaseImageUrl;
    String email;
    String name;
    String description;
    boolean interested;

    public Social(){}


    public boolean isInterested() {
        return interested;
    }

    public void setInterested(boolean interested) {
        this.interested = interested;
    }

    public Social(Integer interest, String firebaseImageUrl, String email, String name, String description, boolean interested) {
        this.interest = interest;
        this.email = email;
        this.name = name;
        this.description = description;
        this.firebaseImageUrl = firebaseImageUrl;
        this.interested = interested;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public String getFirebaseImageUrl() {
        return firebaseImageUrl;
    }

    public void setFirebaseImageUrl(String firebaseImageUrl) {
        this.firebaseImageUrl = firebaseImageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
