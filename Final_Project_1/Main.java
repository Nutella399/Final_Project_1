package cs3700;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
//import akka.actor.typed.ActorSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many runs of rock paper scissors would you like the computers to play.");
        int run = scan.nextInt();
        final ActorSystem rps = ActorSystem.create("rpsSystem");

        ActorRef actorRefA = rps.actorOf(Props.create(rpsActor.class), "actor_A");
        ActorRef actorRefB = rps.actorOf(Props.create(rpsActor.class), "actor_B");
        ActorRef actorRefC = rps.actorOf(Props.create(rpsActor.class), "actor_C");

        actorRefA.tell(actorRefB, ActorRef.noSender());
        actorRefC.tell(actorRefB, ActorRef.noSender());
        actorRefA.tell(actorRefC, ActorRef.noSender());
        actorRefB.tell(actorRefC, ActorRef.noSender());
        actorRefB.tell(actorRefA, ActorRef.noSender());
        actorRefC.tell(actorRefA, ActorRef.noSender());


        for(int i = 0; i < run; i++) {
            actorRefA.tell("Hey", ActorRef.noSender());
            actorRefB.tell("Hey", ActorRef.noSender());
            actorRefC.tell("Hey", ActorRef.noSender());
        }
        rps.terminate();
    }
}