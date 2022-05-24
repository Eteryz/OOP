package threads.file;

import api.Vehicle;
import util.VehicleFactoryUtil;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class ThreadReadFile implements Runnable{

    private File file;

    private BlockingQueue<Vehicle> queue;

    public ThreadReadFile(String fileName, BlockingQueue<Vehicle> queue) {
        this.file = new File(fileName);
        this.queue = queue;
    }

    @Override
    public void run() {
        try (FileReader fr = new FileReader(file)) {
            String brand = new BufferedReader(fr).readLine();
            Vehicle vehicle = VehicleFactoryUtil.getVehicle();
            vehicle.setBrand(brand);
            queue.put(vehicle);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
