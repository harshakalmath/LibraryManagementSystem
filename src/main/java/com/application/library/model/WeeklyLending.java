package com.application.library.model;

public class WeeklyLending implements BookLending{
    @Override
    public double lending() {
        return fine+(0.5*fine);
    }
}
