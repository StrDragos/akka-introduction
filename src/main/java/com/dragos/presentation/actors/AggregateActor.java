package com.dragos.presentation.actors;

import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

import com.dragos.presentation.models.ReduceData;
import com.dragos.presentation.models.Result;

/**
 * Created by dragos on 02/11/14.
 */
public class AggregateActor extends AbstractActor {
    private Map<String, Integer> finalList = new HashMap<>();
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public AggregateActor() {
        receive(ReceiveBuilder
                .match(ReduceData.class, message -> aggregateInMemoryReduce(message.getReducedDataList()))
                .match(Result.class, message -> sender().tell(finalList.toString(), self()))
                .matchAny(this::unhandled).build());
    }

    private void aggregateInMemoryReduce(Map<String, Integer> reducedList) {
        Integer count;
        log.info("parse message {}", reducedList);
        for (String key : reducedList.keySet()) {
            if (finalList.containsKey(key)) {
                count = reducedList.get(key) + finalList.get(key);
                finalList.put(key, count);
            } else {
                finalList.put(key, reducedList.get(key));
            }
        }

    }
}
