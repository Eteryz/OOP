package impl;


import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import org.junit.Test;

import java.util.Arrays;

public class MopedTest{

    Moped moped = new Moped("Riga",1);
    {
        try {
            moped.addModel("D8",44555);
            moped.addModel("D8-M",30000);
            moped.addModel("Min",250);

        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }

    public MopedTest() throws DuplicateModelNameException {
    }

    @Test
    public void testGetLength() {
        System.out.println(moped.getLength());
    }

    @Test
    public void testSetModelName() throws DuplicateModelNameException, NoSuchModelNameException {
        moped.setModelName("8", "SSS");
        moped.print();
    }

    @Test
    public void testGetModelNames() {
        System.out.println(Arrays.toString(moped.getModelNames()));
    }

    @Test
    public void testSetModelPrice() throws NoSuchModelNameException {
        moped.setModelPrice("Min", 300);
        moped.print();
    }

    @Test
    public void testGetModelPrice() throws NoSuchModelNameException {
        System.out.println(moped.getModelPrice("D8"));
    }

    @Test
    public void testGetModelPrices() {
        System.out.println(Arrays.toString(moped.getModelPrices()));
    }

    @Test
    public void testAddModel() throws DuplicateModelNameException {
        moped.addModel("Delta", 450);
        moped.print();
    }

    @Test
    public void testDeleteModel() throws NoSuchModelNameException {
        moped.deleteModel("D8");
        moped.print();
    }
}