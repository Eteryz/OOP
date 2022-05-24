package thread;

import api.Vehicle;
import util.VehicleUtil;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ThreadServer implements Runnable{

    private Socket clientSocket;

    private int count;

    public ThreadServer(Socket socket, int count) {
        this.clientSocket = socket;
        this.count = count;
    }

    @Override
    public void run() {
        try(ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
            DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
        ){
            Vehicle[] vehicles = (Vehicle[]) reader.readObject();
            writer.writeDouble(VehicleUtil.average(vehicles));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("Выполнен запрос с №: "+ count);
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
