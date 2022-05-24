package impl;

import api.Vehicle;
import exception.DuplicateModelNameException;
import exception.ModelPriceOutOfBoundsException;
import exception.NoSuchModelNameException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class QuadBike implements Vehicle {

    private String brand;

    private ArrayList<Model> models = new ArrayList<>();

    public QuadBike(String brand, int size) {
        this.brand = brand;
        for (int i = 0; i < size; i++) {
            models.add(new Model("model" + i, 0));
        }
    }

    public QuadBike() {
    }

    public ArrayList<Model> getModels() {
        return new ArrayList<>(this.models);
    }

    public void setModels(ArrayList<Model> list) {
        this.models = list;
    }

    private class Model {

        private String name = null;

        private double price = Double.NaN;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Model)) return false;
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
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" Model{ ")
                    .append("name = '")
                    .append(name)
                    .append('\'')
                    .append(", price = ")
                    .append(price)
                    .append(" }");
            return stringBuffer.toString();
        }
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
        if (isDuplicateName(newName))
            throw new DuplicateModelNameException(newName);
        AtomicBoolean flag = new AtomicBoolean(true);
        models.forEach(model -> {
            if (Objects.equals(model.getName(), currentName)) {
                flag.set(false);
                model.setName(newName);
            }
        });
        if (flag.get()) {
            throw new NoSuchModelNameException(currentName);
        }
    }

    @Override
    public String[] getModelNames() {
        String[] strings = new String[models.size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = models.get(i).getName();
        }
        return strings;
    }

    @Override
    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        if (price < 0 || BigDecimal.valueOf(price).scale() > 2)
            throw new ModelPriceOutOfBoundsException(price);
        AtomicBoolean flag = new AtomicBoolean(true);
        models.forEach(model -> {
            if (Objects.equals(model.getName(), name)) {
                flag.set(false);
                model.setPrice(price);
            }
        });
        if (flag.get()) {
            throw new NoSuchModelNameException(name);
        }
    }

    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        AtomicReference<Double> res = new AtomicReference<>((double) 0);
        AtomicBoolean flag = new AtomicBoolean(true);
        models.forEach(model -> {
            if (Objects.equals(model.getName(), modelName)) {
                flag.set(false);
                res.set(model.getPrice());
            }
        });
        if (flag.get()) {
            throw new NoSuchModelNameException(modelName);
        }
        return res.get();
    }

    @Override
    public double[] getModelPrices() {
        double[] doubles = new double[models.size()];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = models.get(i).getPrice();
        }
        return doubles;
    }

    @Override
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0 || BigDecimal.valueOf(price).scale() > 2)
            throw new ModelPriceOutOfBoundsException(price);
        else if (isDuplicateName(name))
            throw new DuplicateModelNameException(name);
        models.add(new Model(name, price));
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        if (!models.removeIf(model -> model.getName().equals(modelName)))
            throw new NoSuchModelNameException(modelName);
    }

    private boolean isDuplicateName(String name) {
        for (Model model : models) {
            if (model != null && model.getName() != null && model.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        System.out.println("QuadBike { brand= " + brand + '\t' + models.toString());
    }
}
