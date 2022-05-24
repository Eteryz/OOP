package exception;

public class ModelPriceOutOfBoundsException extends RuntimeException {

    public ModelPriceOutOfBoundsException(double message) {
   super("price > 0 and number of digits after the dot < 3!  error:"+ message);
 }
}
