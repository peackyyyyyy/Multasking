package Workflow;

import Serveur.Serveur;

import java.util.ArrayList;

public class Dispatcher {
    private final ArrayList<Serveur> serveurs;
    public Dispatcher(ArrayList<Serveur> serveurs){
        this.serveurs = serveurs;

    }
    public void add_Serveur(Serveur serveur){
        this.serveurs.add(serveur);
    }
    public void delete_Serveur(Serveur serveur){
        this.serveurs.remove(serveur);
    }
    public void dispatch(String message){
        for (Serveur serveur : serveurs) {
            serveur.getServeurSender().send_message(message);
        }
    }
}
