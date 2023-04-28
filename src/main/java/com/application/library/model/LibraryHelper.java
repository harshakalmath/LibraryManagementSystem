package com.application.library.model;

import java.util.Random;

public class LibraryHelper {

    public static String weekly = "weekly";
    public static String biWeekly = "bi-weekly";
    public static String monthly = "monthly";

    public static String chooseLendingBehaviour(){
        Random random = new Random();
        int pick = random.nextInt(0,3);
        if(pick==0)
            return weekly;
        else if(pick==1)
            return biWeekly;
        else
            return monthly;
    }

    public static boolean chooseLateReturn(){
        Random random = new Random();
        int pick = random.nextInt(0,2);
        if(pick==0)
            return false;
        else return true;

    }



}
