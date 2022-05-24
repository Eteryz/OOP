import thread.ThreadServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(1024);){
            System.out.println("Сервер запущен!");
            int count = 0;
            while (true) {
                count++;
                Socket clientSocket = serverSocket.accept();
                System.out.println("Есть подключение!");
                new Thread(new ThreadServer(clientSocket,count)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
