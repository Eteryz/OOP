package task5;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import task4.*;
import org.junit.Test;

public class VehicleTest {

    Vehicle motorcycle1 = new Motorcycle("BMV",3);


    Vehicle car1 = new Car("Lada",1);
    {
        try {
            car1.setModelName(0,"Granta");
            car1.setModelPrice("granta",500000);
            car1.addModel("priora",300000);
        } catch (DuplicateModelNameException | NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }

    Vehicle iVehicle = car1;

    @Test
    public void average() {
       System.out.println("average: "+ VehicleUtil.average(iVehicle));
    }

    @Test
    public void printModels() {
        System.out.print("printModels: ");
        VehicleUtil.printModels(iVehicle);
    }

    @Test
    public void printModelPrices() {
        System.out.print("printModelPrices: ");
        VehicleUtil.printModelPrices(iVehicle);
    }
}