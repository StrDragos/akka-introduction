package com.dragos.presentation.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.dragos.presentation.models.MapData;
import com.dragos.presentation.models.ReduceData;
import com.dragos.presentation.models.WordCount;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dragos on 02/11/14.
 */
public class ReduceActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public ReduceActor() {
        receive(ReceiveBuilder
                .match(MapData.class, message -> sender().tell(reduceData(message.getWordCounts()), self()))
                .matchAny(this::unhandled).build());
    }

    private ReduceData reduceData(List<WordCount> dataList) {
        HashMap<String, Integer> reducedMap = new HashMap<>();
        log.info("parse message {}", dataList);
        for (WordCount wordCount : dataList) {
            if (reducedMap.containsKey(wordCount.getWord())) {
                Integer value = reducedMap.get(wordCount.getWord());
                value++;
                reducedMap.put(wordCount.getWord(), value);
            } else {
                reducedMap.put(wordCount.getWord(), 1);
            }
        }
        return new ReduceData(reducedMap);
    }
}
