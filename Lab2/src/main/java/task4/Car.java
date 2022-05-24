package task4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import exception.*;

public class Car implements Vehicle {

    private class Model {

        private String name = null;

        private double price = Double.NaN;

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

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public Model() {
        }

        @Override
        public String toString() {
            return " Model{ " +
                    "name = '" + name + '\'' +
                    ", price = " + price +
                    " }";
        }
    }

    private String brand;

    @Override
    public int getLength() {
        return this.models.length;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    private Model[] models;

    public Model[] getModels() {
        return models;
    }

    private Model getModel(String name) throws NoSuchModelNameException {
        for (Model model : models) {
            if (model != null && model.getName() != null && model.getName().equalsIgnoreCase(name)) {
                return model;
            }
        }
        throw new NoSuchModelNameException(name);
    }

    @Override
    public void setModelName(int index, String name) throws DuplicateModelNameException {
        if (isDuplicateName(name))
            throw new DuplicateModelNameException(name);
        models[index].setName(name);
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
        String[] modelNames = new String[models.length];
        for (int i = 0; i < modelNames.length; i++) {
            modelNames[i] = models[i].getName();
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
        double[] modelPrices = new double[models.length];
        for (int i = 0; i < modelPrices.length; i++) {
            modelPrices[i] = models[i].getPrice();
        }
        return modelPrices;
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price <= 0 || BigDecimal.valueOf(price).scale() > 2)
            throw new ModelPriceOutOfBoundsException(price);
        else if (isDuplicateName(name))
            throw new DuplicateModelNameException(name);
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(name, price);
    }

    private boolean isDuplicateName(String name) {
        for (Model model : models) {
            if (model != null && model.getName() != null && model.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        System.out.println("Car{ brand= " + brand + '\t' + Arrays.toString(models));
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        int index = new ArrayList<>(Arrays.asList(models)).indexOf(getModel(modelName));
        System.arraycopy(models, index + 1, models, index, models.length - index - 1);
        models = Arrays.copyOf(models, models.length - 1);
    }

    public Car(String brand, int modelsArrayLength) {
        this.brand = brand;
        this.models = new Model[modelsArrayLength];
        for (int i = 0; i < this.models.length; i++) {
            this.models[i] = new Model();
        }
    }
}
