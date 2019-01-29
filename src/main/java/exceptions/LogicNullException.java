package exceptions;

public class LogicNullException extends Exception{
    public LogicNullException(String message){super(message + " is null;");}
}
