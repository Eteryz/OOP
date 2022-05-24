import api.Vehicle;
import factory.*;
import impl.Car;
import impl.Motorcycle;
import impl.QuadBike;
import impl.Scooter;
import threads.ThreadBrandRunnable;
import threads.ThreadNames;
import threads.ThreadPrices;
import threads.file.ThreadReadFile;
import threads.reentrantLock.ThreadNamesLock;
import threads.reentrantLock.ThreadPricesLock;
import threads.synchronizer.ThreadNamesRunnable;
import threads.synchronizer.ThreadPricesRunnable;
import threads.synchronizer.TransportSynchronizer;
import util.VehicleFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Vehicle vehicle1 = new Motorcycle("motorcycle", 5);
        /*1
        ThreadNames t1 = new ThreadNames(vehicle1);
        ThreadPrices t2 = new ThreadPrices(vehicle1);
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        */

        /*
        TransportSynchronizer sync = new TransportSynchronizer(vehicle1);
        Thread t1 = new Thread(new ThreadNamesRunnable(sync));
        Thread t2 = new Thread(new ThreadPricesRunnable(sync));
        */


        /*3
        ReentrantLock loker = new ReentrantLock();
        Thread t1 = new Thread(new ThreadNamesLock(vehicle1,loker));
        Thread t2 = new Thread(new ThreadPricesLock(vehicle1,loker));
        */

        /*4
        Vehicle vehicle2 = new Scooter("scooter",3);
        Vehicle vehicle3 = new QuadBike("quadBike",3);
        Vehicle vehicle4 = new Car("car",3);
        Runnable r1 = new ThreadBrandRunnable(vehicle1);
        Runnable r2 = new ThreadBrandRunnable(vehicle2);
        Runnable r3 = new ThreadBrandRunnable(vehicle3);
        Runnable r4 = new ThreadBrandRunnable(vehicle4);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(r1);
        service.execute(r2);
        service.execute(r3);
        service.execute(r4);
        service.shutdown();
        */

        /*
        String[] fileNames = new String[]{
                "src\\main\\resources\\testFile\\Test1.txt",
                "src\\main\\resources\\testFile\\Test2.txt",
                "src\\main\\resources\\testFile\\Test3.txt",
                "src\\main\\resources\\testFile\\Test4.txt",
                "src\\main\\resources\\testFile\\Test5.txt"
        };
        BlockingQueue<Vehicle> queue = new ArrayBlockingQueue<>(2);

        VehicleFactoryUtil.setVehicleFactory(new CarFactory());

        for (int i = 0; i < fileNames.length; i++) {
            (new Thread(new ThreadReadFile(fileNames[i],queue))).start();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take().getBrand());
        }

        */

        ///*
        //t1.start();
        //t2.start();

        //t1.join();
        //t2.join();

       // */
        //System.out.println("Все потоки закончили работу, программа завершена");
    }
}
