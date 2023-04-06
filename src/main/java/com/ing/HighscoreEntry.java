package com.ing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HighscoreEntry {
    private int score;
    private String name;
    private String date;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public HighscoreEntry(int score, String name) {
        LocalDateTime now = LocalDateTime.now();
        this.score = score;
        this.name = name;
        this.date = dtf.format(now);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getScore() + "," + getName() + "," + getDate();
    }

}
