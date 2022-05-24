import api.Vehicle;
import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import factory.MotorcycleFactory;
import factory.QuadBikeFactory;
import impl.Car;
import impl.Motorcycle;
import impl.QuadBike;
import impl.Scooter;
import util.VehicleFactoryUtil;
import util.VehicleUtil;

import java.lang.reflect.*;


public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {

        try {
            Class<?> vClass =  Class.forName(args[0]);
            Object result =  vClass.getConstructor(String.class, int.class)
                             .newInstance(args[4],Integer.parseInt(args[5]));
            Method method = vClass.getMethod(args[1],String.class, double.class);
            method.invoke(result, args[2], Double.parseDouble(args[3]));
            System.out.println(result);

        } catch (ClassNotFoundException |
                InvocationTargetException |
                InstantiationException |
                IllegalAccessException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------");

        Vehicle m = new Motorcycle("BMV", 3);
        Vehicle v = VehicleUtil.createVehicle("BMV",3,m);
        System.out.println(m);
        System.out.println(v);
        System.out.println(v != null ? v.getClass() : "null");



        System.out.println("-------------------------");

        Vehicle scooter = new Scooter("Veter",1);
        scooter.addModel("Kafka",5000);
        scooter.addModel("Ddqwe",11420);
        scooter.addModel("sfsdf",1111);
        scooter.print();

        QuadBike quadBike = new QuadBike("Speed",1);
        quadBike.addModel("A", 1);
        quadBike.addModel("B", 2);
        quadBike.print();

        System.out.println(VehicleUtil.average(m,scooter,quadBike));

        VehicleFactoryUtil.setVehicleFactory(new MotorcycleFactory());
        Vehicle vehicle = VehicleUtil.readVehicle();
        VehicleUtil.writeVehicle(vehicle);

    }
}
