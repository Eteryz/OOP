package task1;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import org.junit.Test;

import java.util.Arrays;

public class CarTest {

    private Car car = new Car("Lada",2);

    @Test
    public void getBrand() {
        System.out.println("Brand: " + car.getBrand());
    }

    @Test
    public void setBrand() {
        car.setBrand("BMW");
        System.out.println("Set BMW: "+car.getBrand());
    }

    @Test
    public void getModels() {
        System.out.println(Arrays.toString(car.getModels()));
    }

    @Test
    public void setModelName() throws DuplicateModelNameException {
        car.setModelName(0, "Largus");
        car.setModelName(1, "Kalina");
        System.out.println("setModelName:   "+car.toString());
    }

    @Test
    public void setModelPrice() throws DuplicateModelNameException, NoSuchModelNameException {
        car.setModelName(0, "Largus");
        car.setModelName(1, "Kalina");
        car.setModelPrice("Largus",33333.33d);
        car.setModelPrice("Kalina",400000);
        System.out.println("setModelPrice:   "+car.toString());
    }

    @Test
    public void getModelNames() {
        System.out.println("ModelNames: "+Arrays.toString(car.getModelNames()));
    }

    @Test
    public void getModelPrice() throws DuplicateModelNameException, NoSuchModelNameException {
        car.addModel("Priora", 300000);
        System.out.println("ModelPrice: "+ car.getModelPrice("priora"));
    }

    @Test
    public void getModelPrices() {
        System.out.println("Prices: "+Arrays.toString(car.getModelPrices()));
    }

    @Test
    public void addModel() throws DuplicateModelNameException {
        car.addModel("Granta", 500000.33);
        car.addModel("Niva", 450000);
        car.addModel("Vesta", 900000);
        System.out.println("AddModel:   "+ car.toString());

    }

    @Test
    public void deleteModel() throws DuplicateModelNameException, NoSuchModelNameException {
        car.addModel("priora", 200000);
        car.addModel("niva", 100000);
        car.deleteModel("niva");
        System.out.println("deleteModal  "+car.toString());
    }

    @Test
    public void getModelsArrayLength() {
        System.out.println("models.length() = " + car.getModelsArrayLength());
    }
}