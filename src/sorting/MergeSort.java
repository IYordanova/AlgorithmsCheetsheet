package sorting;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * Based on divide and conquer technique.
 * Worst-case time complexity being Ο(n log n).
 * Merge sort first divides the array into equal halves and then combines them.
 */
public class MergeSort {

    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        Key[] aux = (Key[]) Array.newInstance(Comparable.class, a.length);
        sort(a, aux, 0, a.length - 1);
    }

    private static <Key extends Comparable<Key>>  void sort(Key[] a, Key[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static <Key extends Comparable<Key>>  void merge(Key[] a, Key[] aux, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) {
            System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {         // first pointer passed mid/first half, so take from the second half
                a[k] = aux[j++];
            } else if (j > hi) {   // second pointer passed hi/second half, so take from the first half
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {  // current element in second half smaller than the one in the first, select it
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];    // set and move the first pointer
            }
        }
    }

    public static void main(String[] args) {
        String[] a = {"a", "h","op", "oep", "pet", "gr", "wqa", "rds", "as", "rfd", "ed"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
