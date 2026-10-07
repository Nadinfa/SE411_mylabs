package edu.spu.se411.lab08;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSubject implements Subject {

    protected Set<Observer> observers = new HashSet<>();

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

}