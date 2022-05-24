package task2;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import org.junit.Test;

import java.util.Arrays;

public class MotorcycleTest {

    Motorcycle motorcycle2 = new Motorcycle("BMV", 3);
    {
        try {
            motorcycle2.setModelName(0,"Rs1");
            motorcycle2.setModelPrice("rs1",100000);
            motorcycle2.setModelName(1,"m1");
            motorcycle2.setModelPrice("m1",50000);
            motorcycle2.setModelName(2,"m2");
            motorcycle2.setModelPrice("m2",10000);
        } catch (DuplicateModelNameException | NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addModel() throws DuplicateModelNameException {

        motorcycle2.print();

        System.out.println("\n\n\n");

        motorcycle2.addModel("A3",100090);
        motorcycle2.addModel("A2",80000);
        motorcycle2.print();
        System.out.println("\n");
    }

    @Test
    public void setModelName() throws DuplicateModelNameException{

        motorcycle2.setModelName(2,"Fantom");
        motorcycle2.setModelName(0,"Alfa");
        System.out.println("\n");
    }

    @Test
    public void getModelNames(){
        System.out.println(Arrays.toString(motorcycle2.getModelNames()));
        System.out.println("\n");
    }

    @Test
    public void setModelPrice() throws NoSuchModelNameException {
        motorcycle2.setModelPrice("rs1",10.55d);
        motorcycle2.print();
        System.out.println("\n");
    }

    @Test
    public void getModelPrice() throws NoSuchModelNameException {
        System.out.println(motorcycle2.getModelPrice("rs1"));
    }

    @Test
    public void getModelPrices(){
        System.out.println(Arrays.toString(motorcycle2.getModelPrices()));
    }

    @Test
    public void deleteModel() throws NoSuchModelNameException {

        motorcycle2.deleteModel("m1");

        motorcycle2.print();
        System.out.println("\n");
    }

}