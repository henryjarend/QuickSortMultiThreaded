/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

/**
 *
 * @author henry
 */
public class QuickSortThread <T extends Comparable<T>> extends Thread {
    Thread firstThread = new Thread();
    int first;
    int last;
    T[] table;
    
    public QuickSortThread(T[] table, int first, int last){
        this.table = table;
        this.first = first;
        this.last = last;
    }
    @Override
    public void run(){
        Quicksort.quickSort(table, first, last);
    }
    
    
}
