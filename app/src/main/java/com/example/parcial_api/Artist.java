package com.example.parcial_api;

import java.io.Serializable;

public class Artist implements Serializable {

    private String name;

    public Artist(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
