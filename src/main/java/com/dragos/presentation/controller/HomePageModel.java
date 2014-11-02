package com.dragos.presentation.controller;

/**
 * Created by dragos on 02/11/14.
 */
public class HomePageModel {
    private String countedWords;
    private String countFor;

    public String getCountFor() {
        return countFor;
    }

    public void setCountFor(String countFor) {
        this.countFor = countFor;
    }

    public String getCountedWords() {
        return countedWords;
    }

    public void setCountedWords(String countedWords) {
        this.countedWords = countedWords;
    }

    @Override
    public String toString() {
        return "HomePageModel{" +
                "countedWords='" + countedWords + '\'' +
                ", countFor='" + countFor + '\'' +
                '}';
    }
}
