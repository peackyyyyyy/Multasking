package Main;

import Client.Client;

public class ClientThread extends Thread{
    private final Client client;
    public ClientThread(Client client){
        this.client = client;
    }

    public void run(){
        while (true){
            try {
                for (int i=0; i<100; i++){
                    this.client.getClientSender().send_task("Task : "+i);
                }
                wait(100000000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
