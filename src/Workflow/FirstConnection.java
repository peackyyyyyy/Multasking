package Workflow;

import java.io.Serial;
import java.io.Serializable;

public class FirstConnection implements Serializable {
    @Serial
    private static final long serialVersionUID = -5399605122490343339L;
    private final String name;
    public FirstConnection(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
