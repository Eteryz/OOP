package factory;

import api.Vehicle;
import impl.QuadBike;

public class QuadBikeFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new QuadBike();
    }
}
