package com.labs.paralels22;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CPU {

    public static final int minTime = 500;
    public static final int maxTime = 700;
    public int number;
    public boolean occupation;
    public int[] countCrash;

    public int processedThread = 0;

    RandomValue rv = new RandomValue();

    void setNumber(int i){
        number = i;
    }

    int getNumber(){
        return number;
    }

    boolean getOccupation(){
        return occupation;
    }

    public void processing(int number, int[]cr) {

        countCrash = cr;
        Lock l = new ReentrantLock();
        if (l.tryLock()) {
            try {
                occupation = true;
                processedThread = number;
                try{
                    Thread.currentThread().sleep((long)rv.getRandomValue(minTime,maxTime));
                }
                catch (InterruptedException e){
                    System.err.println("Error int threads");
                }
                occupation = false;
            }
            finally {
                processedThread = 0;
                l.unlock();
            }
        }
        else{
            countCrash[number]++;
            Thread.currentThread().interrupt();
        }
    }
}