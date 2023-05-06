package com.application.library.observer;

public class Tracker implements Observer {
    private int numberOfBooksLent;
    int n;

    private static Tracker instance = null;


    /*Implementing Singleton pattern*/
    public static Tracker getInstance()
    {
        //We have used lazy method to create the singleton object here

        if(instance == null)
            instance = new Tracker();
        return instance;
    }

    public void setTrackerDay(int n){
        this.n = n;
    }
    public void clearTracker(){
        this.numberOfBooksLent = 0;
        this.n = 0;
    }

    /**
     *
     * @param observerData
     */
    @Override
    public void push(ObserverData observerData) {
        if (observerData.numberOfBooksLent != null) {
            this.numberOfBooksLent += observerData.numberOfBooksLent;
        }
    }


    public void display() {
        System.out.println("Tracker: Day "+ n);
        System.out.println("Number of books Lent:" + numberOfBooksLent);
    }

    public int getNumberOfBooksLent() {
        return numberOfBooksLent;
    }
}
