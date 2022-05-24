package factory;

import api.Vehicle;
import impl.Moped;


public class MopedFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Moped();
    }
}
