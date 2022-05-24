package impl;

import exception.DuplicateModelNameException;
import junit.framework.TestCase;
import org.junit.Test;

public class MotorcycleTest extends TestCase {

    @Test
    public void testAddModel() throws DuplicateModelNameException {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.addModel("rs",30000);
        motorcycle.addModel("a22",10000);
        motorcycle.print();
    }
}