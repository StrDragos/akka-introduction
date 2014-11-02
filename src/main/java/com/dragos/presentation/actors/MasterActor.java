package com.dragos.presentation.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import com.dragos.presentation.models.MapData;
import com.dragos.presentation.models.ReduceData;
import com.dragos.presentation.models.Result;

/**
 * Created by dragos on 02/11/14.
 */
public class MasterActor extends AbstractActor {
    private ActorRef mapActor = context().actorOf(new RoundRobinPool(5).props(Props.create(MapActor.class)), "mapActor");
    private ActorRef reduceActor = context().actorOf(new RoundRobinPool(5).props(Props.create(ReduceActor.class)), "reduceActor");
    private ActorRef aggregateActor = context().actorOf(new RoundRobinPool(5).props(Props.create(AggregateActor.class)), "aggregateActor");
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    public MasterActor() {
        receive(ReceiveBuilder
                .match(String.class, message -> mapActor.tell(message, self()))
                .match(MapData.class, message -> reduceActor.tell(message, self()))
                .match(ReduceData.class, message -> aggregateActor.tell(message, self()))
                .match(Result.class, message -> aggregateActor.forward(message, context()))
                .matchAny(this::unhandled)
                .build());
    }
}
