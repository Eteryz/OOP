package exception;

public class DuplicateModelNameException extends Exception {

    public DuplicateModelNameException(String name){
        super("A model with the same name already exists - " + name);
    }
}
