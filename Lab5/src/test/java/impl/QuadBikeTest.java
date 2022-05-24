package impl;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import org.junit.Test;

import java.util.Arrays;

public class QuadBikeTest {

    QuadBike quadBike = new QuadBike("Pip",2);
    {
        try {
            quadBike.addModel("FF",44555);
            quadBike.addModel("BB",30000);
            quadBike.addModel("SS",100);

        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLength() {
        System.out.println(quadBike.getLength());
    }

    @Test
    public void testSetModelName() throws DuplicateModelNameException, NoSuchModelNameException {
        quadBike.setModelName("SS","NNN");
        quadBike.print();
    }


    @Test
    public void testGetModelNames() {
        System.out.println(Arrays.toString(quadBike.getModelNames()));
    }

    @Test
    public void testSetModelPrice() throws NoSuchModelNameException {
        quadBike.setModelPrice("FF",0);
        quadBike.print();
    }

    @Test
    public void testGetModelPrice() throws NoSuchModelNameException {
        System.out.println(quadBike.getModelPrice("FF"));
    }

    @Test
    public void testGetModelPrices() {
        System.out.println(Arrays.toString(quadBike.getModelPrices()));
    }

    @Test
    public void testAddModel() throws DuplicateModelNameException {
        quadBike.addModel("MM",24221);
        quadBike.addModel("ZZ",5112);
        quadBike.print();
    }

    @Test
    public void testDeleteModel() throws NoSuchModelNameException {
        quadBike.deleteModel("FF");
        quadBike.print();
    }
}