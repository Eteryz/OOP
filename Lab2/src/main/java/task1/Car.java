package task1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import exception.*;

public class Car {

    private class Model{

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

        public Model(){}

        @Override
        public String toString() {
            return " Model{ " +
                    "name = '" + name + '\'' +
                    ", price = " + price +
                    " }";
        }
    }

    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    private Model[] models;

    public Model[] getModels() {
        return models;
    }

    private Model getModel(String name) throws NoSuchModelNameException{
        for (Model model : models) {
            if (model != null && model.getName() != null && model.getName().equalsIgnoreCase(name)) {
                return model;
            }
        }
        throw new NoSuchModelNameException(name);
    }

    public void setModelName(int index, String name) throws DuplicateModelNameException{
        if(isDuplicateName(name))
            throw new DuplicateModelNameException(name);
        models[index].setName(name);
    }

    public void setModelName(String currentName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        if(isDuplicateName(newName))
            throw new DuplicateModelNameException(newName);
        Model model = getModel(currentName);
        model.setName(newName);
    }

    public String[] getModelNames(){
        String[] modelNames = new String[models.length];
        for (int i = 0; i < modelNames.length; i++) {
            modelNames[i] = models[i].getName();
        }
        return modelNames;
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException{
       try {
           if(price <= 0 ||  BigDecimal.valueOf(price).scale() > 2)
               throw new ModelPriceOutOfBoundsException(price);
           Model model = getModel(name);
           model.setPrice(price);
       }catch (ModelPriceOutOfBoundsException e){
           e.printStackTrace();
       }
    }

    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        return getModel(modelName).getPrice();
    }

    public double[] getModelPrices(){
        double[] modelPrices = new double[models.length];
        for (int i = 0; i < modelPrices.length; i++) {
            modelPrices[i] = models[i].getPrice();
        }
        return modelPrices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException{
        try {
            if (price <= 0 || BigDecimal.valueOf(price).scale() > 2)
                throw new ModelPriceOutOfBoundsException(price);
            else if (isDuplicateName(name))
                throw new DuplicateModelNameException(name);
            models = Arrays.copyOf(models, models.length + 1);
            models[models.length - 1] = new Model(name, price);
        }catch (ModelPriceOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    private boolean isDuplicateName(String name){
        for (Model model: models) {
            if(model.getName() != null && model.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void deleteModel(String modelName) throws NoSuchModelNameException{
        int index = new ArrayList<>(Arrays.asList(models)).indexOf(getModel(modelName));
        System.arraycopy(models, index + 1, models, index, models.length - index - 1);
        models = Arrays.copyOf(models, models.length - 1);
    }

    public int getModelsArrayLength(){
        return this.models.length;
    }

    public Car(String brand, int modelsArrayLength) {
        this.brand = brand;
        this.models = new Model[modelsArrayLength];
        for (int i = 0; i < this.models.length; i++) {
            this.models[i] = new Model();
        }
    }

    @Override
    public String toString() {
        return "Task1.Car{ " +
                "brand = '" + brand + '\'' +
                ", models = " + Arrays.toString(models) +
                " }";
    }
}
