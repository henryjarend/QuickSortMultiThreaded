/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class Driver {

    static final int SEEDNO = 123456789;
    static final int N = 1000;
    static final long toRun = 1000;
    long avgThreadTime;
    long avgUnthreadTime;
    static long noOfTimesRun;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer[] toSort;
        
        long unthreadedStart;
        long unthreadedFinish;
        
        long threadedStart;
        long threadedFinish;
        
        long totalThreadTime = 0;
        long totalUnthreadTime = 0;
        
        while(noOfTimesRun != toRun){
            toSort = makeArray(N);
            unthreadedStart = System.nanoTime();
            Quicksort.sort(toSort);
            unthreadedFinish = System.nanoTime();
            long unthreadedTimeToComplete = unthreadedFinish-unthreadedStart;
            //System.out.println("Unthreaded time for 100: " + unthreadedTimeToComplete);
            
            totalUnthreadTime = totalUnthreadTime + unthreadedTimeToComplete;
            
            toSort = makeArray(N);
            threadedStart = System.nanoTime();
            int partInd = Quicksort.partition(toSort, 0, toSort.length-1);
            QuickSortThread firstThread = new QuickSortThread(toSort,0, partInd-1);
            QuickSortThread secondThread = new QuickSortThread(toSort, partInd+1, toSort.length-1);
            firstThread.start();
            secondThread.start();
            try {
                firstThread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                secondThread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
            }
            threadedFinish = System.nanoTime();
            long threadedTimeToComplete = threadedFinish - threadedStart;
            
            noOfTimesRun++;
            
            totalThreadTime = totalThreadTime + threadedTimeToComplete;
            
        }
        
        long avgThreadTime = totalThreadTime/toRun;
        long avgUnthreadTime = totalUnthreadTime/toRun;
        
        System.out.println("Total Threaded time:" + avgThreadTime);
        System.out.println("Total Unthreaded Time: " + avgUnthreadTime);
    }
    
    public static Integer[] makeArray(int arraySize){
        Integer[] toFill = new Integer[arraySize];
        Random rand = new Random(SEEDNO);
        for(int i = 0; i <toFill.length; i++){
            toFill[i] = rand.nextInt(200);
        }
        return toFill;
    }

    private static void printArray(Integer[] toPrint) {
        for(int i = 0; i < toPrint.length; i++){
            System.out.println(toPrint[i] + " at spot " + (i+1));
        }
        System.out.println();
    }
}
