package Workflow;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MessageServeur implements Serializable {
    @Serial
    private static final long serialVersionUID = -5399605122490343339L;

    private final String message;
    private final String expediteur;
    private final Date date;

    public MessageServeur(String message, String expediteur, Date date){
        this.message = message;
        this.expediteur = expediteur;
        this.date = date;
    }


    public Date getDate() {
        return date;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Workflow.Message{" +
                "message='" + message + '\'' +
                ", expediteur='" + expediteur + '\'' +
                ", date=" + date +
                '}';
    }
}
