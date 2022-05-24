package factory;

import api.Vehicle;
import impl.Scooter;

public class ScooterFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Scooter();
    }
}
