package edu.psu.se411.lab09_inventory.model;

public class ElectronicDevice extends Item {

    private String category;

    public ElectronicDevice(String id, String name, String category) {
        super(id, name);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return super.toString() + ", Category: " + category;
    }
}