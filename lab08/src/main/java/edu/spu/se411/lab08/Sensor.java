package edu.spu.se411.lab08;

import java.util.HashSet;

public class Sensor extends AbstractSubject implements Cloneable {

    private String name;
    private SensorType type;
    private double reading;

    public Sensor(String name, SensorType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SensorType getType() {
        return type;
    }

    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        this.reading = reading;
        notifyObservers();
    }

    @Override
    public Sensor clone() {
        try {
            Sensor copy = (Sensor) super.clone();

            copy.observers = new HashSet<>();

            return copy;

        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}