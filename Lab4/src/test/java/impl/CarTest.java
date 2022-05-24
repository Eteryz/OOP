package impl;


import api.Vehicle;
import exception.DuplicateModelNameException;
import org.junit.Assert;
import org.junit.Test;

public class CarTest{

    Vehicle car1 = new Car();
    Vehicle car2 = new Car();
    {
        try {
            car1.setBrand("Lada");
            car1.addModel("granta", 550000);
            car1.addModel("vesta", 1200000);
            car2.setBrand("Lada");
            car2.addModel("granta", 550000);
            car2.addModel("vesta", 1200000);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testEquals() {
        Assert.assertTrue(car1.equals(car2));
    }

    @Test
    public void testHashCode() {
        Assert.assertTrue(car1.hashCode() == car2.hashCode());
    }

}