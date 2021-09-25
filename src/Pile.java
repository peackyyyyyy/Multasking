import java.util.Queue;
public class Pile extends Thread{

    private final Queue<Message> pile;

    public Pile(Queue<Message> pile){
        this.pile = pile;
    }

    public synchronized void write(Message message) throws InterruptedException {
        this.pile.add(message);
        System.out.println("write : \n" + message.toString());
        notify();
    }

    public synchronized Message read (){
        Message element = this.pile.poll();
        System.out.println("read : \n"+ element);
        notify();
        return element;
    }
}
