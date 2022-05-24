package task4;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;

public interface Vehicle {

    int getLength();

    String getBrand();

    void setBrand(String brand);

    void setModelName(int index, String name) throws DuplicateModelNameException;

    void setModelName(String currentName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;

    String[] getModelNames();

    void setModelPrice(String name, double price) throws NoSuchModelNameException;

    double getModelPrice(String modelName) throws NoSuchModelNameException;

    double[] getModelPrices();

    void addModel(String name, double price) throws DuplicateModelNameException;

    void deleteModel(String modelName) throws NoSuchModelNameException;

    void print();
}
