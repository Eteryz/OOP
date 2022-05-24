package factory;

import api.Vehicle;
import impl.Motorcycle;

public class MotorcycleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle() {
        return new Motorcycle();
    }

}