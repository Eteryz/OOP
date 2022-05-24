package impl;

import api.Vehicle;
import exception.DuplicateModelNameException;
import exception.ModelPriceOutOfBoundsException;
import exception.NoSuchModelNameException;

import java.math.BigDecimal;
import java.util.HashMap;

public class Scooter implements Vehicle {

    private String brand;

    private HashMap<String, Double> models = new HashMap<>();

    public Scooter(String brand, int size) {
        this.brand = brand;
        for (int i = 0; i < size; i++) {
            models.put("model" + i, 0.0);
        }
    }

    public Scooter() {
    }

    public HashMap<String, Double> getModels() {
        return new HashMap<>(models);
    }

    public void setModels(HashMap<String, Double> models) {
        this.models = models;
    }

    @Override
    public int getLength() {
        return models.size();
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void setModelName(String currentName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        Double p = models.get(currentName);
        deleteModel(currentName);
        addModel(newName, p);
    }

    @Override
    public String[] getModelNames() {
        return models.keySet().toArray(new String[0]);
    }

    @Override
    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        if (models.get(name) == null)
            throw new NoSuchModelNameException(name);
        models.replace(name, models.get(name), price);
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        if (models.get(modelName) == null)
            throw new NoSuchModelNameException(modelName);
        return models.get(modelName);
    }

    @Override
    public double[] getModelPrices() {
        Double[] arr = models.values().toArray(new Double[0]);
        double[] res = new double[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0 || BigDecimal.valueOf(price).scale() > 2)
            throw new ModelPriceOutOfBoundsException(price);
        else if (models.get(name) != null)
            throw new DuplicateModelNameException(name);
        models.put(name, price);
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        if (models.remove(modelName) == null)
            throw new NoSuchModelNameException(modelName);
    }

    @Override
    public void print() {
        System.out.println("Scooter { brand= " + brand + '\t' + models.toString());
    }
}
