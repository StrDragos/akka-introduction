package com.dragos.presentation.services;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.dragos.presentation.actors.MasterActor;
import com.dragos.presentation.models.Result;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * Created by dragos on 02/11/14.
 */

@Service
public class ParsePhrase {

    private ActorRef masterActor = ActorSystem.create("masterActor").actorOf(Props.create(MasterActor.class), "masterActor");

    public void parse(String phrase) {
        masterActor.tell(phrase, ActorRef.noSender());
    }

    public String askForResults() throws Exception {
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        Future<Object> future = Patterns.ask(masterActor, new Result(), timeout);
        return (String) Await.result(future, timeout.duration());
    }

}
