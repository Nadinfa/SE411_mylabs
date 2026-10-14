package edu.psu.se411.lab09_inventory.model;

@FunctionalInterface
public interface SearchCriteria<T> {

    boolean matches(T item);
}
