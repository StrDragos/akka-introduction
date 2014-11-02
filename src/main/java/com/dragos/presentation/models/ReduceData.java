package com.dragos.presentation.models;

import java.util.HashMap;

/**
 * Created by dragos on 02/11/14.
 */
public class ReduceData {

    private HashMap<String, Integer> reducedDataList;

    public ReduceData(HashMap<String, Integer> reducedDataList) {
        this.reducedDataList = reducedDataList;
    }

    public HashMap<String, Integer> getReducedDataList() {
        return reducedDataList;
    }

    @Override
    public String toString() {
        return "ReduceData{" +
                "reducedDataList=" + reducedDataList +
                '}';
    }
}
