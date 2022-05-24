package impl;

import api.Vehicle;
import exception.DuplicateModelNameException;
import org.junit.Assert;
import org.junit.Test;

public class MotorcycleTest {

    Vehicle mot1 = new Motorcycle();
    Vehicle mot2 = new Motorcycle();
    {
        try {
            mot1.setBrand("BMW");
            mot1.addModel("rs1", 50000);
            mot2.setBrand("BMW");
            mot2.addModel("rs1", 50000);
            mot2.addModel("alfa", 30000);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(mot1.equals(mot2));
    }

    @Test
    public void testHashCode() {
        Assert.assertTrue(mot1.hashCode() == mot2.hashCode());
    }
}