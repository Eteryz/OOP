package util;

import api.Vehicle;
import exception.DuplicateModelNameException;

import java.io.*;
import java.util.Arrays;


public class VehicleUtil {

    public static double average(Vehicle v) {
        double[] modelPrices = v.getModelPrices();
        double answer = 0;
        for (double modelPrice : modelPrices) {
            answer += modelPrice;
        }
        return Double.isNaN(answer) ? 0 : answer / modelPrices.length;
    }

    public static void printModels(Vehicle v) {
        v.print();
    }

    public static void printModelPrices(Vehicle v) {
        System.out.println(Arrays.toString(v.getModelPrices()));
    }

    //запись информации о транспортном средстве в байтовый поток (использовать DataOutputStream)
    // записываем в последовательности: длина строки,бренд(String); количество моделей,длина строки, название модели...длина строки, название модели; цена модели ... цена модели.
    public static void outputVehicle(Vehicle v, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeInt(v.getBrand().length());
        //записываем бренд
        for (int i = 0; i < v.getBrand().length(); i++) {
            dos.writeByte(v.getBrand().getBytes()[i]);
        }
        //записываем имена моделей
        dos.writeInt(v.getLength());
        String[] s = v.getModelNames();
        for (String value : s) {
            int l = value.length();
            dos.writeInt(l);
            for (int j = 0; j < l; j++) {
                dos.writeByte(value.getBytes()[j]);
            }
        }
        //записываем цены моделей
        for (double value : v.getModelPrices()) {
            dos.writeDouble(value);
        }
        dos.flush();
    }

    //чтения информации о транспортном средстве из байтового потока (использовать DataInputStream)
    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException {
        Vehicle vehicle = VehicleFactoryUtil.getVehicle();
        DataInputStream dis = new DataInputStream(in);
            //считываем бренд
            int l = dis.readInt();
            byte[] bytes = new byte[l];
            for (int i = 0; i < l; i++) {
                bytes[i] = dis.readByte();
            }
            vehicle.setBrand(new String(bytes));
            //считываем названия моделей
            int count = dis.readInt();//количество моделей
            String[] names = new String[count];
            for (int i = 0; i < count; i++) {
                l = dis.readInt();
                bytes = new byte[l];
                for (int j = 0; j < l; j++) {
                    bytes[j] = dis.readByte();
                }
                names[i] = new String(bytes);
            }
            //считываем стоимость моделей и сказу же добавляе в объект
            for (int i = 0; i < count; i++) {
                vehicle.addModel(names[i], dis.readDouble());
            }
        return vehicle;
    }

    //записи информации о транспортном средстве в символьный поток (использовать PrintWriter)
    public static void writeVehicle(Vehicle v, Writer out) {
        PrintWriter pw = new PrintWriter(out);
        pw.println(v.getBrand());
        pw.println(v.getLength());
        for (String s: v.getModelNames()) {
            pw.println(s);
        }
        for (double d : v.getModelPrices()) {
            pw.println(d);
        }
        pw.flush();
    }

    //чтения информации о транспортном средстве из символьного потока (использовать BufferedReader или StreamTokenizer)
    public static Vehicle readVehicle(Reader in) throws IOException, DuplicateModelNameException {
        Vehicle vehicle = VehicleFactoryUtil.getVehicle();

        BufferedReader br = new BufferedReader(in);
        vehicle.setBrand(br.readLine());
        int size = Integer.parseInt(br.readLine());
        String [] names = new String[size];
        for (int i = 0; i < size; i++) {
            names[i] = br.readLine();
        }
        for (int i = 0; i < size ; i++) {
            vehicle.addModel(names[i],Double.parseDouble(br.readLine()));
        }
        return vehicle;
    }
}