package task5;

import task4.Vehicle;

import java.util.Arrays;

public class VehicleUtil {

    public static double average(Vehicle i){
        double[] modelPrices = i.getModelPrices();
        double answer = 0;
        for (double modelPrice : modelPrices) {
            answer += modelPrice;
        }
        return Double.isNaN(answer) ? 0 : answer / modelPrices.length;
    }

    public static void printModels(Vehicle i){
            i.print();
    }

    public  static void printModelPrices(Vehicle i){
        System.out.println(Arrays.toString(i.getModelPrices()));
    }
}
