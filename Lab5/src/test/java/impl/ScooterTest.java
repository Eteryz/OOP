package impl;

import api.Vehicle;
import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class ScooterTest {

    Vehicle v = new Scooter("Veter",2);
    {
        try {
            v.addModel("C", 5000);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getModelsHashMap() {
        HashMap<String,Double> hashMap = ((Scooter)v).getModels();
        hashMap.put("sssss", 2222.0);
        System.out.println("New " +hashMap);
        System.out.print("Old ");v.print();
    }

    @Test
    public void getLength() {
        System.out.println(v.getLength());
    }

    @Test
    public void setModelName() throws DuplicateModelNameException, NoSuchModelNameException {
        v.setModelName("C","B");
        v.print();
    }

    @Test
    public void getModelNames() {
        System.out.println(Arrays.toString(v.getModelNames()));
    }

    @Test
    public void setModelPrice() throws NoSuchModelNameException {
        v.setModelPrice("C",1);
        v.print();
    }

    @Test
    public void getModelPrice() throws NoSuchModelNameException {
        System.out.println(v.getModelPrice("C"));
    }

    @Test
    public void getModelPrices() {
        System.out.println(Arrays.toString(v.getModelPrices()));
    }

    @Test
    public void addModel() throws DuplicateModelNameException {
        v.addModel("E",22);
        v.addModel("J", 8888);
        v.print();
    }

    @Test
    public void deleteModel() throws NoSuchModelNameException {
        v.deleteModel("C");
        v.print();
    }
}