package com.dragos.presentation.models;

/**
 * Created by dragos on 02/11/14.
 */
public class WordCount {
    private final String word;
    private Integer counted;


    public WordCount(String word, Integer counted) {
        this.word = word;
        this.counted = counted;
    }

    public String getWord() {
        return word;
    }

    public Integer getCounted() {
        return counted;
    }

    @Override
    public String toString() {
        return "WordCount{" +
                "word='" + word + '\'' +
                ", counted=" + counted +
                '}';
    }
}
