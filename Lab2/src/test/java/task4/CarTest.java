package task4;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import org.junit.Test;

import java.util.Arrays;

public class CarTest {

    Car car1 = new Car("Lada",1);

    @Test
    public void getLength() {
        System.out.println(car1.getLength());
    }

    @Test
    public void getBrand() {
    }

    @Test
    public void setBrand() {
    }

    @Test
    public void getModels() {
        System.out.println(Arrays.toString(car1.getModels()));
    }

    @Test
    public void setModelName() throws DuplicateModelNameException {
        car1.setModelName(0,"granta");
        System.out.println(car1.toString());
    }

    @Test
    public void getModelNames() {
        System.out.println(Arrays.toString(car1.getModelNames()));
    }

    @Test
    public void setModelPrice() throws NoSuchModelNameException {
        car1.setModelPrice("granta",20000);
        System.out.println(car1.toString());
    }

    @Test
    public void getModelPrice() throws NoSuchModelNameException {
        System.out.println(car1.getModelPrice("granta"));
    }

    @Test
    public void getModelPrices() {
        System.out.println(Arrays.toString(car1.getModelPrices()));
    }

    @Test
    public void addModel() throws DuplicateModelNameException {
        car1.addModel("granta", 500000);
        System.out.println(car1.toString());
    }

    @Test
    public void deleteModel() throws NoSuchModelNameException {
        car1.deleteModel("granta");
        System.out.println(car1.toString());
    }
}