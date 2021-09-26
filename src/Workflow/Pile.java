package Workflow;
import java.util.Queue;
public class Pile extends Thread{

    private final Queue<MessageClient> pile;

    public Pile(Queue<MessageClient> pile){
        this.pile = pile;
    }

    public synchronized void write(MessageClient messageClient) throws InterruptedException {
        this.pile.add(messageClient);
        System.out.println("write : \n" + messageClient.toString());
        notify();
    }

    public synchronized MessageClient read (){
        MessageClient element = this.pile.poll();
        System.out.println("read : \n"+ element);
        notify();
        return element;
    }
}
