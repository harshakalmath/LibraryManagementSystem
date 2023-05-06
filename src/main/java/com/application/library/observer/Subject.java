package com.application.library.observer;
/* Implementing observer pattern
 * */
public interface Subject {
    /**
     * Method to register observers
     * @param o
     */
    public void registerObserver(Observer o);
    /**
     * Method to deregister observers
     * @param o
     */
    public void unregisterObserver(Observer o);
    /**
     * Method to notify observers
     * @param data
     */
    public void notifyObservers(ObserverData data);
}
