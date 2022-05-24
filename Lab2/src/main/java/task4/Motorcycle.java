package task4;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;

import exception.*;

public class Motorcycle implements Iterable<Motorcycle.Model>, Vehicle {

    class Model {

        private String name = null;

        private double price = Double.NaN;

        private Model prev = null;

        private Model next = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Model getPrev() {
            return prev;
        }

        public void setPrev(Model prev) {
            this.prev = prev;
        }

        public Model getNext() {
            return next;
        }

        public void setNext(Model next) {
            this.next = next;
        }

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public Model() {
        }

        @Override
        public String toString() {
            return "Model{ " +
                    "name = '" + name + '\'' +
                    ", price = " + price +
                    ", prev = " + prev.getName() +
                    ", next = " + next.getName() +
                    '}';
        }
    }

    private final Model head = new Model();

    {
        head.prev = head;
        head.next = head;
    }

    private int size = 0;
    // далее код по заданию

    @Override
    public int getLength() {
        return size;
    }

    private String brand;

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    private Model getModel(String name) throws NoSuchModelNameException {
        Model model = head.next;
        while (model != head) {
            if (model.getName() != null && model.getName().equalsIgnoreCase(name)) {
                return model;
            }
            model = model.next;
        }
        throw new NoSuchModelNameException(name);
    }

    @Override
    public void setModelName(int index, String name) throws DuplicateModelNameException {
        if (isDuplicateName(name))
            throw new DuplicateModelNameException(name);
        if (index < size) {
            int i = 0;
            Model model = head.next;
            while (i != index) {
                model = model.next;
                i++;
            }
            model.setName(name);
        } else
            throw new NoSuchElementException();
    }

    @Override
    public void setModelName(String currentName, String newName) throws DuplicateModelNameException,
            NoSuchModelNameException {
        if (isDuplicateName(newName))
            throw new DuplicateModelNameException(newName);
        Model model = getModel(currentName);
        model.setName(newName);
    }

    @Override
    public String[] getModelNames() {
        String[] modelNames = new String[size];
        Model model = head.next;
        int i = 0;
        while (model != head) {
            modelNames[i] = model.getName();
            model = model.next;
            i++;
        }
        return modelNames;
    }

    @Override
    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        if (price <= 0 || BigDecimal.valueOf(price).scale() > 2)
            throw new ModelPriceOutOfBoundsException(price);
        Model model = getModel(name);
        model.setPrice(price);
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        return getModel(modelName).getPrice();
    }

    @Override
    public double[] getModelPrices() {
        double[] modelPrices = new double[size];
        Model model = head.next;
        int i = 0;
        while (model != head) {
            modelPrices[i] = model.getPrice();
            model = model.next;
            i++;
        }
        return modelPrices;
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price <= 0 || BigDecimal.valueOf(price).scale() > 2)
            throw new ModelPriceOutOfBoundsException(price);
        else if (isDuplicateName(name))
            throw new DuplicateModelNameException(name);
        Model model = new Model(name, price);
        model.next = head;
        model.prev = head.prev;
        head.prev.next = model;
        head.prev = model;
        size++;
    }

    private void addModel() {
        Model model = new Model();
        model.next = head;
        model.prev = head.prev;
        head.prev.next = model;
        head.prev = model;
        size++;
    }

    private boolean isDuplicateName(String name) {
        Model model = head.next;
        while (model != head) {
            if (model.getName() != null && model.getName().equalsIgnoreCase(name)) {
                return true;
            }
            model = model.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        Model model = getModel(modelName);
        model.prev.next = model.next;
        model.next.prev = model.prev;
        size--;
    }

    public void deleteModel(int index, Iterator<Model> i) {
        int count = 0;
        while (i.hasNext()) {
            i.next();
            if (count == index) {
                i.remove();
                size--;
                return;
            }
            count++;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void print() {
        System.out.print("Motorcycle { brand= " + brand + '\t');
        /*Model current = head.next;
        while (current != head){
            System.out.print(current.toString()+"\t");
            current = current.next;
        }

         */
        forEach(model -> System.out.print(model.toString() + "\t"));
    }

    public Motorcycle(String brand, int size) {
        this.brand = brand;
        for (int i = 0; i < size; i++) {
            addModel();
        }
    }

    @Override
    public Iterator<Model> iterator() {
        return new IteratorLinked();
    }

    private class IteratorLinked implements Iterator<Model> {

        private Model current = head;

        private int count = 0;

        @Override
        public boolean hasNext() {
            return count <= size;
        }

        @Override
        public Model next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Elements not found...");
            }
            Model value = current;
            current = current.next;
            this.count++;
            return value;
        }

        @Override
        public void remove() {
            Model value = current;
            value.prev.next = value.next;
            value.next.prev = value.prev;
        }
    }
}
