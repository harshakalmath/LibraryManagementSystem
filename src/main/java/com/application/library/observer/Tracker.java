package com.application.library.observer;

public class Tracker implements Observer {
    private int numberOfBooksLent;
    int n;

    private static Tracker instance = null;

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
     * Sets and adds staff Earning and FNCD Earning
     * @param observerData
     */
    @Override
    public void push(ObserverData observerData) {
        if (observerData.numberOfBooksLent != null) {
            this.numberOfBooksLent += observerData.numberOfBooksLent;
        }
    }

    /**
     * Displays the tracker data - Staff Earning, FNCD Earning
     */
    public void display() {
        System.out.println("Tracker: Day "+ n);
        System.out.println("Number of books Lent:" + numberOfBooksLent);
    }

    public int getNumberOfBooksLent() {
        return numberOfBooksLent;
    }
}
