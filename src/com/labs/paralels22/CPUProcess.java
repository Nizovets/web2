package com.labs.paralels22;

public class CPUProcess {

    public static final int minCountProcess = 5;
    public static final int maxCountProcess = 10;

    RandomValue rv = new RandomValue();

    int countProcess(){
        return (int)rv.getRandomValue(minCountProcess, maxCountProcess);
    }
}
