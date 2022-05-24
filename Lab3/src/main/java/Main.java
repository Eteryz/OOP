import api.Vehicle;
import exception.DuplicateModelNameException;
import factory.CarFactory;
import factory.MotorcycleFactory;
import factory.VehicleFactoryUtil;
import task1.VehicleUtil;

import java.io.*;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException {

        Vehicle vehicle = VehicleFactoryUtil.getVehicle();
        System.out.println(vehicle.getClass());
        vehicle.setBrand("Lada");
        vehicle.addModel("Priora",400000);
        vehicle.addModel("Vesta",860000);


        /*VehicleFactoryUtil.setVehicleFactory(new MotorcycleFactory());
        Vehicle vehicle = VehicleFactoryUtil.getVehicle();
        vehicle.setBrand("BMW");
        vehicle.addModel("Alfa",40000);
        vehicle.addModel("A3",500000);

         */

        vehicle.print();

        String fileName = "data.bin";
        String fileName2 = "data2.txt";
        String fileName3 = "data3.ser";
        try(
                FileOutputStream fos = new FileOutputStream(fileName);
                FileInputStream fis = new FileInputStream(fileName);
                FileWriter fw = new FileWriter(fileName2);
                FileReader fr = new FileReader(fileName2);

                FileOutputStream fos2 = new FileOutputStream(fileName3);
                ObjectOutputStream oos = new ObjectOutputStream(fos2);

                FileInputStream fis2 = new FileInputStream(fileName3);
                ObjectInputStream ois = new ObjectInputStream(fis2);
                )
        {

            VehicleUtil.outputVehicle(vehicle, fos);
            Vehicle vehicleInput = VehicleUtil.inputVehicle(fis);

            vehicleInput.print();

            VehicleUtil.writeVehicle(vehicle, fw);
            Vehicle vehicleRead = VehicleUtil.readVehicle(fr);
            vehicleRead.print();

            System.out.println("Сериализация: ");
            oos.writeObject(vehicle);
            oos.flush();

            System.out.println("Десериализация: ");
            Vehicle vehicleSer = (Vehicle) ois.readObject();
            vehicleSer.print();


            System.out.println("Через System.in and System.out(ввести нужно бренд, кол-во, имена, цены)");
            Vehicle vehicleIn = VehicleUtil.readVehicle(new InputStreamReader(System.in));
            VehicleUtil.writeVehicle(vehicleIn,new OutputStreamWriter(System.out));

            System.out.println("---------------------");

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
