package threads.reentrantLock;

import api.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadPricesLock implements Runnable{

    private Vehicle vehicle;

    private ReentrantLock locker;

    public ThreadPricesLock(Vehicle vehicle, ReentrantLock locker) {
        this.vehicle = vehicle;
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            double[] prices = vehicle.getModelPrices();
            for (int i = 0; i < prices.length; i++) {
                System.out.println(prices[i]);
            }
        }finally {
            locker.unlock();
        }
    }
}
