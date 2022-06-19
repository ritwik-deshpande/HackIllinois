package com.example.hackillinois.models;

public class Leaderboard {
    String id;
    int points;
    String discord;

    public void setId(String id) {
        this.id = id;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public Leaderboard(String id, int points, String discord) {
        this.id = id;
        this.points = points;
        this.discord = discord;
    }

    public String getId() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public String getDiscord() {
        return discord;
    }
}
