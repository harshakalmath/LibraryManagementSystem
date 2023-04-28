package com.application.library.model;

public class BiWeeklyLending implements BookLending{
    @Override
    public double lending(double fine) {
        return fine+(0.7*fine);
    }
}
