package quicksort;

/**
 *
 * @author henry
 */
public class Quicksort {

    
    /**
     * @param args T[] table is an array of things
     */
    public static <T extends Comparable<T>> void sort(T[] table){
        quickSort(table, 0, table.length-1);
    }
    
    public static <T extends Comparable<T>> void quickSort(T[] table, int first, int last){
        if(first<last){
            int pivIndex = partition(table, first, last);
            quickSort(table, first, pivIndex - 1);
            quickSort(table, pivIndex + 1, last);
        }
    }
    
    public static <T extends Comparable<T>> int partition(T[] table, int first, int last){
        T pivot = table[first];
        int up = first;
        int down = last;
        do{
            while((up < last) && (pivot.compareTo(table[up]) >= 0)){
                up++;
            }
            while(pivot.compareTo(table[down]) < 0){
                down--;
            }
            if (up < down){
                swap(table, up, down);
            }
        } while (up < down);
        swap(table, first, down);
        return down;
    }
    
    private static <T extends Comparable<T>> void swap(T[] table, int first, int second){
        T temp;
        temp = table[first];
        table[first] = table[second];
        table[second] = temp;
    }
}
