package util;

import api.Vehicle;
import factory.CarFactory;
import factory.VehicleFactory;


public class VehicleFactoryUtil {

    private static VehicleFactory vehicleFactory = new CarFactory();

    public static void setVehicleFactory(VehicleFactory vehicleFactory) {
        VehicleFactoryUtil.vehicleFactory = vehicleFactory;
    }

    public static Vehicle getVehicle(){
        return vehicleFactory.createVehicle();
    }
}
