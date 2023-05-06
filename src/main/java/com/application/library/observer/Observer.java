package com.application.library.observer;

public interface Observer {
    /**
     * Method to push data to the observer
     * @param observerData
     */
    public void push(ObserverData observerData);
}
