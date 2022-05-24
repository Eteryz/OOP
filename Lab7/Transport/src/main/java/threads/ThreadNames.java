package threads;

import api.Vehicle;

public class ThreadNames extends Thread{

    private Vehicle vehicle;

    public ThreadNames(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        String[] names = vehicle.getModelNames();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }
}
