package exception;

//import java.util.NoSuchElementException;

public class NoSuchModelNameException extends Exception {

    public NoSuchModelNameException(String name) {
        super("Elements with name -"+ name +"- not found...");
    }
}
