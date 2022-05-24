import api.Vehicle;
import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;
import impl.Car;
import impl.Motorcycle;


public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {

        Car v = new Car();
        v.setBrand("Lada");
        v.addModel("granta",550000);
        v.addModel("vesta", 1200000);
        System.out.println(v);
        Vehicle v2 = (Vehicle) ((Car)v).clone();
        System.out.println(v2);

        if(v.hashCode()== v2.hashCode()){
            System.out.println(v.equals(v2));

        }else
            System.out.println(false);

        v2.setModelName(0, "231fdsdfs");
        //v2.setBrand("dfd");
        //v2.setModelPrice("vesta", 1);
        System.out.println("После изменения");
        System.out.println(v);
        System.out.println(v2);

        if(v.hashCode()== v2.hashCode()){
            System.out.println(v.equals(v2));

        }else
            System.out.println(false);

        System.out.println("\n\n");

        Vehicle m = new Motorcycle();
        m.setBrand("BMW");
        m.addModel("rs1", 50000);
        m.addModel("alfa", 30000);
        System.out.println(m);
        Vehicle m2 = (Vehicle) ((Motorcycle)m).clone();
        //m2.deleteModel("alfa");
        System.out.println(m2);

        if(m.hashCode()== m2.hashCode()){
            System.out.println(m.equals(m2));

        }else
            System.out.println(false);

        m2.setModelName(0, "11111");
        //m2.setBrand("dfd");
        //m2.setModelPrice("rs1", 1);
        System.out.println("После изменения");
        System.out.println(m);
        System.out.println(m2);

        if(m.hashCode()== m2.hashCode()){
            System.out.println(m.equals(m2));

        }else
            System.out.println(false);

    }
}
