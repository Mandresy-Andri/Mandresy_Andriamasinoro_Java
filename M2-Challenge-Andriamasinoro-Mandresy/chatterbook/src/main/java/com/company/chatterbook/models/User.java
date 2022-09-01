package com.company.chatterbook.models;

import java.util.List;

public class User {
    //user name
    private String name;
    //list of chatterposts
    private List<ChatterPost> chatterPosts;

    //constructor that sets name
    public User(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    public void setChatterPosts(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }
}
