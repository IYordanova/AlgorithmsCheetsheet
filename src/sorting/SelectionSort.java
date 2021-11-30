package sorting;

import java.util.Arrays;

/**
 * The list is divided into two parts - sorted part at the left end and  unsorted part at the right end.
 * Initially, the sorted part is empty and the unsorted part is the entire list.
 * The smallest element is selected from the unsorted array and swapped with the leftmost element, becomes a part of the sorted array.
 * This process continues moving unsorted array boundary by one element to the right.
 * Not suitable for large data sets - average and worst case complexities are of Ο(n^2).
 */
public class SelectionSort {

    public static <Key extends Comparable<Key>>  void sort(Key[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].compareTo( a[min]) < 0) {
                    min = j;
                }
            }
            Key swap = a[i];
            a[i] = a[min];
            a[min] = swap;
        }
    }

    public static void main(String[] args) {
        String[] a = {"a", "h", "op", "oep", "pet", "gr", "wqa", "rds", "as", "rfd", "ed"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
