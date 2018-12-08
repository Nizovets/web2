package com.labs.paralels22;

import java.util.Random;

public class RandomValue {

    //по рівномірному закону
    double getRandomValue(int minT, int maxT){
        Random rand = new Random();
        double x = (double)rand.nextInt(maxT)/maxT;
        return minT + x * (maxT-minT);
    }
}
