package threads.reentrantLock;

import api.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadNamesLock implements Runnable{

    private Vehicle vehicle;

    private ReentrantLock locker;

    public ThreadNamesLock(Vehicle vehicle, ReentrantLock locker) {
        this.vehicle = vehicle;
        this.locker = locker;
    }

    @Override
    public void run() {
        locker.lock();
        try {
            String[] names = vehicle.getModelNames();
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i]);
            }
        }finally {
            locker.unlock();
        }
    }
}
