package exceptions;

public class GetException extends Exception {
    public GetException(String message){super("id" + message + " is null or empty!");}
}
