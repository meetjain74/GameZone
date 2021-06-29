package com.example.gamezone.data;

import java.util.List;

public class Game {
    String name;
    String gameUrl;
    String imageUrl;
    double rating; // Out of 5
    List<String> keyWords;

    public Game() {}

    public Game(String name, String gameUrl,String imageUrl,double rating) {
        this.name = name;
        this.gameUrl = gameUrl;
        this.imageUrl=imageUrl;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public void addKeyWord(String word) {
        this.keyWords.add(word);
    }
}
