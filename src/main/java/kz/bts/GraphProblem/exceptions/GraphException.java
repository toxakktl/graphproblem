package kz.bts.GraphProblem.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GraphException extends RuntimeException {

    final static Logger logger = LogManager.getLogger(GraphException.class);

    private String message;

    public GraphException(String message){
        this.message = message;
    }

    public void printException(){
        logger.error(this.message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
