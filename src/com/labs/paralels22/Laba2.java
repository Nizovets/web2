package com.labs.paralels22;

import java.io.PrintStream;
import java.util.*;

class ModelingThreads extends Thread{

    public int numberThread;
    public int countProcess = 0;
    public static final int minTime = 300;
    public static final int maxTime = 700;
    public CPU[] processor;
    public int[] crashed;
    RandomValue rv = new RandomValue();

    List<Integer> queue = new ArrayList<Integer>();;

    public ModelingThreads(int n, CPU[] p, int[] crash){

        numberThread = n;
        processor = p;
        crashed = crash;
    }

    @Override
    public void run(){

        System.out.println("Create new tread number " + numberThread);

        do {
            try {

                boolean added = false;

                added = true;
                countProcess += 1;
                queue.add(queue.size());

                ModelingProcess process = new ModelingProcess(processor,numberThread,crashed);

                if (processor[0].processedThread == numberThread || processor[0].processedThread == 0) {

                    process.start();

                        if (added) {
                            queue.remove(queue.size() - 1);
                        }

                }

                Thread.currentThread().sleep((long) rv.getRandomValue(minTime, maxTime));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }while(queue.size() != 0);

        System.out.println("Count process in " + numberThread + " thread = " + countProcess);
    }
}

class ModelingProcess extends Thread{

    public CPU[] processor;
    public int number;
    public int[] countCrash;

    ModelingProcess(CPU[] p, int nt, int[] cr){
        processor = p;
        number = nt;
        countCrash = cr;
    }

    @Override
    public void run(){

        if(processor[0].getOccupation() == false) {

            processor[0].processing(number,countCrash);
        } else {
            Thread.currentThread().interrupt();
        }
    }

}

public class Laba2 {

    public static final int threadCount = 2;
    public static final int numberCPU = 1;

    public static void main(String[] args) throws Exception {

        int[] crashedThreads = new int[threadCount];
//        System.setOut(new PrintStream("output.txt"));
        CPU[] processor = new CPU[numberCPU];
        for(int i =0; i< numberCPU; i++){
            processor[i] = new CPU();
            processor[i].setNumber(i);
        }

        ModelingThreads[] threads = new ModelingThreads[threadCount];

        for(int i =0; i< threadCount; i++) {

            threads[i] = new ModelingThreads(i, processor,crashedThreads);
            threads[i].start();
        }

        for( int i=0; i<threadCount; i++) {
            threads[i].join();
        }

        for( int i=0; i<threadCount; i++) {

            System.out.println("Count process in " + i + " thread = " + threads[i].countProcess);
//            System.out.println("Crashed process in " + i + " thread = " + crashedThreads[i]);
//            System.out.printf("Percent crashed process in " + i + " thread = %.3f", percent[i]);
//            System.out.print("%");
//            System.out.println("\n");
        }

    }
}
