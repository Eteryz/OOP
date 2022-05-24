package impl;

import api.Vehicle;
import exception.DuplicateModelNameException;
import exception.ModelPriceOutOfBoundsException;
import exception.NoSuchModelNameException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Motorcycle implements Vehicle, Cloneable {

    private static final long serialVersionUID = 1L;

    private String brand;

    private int size = 0;

    private class Model implements Serializable, Cloneable {

        private String name = null;

        private double price = Double.NaN;

        private Model prev = null;

        private Model next = null;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public Model() {
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Model)) return false;
            //if (o == null || getClass() != o.getClass()) return false;
            Model model = (Model) o;
            return Double.compare(model.price, price) == 0 && Objects.equals(name, model.name);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + (Double.isNaN(price) ? 0 : (int) price);
            return result;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Model{ ")
                    .append("name = '")
                    .append(name)
                    .append('\'')
                    .append(", price = ")
                    .append(price)
                    .append(", prev = ")
                    .append(prev.getName())
                    .append(", next = ")
                    .append(next.getName())
                    .append('}');
            return stringBuffer.toString();
        }
    }

    private Model head = new Model();

    {
        head.prev = head;
        head.next = head;
    }

    @Override
    public int getLength() {
        return size;
    }

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
        if (price < 0 || BigDecimal.valueOf(price).scale() > 2)
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
        if (price < 0 || BigDecimal.valueOf(price).scale() > 2)
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

    @Override
    public void print() {
        System.out.print("Motorcycle { brand= " + brand + '\t');
        Model current = head.next;
        while (current != head) {
            System.out.print(current.toString() + "\t");
            current = current.next;
        }
        System.out.println();
    }

    public Motorcycle(String brand, int size) {
        this.brand = brand;
        for (int i = 0; i < size; i++) {
            Model model = new Model("model" + i, 0);
            model.next = head;
            model.prev = head.prev;
            head.prev.next = model;
            head.prev = model;
            this.size++;
        }
    }

    public Motorcycle() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Motorcycle motorcycle = (Motorcycle) o;
        Model current = head.next;
        Model model = motorcycle.head.next;
        while (current != head) {
            if (!(current.equals(model))) return false;
            model = model.next;
            current = current.next;
        }
        return size == motorcycle.size && Objects.equals(brand, motorcycle.brand);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (brand == null ? 0 : brand.hashCode());
        Model current = head;
        do {
            result = prime * result + current.hashCode();
            current = current.next;
        } while (current != head);
        return result;
    }

    @Override
    public Object clone() {
        Motorcycle clone = null;
        try {
            clone = (Motorcycle) super.clone();
            clone.head = new Model();
            clone.head.next = clone.head;
            clone.head.prev = clone.head;
            Model current = head.next;
            Model cloneModel;
            while (current != head) {
                cloneModel = (Model) current.clone();
                cloneModel.next = clone.head;
                cloneModel.prev = clone.head.prev;
                clone.head.prev.next = cloneModel;
                clone.head.prev = cloneModel;
                current = current.next;
            }

        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
        return clone;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Motorcycle{")
                .append(" brand = '")
                .append(brand)
                .append('\'')
                .append(", models = ");
        Model current = head.next;
        while (current != head) {
            stringBuffer.append(current.toString());
            if ((current = current.next) != head)
                stringBuffer.append(",\t");
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
