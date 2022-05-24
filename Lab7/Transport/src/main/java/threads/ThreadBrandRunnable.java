package threads;

import api.Vehicle;

public class ThreadBrandRunnable implements Runnable{

    private Vehicle vehicle;

    public ThreadBrandRunnable(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        System.out.println(vehicle.getBrand());
    }
}
