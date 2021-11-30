package sorting;

import java.util.Arrays;


/**
 * Sub-list is maintained which is always sorted, for example, the lower part of an array.
 * The array is searched sequentially and unsorted items are moved to the sorted sub-array.
 * Not suitable for large data sets - average and worst case complexity are of Ο(n^2).
 */
public class InsertionSort {

    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j].compareTo(a[j - 1]) < 0; j--) {
                Key swap = a[j];
                a[j] = a[j-1];
                a[j-1] = swap;
            }
        }
    }

    public static void main(String[] args) {
        String[] a = {"a", "h","op", "oep", "pet", "gr", "wqa", "rds", "as", "rfd", "ed"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
