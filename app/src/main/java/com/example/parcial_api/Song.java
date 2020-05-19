package com.example.parcial_api;

import java.io.Serializable;

public class Song implements Serializable {

    String title;

    Artist artist;

    String duration;

    public Song(String title, Artist artist,String duration){
        this.title = title;
        this.artist = artist;
        this.duration=duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
