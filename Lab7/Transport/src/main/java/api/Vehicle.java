package api;

import exception.DuplicateModelNameException;
import exception.NoSuchModelNameException;

import java.io.Serializable;


public interface Vehicle extends Serializable{

    int getLength();

    String getBrand();

    void setBrand(String brand);

    void setModelName(String currentName, String newName) throws DuplicateModelNameException, NoSuchModelNameException;

    String[] getModelNames();

    void setModelPrice(String name, double price) throws NoSuchModelNameException;

    double getModelPrice(String modelName) throws NoSuchModelNameException;

    double[] getModelPrices();

    void addModel(String name, double price) throws DuplicateModelNameException;

    void deleteModel(String modelName) throws NoSuchModelNameException;

    void print();
}
