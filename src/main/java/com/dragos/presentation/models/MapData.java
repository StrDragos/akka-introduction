package com.dragos.presentation.models;

import java.util.List;

/**
 * Created by dragos on 02/11/14.
 */
public class MapData {
    private List<WordCount> wordCounts;

    public MapData(List<WordCount> wordCounts) {
        this.wordCounts = wordCounts;
    }

    public List<WordCount> getWordCounts() {
        return wordCounts;
    }

    @Override
    public String toString() {
        return "MapData{" +
                "wordCounts=" + wordCounts +
                '}';
    }
}
