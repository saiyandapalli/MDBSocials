package com.saiyandapalli.mdbsocials;

import java.io.Serializable;
import java.util.ArrayList;


public class Social implements Serializable{
    Integer interest;
    String firebaseImageUrl;
    String email;
    String name;
    String description;
    ArrayList<String> usersliked;

    public Social(){}

    public ArrayList<String> getUsersliked() {
        return usersliked;
    }

    public void setUsersliked(ArrayList<String> usersliked) {
        this.usersliked = usersliked;
    }

    public Social(Integer interest, String firebaseImageUrl, String email, String name, String description, ArrayList<String> usersliked) {
        this.interest = interest;
        this.email = email;
        this.name = name;
        this.description = description;
        this.firebaseImageUrl = firebaseImageUrl;
        this.usersliked = usersliked;
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

    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

}
