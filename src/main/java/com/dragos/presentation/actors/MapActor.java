package com.dragos.presentation.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.dragos.presentation.models.MapData;
import com.dragos.presentation.models.WordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by dragos on 02/11/14.
 */
public class MapActor extends AbstractActor {

    private String[] STOP_WORDS =
            {"a", "am", "an", "and", "are", "as", "at", "be", "do", "go", "if", "in", "is", "it", "of", "on", "the", "to"};
    private List<String> STOP_WORDS_LIST = Arrays.asList(STOP_WORDS);
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);
    private Pattern numberPattern = Pattern.compile("[+-]?\\d*(\\.\\d+)?");

    public MapActor() {
        receive(ReceiveBuilder
                        .match(String.class, message -> sender().tell(evaluateExpression(message), self()))
                        .matchAny(this::unhandled).build()
        );
    }

    private MapData evaluateExpression(String line) {
        List<WordCount> dataList = new ArrayList<>();
        StringTokenizer parser = new StringTokenizer(line);

        while (parser.hasMoreTokens()) {
            String word = parser.nextToken().toLowerCase();
            if (!STOP_WORDS_LIST.contains(word) && !numberPattern.matcher(word).matches()) {
                dataList.add(new WordCount(word, 1));
            }
        }
        return new MapData(dataList);
    }
}
