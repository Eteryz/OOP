package threads;

import api.Vehicle;

public class ThreadPrices extends  Thread{

    private Vehicle vehicle;

    public ThreadPrices(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        double[] prices = vehicle.getModelPrices();
        for (int i = 0; i < prices.length; i++) {
            System.out.println(prices[i]);
        }
    }
}
