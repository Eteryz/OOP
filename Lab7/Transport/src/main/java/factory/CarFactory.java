package factory;

import api.Vehicle;
import impl.Car;

public class CarFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new Car();
    }

}