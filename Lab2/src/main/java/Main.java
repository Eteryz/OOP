
import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import task4.Car;
import task4.Motorcycle;
import task4.Vehicle;
import task5.VehicleUtil;


public class Main {
    public static void main(String[] args){

        try {

        Vehicle car = new Car("KIA",1);
        car.setModelName(0,"Rio");
        car.setModelPrice("Rio", 800000);
        car.addModel("Ceed", 950000);
        car.addModel("Picanto",  869900);
        car.print();
        System.out.println("\n");

        System.out.println("Price kia ceed - "+ car.getModelPrice("ceed"));
        car.deleteModel("rio");
        System.out.println("After deleting Kia Rio");
        car.print();
        System.out.println("\n");

        Vehicle motorcycle = new Motorcycle("Suzuki",1);
        motorcycle.setModelName(0,"V-strom");
        motorcycle.setModelPrice("v-strom",30000);
        motorcycle.addModel("Boulevard", 495000);
        motorcycle.addModel("Rm-z450", 400000);
        motorcycle.print();
        System.out.println("\n");

        motorcycle.deleteModel("Boulevard");
        System.out.println("After deleting Boulevard");
        motorcycle.print();
        System.out.println("\n");

        System.out.println("Average: " + VehicleUtil.average(car));
        VehicleUtil.printModelPrices(car);
        VehicleUtil.printModels(car);
        System.out.println("\n");

        System.out.println("Average: "+ VehicleUtil.average(motorcycle));
        VehicleUtil.printModelPrices(motorcycle);
        VehicleUtil.printModels(motorcycle);
        }catch (DuplicateModelNameException | NoSuchModelNameException e){
            e.printStackTrace();
        }
    }
}
