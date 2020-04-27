package cs3700;

import java.util.LinkedList;
import java.util.Queue;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

public class rpsActor extends UntypedAbstractActor {

    private ActorRef actorRefB;
    private ActorRef actorRefC;
    private String nameB;
    private String nameC;
    private gameElement A = new gameElement();
    private Queue<gameElement> B = new LinkedList<gameElement>();
    private Queue<gameElement> C = new LinkedList<gameElement>();
    String name  = "";
    @Override
    public void onReceive(Object message) throws Exception {
        name = getSelf().path().name();
        if(message instanceof ActorRef){
            if(actorRefB == null) {
                actorRefB = (ActorRef) message;
                nameB = actorRefB.path().name();
            }else if(actorRefC == null) {
                actorRefC = (ActorRef) message;
                nameC = actorRefC.path().name();
            }
        }else if(message instanceof String){
            A.pick();
            actorRefB.tell(A, self());
            actorRefC.tell(A, self());
        }else if(message instanceof gameElement) {
            if(getSender().path().name().equals(nameB)) {
                B.add((gameElement) message);
            }else if(getSender().path().name().equals(nameC)) {
                C.add((gameElement) message);
            }
            if(!B.isEmpty() && !C.isEmpty() && A.getElement() != null){
                A.getResult(B.poll().getElement(),C.poll().getElement());
                System.out.println(name + " has this many points so far: " + A.getPoints());
            }
        }
    }
}
