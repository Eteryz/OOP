
import api.Vehicle;
import exception.NoSuchModelNameException;
import impl.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws NoSuchModelNameException{

        Vehicle[] vehicles = new Vehicle[]{
                new Car("car",2),
                new Motorcycle("motorcycle",2),
                new Moped("moped",2),
                new QuadBike("bike",2),
                new Scooter("scooter",2)
        };
        Vehicle[] vehicles2 = new Vehicle[]{
                new Car("car",2),
                new Motorcycle("motorcycle",2),
        };
        Vehicle[] vehicles3 = new Vehicle[]{
                new Car("car",2),
        };
        vehicles3[0].setModelPrice("model0",2);
        vehicles[2].setModelPrice("model1",10);
        System.out.println(Arrays.toString(vehicles)+"\n\n");


        ExecutorService exec = Executors.newFixedThreadPool(9);
        for (int i = 0; i < 3; i++) {
            exec.execute(() -> f(vehicles));
            exec.execute(() -> f(vehicles2));
            exec.execute(() -> f(vehicles3));
        }
        exec.shutdown();
    }

    public static void f(Vehicle[] vehicles){
        try (Socket socket = new Socket(InetAddress.getLocalHost(),1024);
             DataInputStream reader = new DataInputStream(socket.getInputStream());
             ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        ){
            writer.writeObject(vehicles);
            System.out.println("Данные отправлены на сервер!");
            System.out.println("Cреднее арифметическое значение цен моделей: "+ reader.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
