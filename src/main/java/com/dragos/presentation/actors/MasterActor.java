package com.dragos.presentation.actors;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;
import com.dragos.presentation.models.MapData;
import com.dragos.presentation.models.ReduceData;
import com.dragos.presentation.models.Result;
import scala.concurrent.duration.Duration;

/**
 * Created by dragos on 02/11/14.
 */
public class MasterActor extends AbstractActor {
    private ActorRef mapActor = context().actorOf(new RoundRobinPool(5).props(Props.create(MapActor.class)), "mapActor");
    private ActorRef reduceActor = context().actorOf(new RoundRobinPool(5).props(Props.create(ReduceActor.class)), "reduceActor");
    private ActorRef aggregateActor = context().actorOf(Props.create(AggregateActor.class));
    private final LoggingAdapter log = Logging.getLogger(context().system(), this);

    private SupervisorStrategy strategy = new OneForOneStrategy(10, Duration.create("1 minute"),
            DeciderBuilder
                    .match(IllegalArgumentException.class, e -> {
                        log.info("restarted actor ");
                        return SupervisorStrategy.restart();
                    })
                    .matchAny(e -> SupervisorStrategy.escalate()).build());

    public MasterActor() {
        receive(ReceiveBuilder
                .match(String.class, message -> mapActor.tell(message, self()))
                .match(MapData.class, message -> reduceActor.tell(message, self()))
                .match(ReduceData.class, message -> aggregateActor.tell(message, self()))
                .match(Result.class, message -> aggregateActor.forward(message, context()))
                .matchAny(this::unhandled)
                .build());
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }
}
