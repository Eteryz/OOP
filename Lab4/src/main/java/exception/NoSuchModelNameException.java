package exception;

public class NoSuchModelNameException extends Exception {

    public NoSuchModelNameException(String name) {
        super("Elements with name -"+ name +"- not found...");
    }
}
