package com.application.library;


import com.application.library.observer.Observer;
import com.application.library.observer.ObserverData;
import com.application.library.observer.Subject;

import java.util.LinkedList;

public class ObserverHelper implements Subject {
    private LinkedList<Observer> observerList = new LinkedList<>();
    private static ObserverHelper instance = null;

    public static ObserverHelper getInstance()
    {
        //We have used lazy method to create the singleton object here
        if(instance == null)
            instance = new ObserverHelper();
        return instance;
    }
    /**
     * Method to register observers
     *
     * @param o
     */
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    /**
     * Method to deregister observers
     *
     * @param o
     */
    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(o);
    }

    /**
     * Method to notify observers
     *
     * @param data
     */
    @Override
    public void notifyObservers(ObserverData data) {
        observerList.forEach(observer -> {
            observer.push(data);
        });
    }

    public ObserverData createObserverData(Integer numberOfBooksLent) {
        return new ObserverData(numberOfBooksLent);
    }
}
