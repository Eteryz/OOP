
import api.Vehicle;
import util.VehicleUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args)  {

        try(ServerSocket serverSocket = new ServerSocket(1024)){
            System.out.println("Сервер запущен!");
            int count = 0;
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
                    DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
                ) {
                    count++;
                    System.out.println("Есть подключение!");

                    Vehicle[] vehicles = (Vehicle[]) reader.readObject();
                    writer.writeDouble(VehicleUtil.average(vehicles));
                    System.out.println("Выполнен запрос с №: " + count);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
