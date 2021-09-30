package Workflow;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MessageClient implements Serializable {
    @Serial
    private static final long serialVersionUID = -5399605122490343339L;

    private final String message;
    private final String expediteur;
    private final ArrayList<String> receveur;
    private final Date date;

    public MessageClient(String message, String expediteur, ArrayList<String> receveur, Date date){
        this.message = message;
        this.expediteur = expediteur;
        this.receveur = receveur;
        this.date = date;
    }

    public ArrayList<String> getReceveur() {
        return receveur;
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
                ", receveur=" + receveur +
                ", date=" + date +
                '}';
    }
}
