package impl;

import exception.DuplicateModelNameException;
import org.junit.Test;

public class CarTest {

    @Test
    public void addModel() throws DuplicateModelNameException {
        Car car = new Car();
        car.addModel("lada",30000);
        car.print();
    }
}