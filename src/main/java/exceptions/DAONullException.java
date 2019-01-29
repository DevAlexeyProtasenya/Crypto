package exceptions;

public class DAONullException extends Exception {
    public DAONullException(String message){super(message + " = null;");}
}
