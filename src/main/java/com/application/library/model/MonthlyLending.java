package com.application.library.model;

public class MonthlyLending implements BookLending{
    @Override
    public double lending(double fine) {
        return fine+(0.8*fine);
    }
}
