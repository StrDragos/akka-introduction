package com.dragos.presentation.services;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.dragos.presentation.actors.MasterActor;
import org.springframework.stereotype.Service;

/**
 * Created by dragos on 02/11/14.
 */

@Service
public class ParseFrase {

    private ActorRef masterActor = ActorSystem.create("masterActor").actorOf(Props.create(MasterActor.class));

    public void parse(String frase) {
        masterActor.tell(frase, ActorRef.noSender());
    }

    public void askForResults() {

    }
}
