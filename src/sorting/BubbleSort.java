package sorting;

import java.util.Arrays;

/**
 * Each pair of adjacent elements is compared and the elements are swapped if they are not in order.
 * Not suitable for large data sets - average and worst case complexity are of Ο(n^2).
 */
public class BubbleSort {

    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int exchanges = 0;
            for (int j = n - 1; j > i; j--) {
                if (a[j].compareTo(a[j - 1]) < 0) {
                    Key swap = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = swap;
                    exchanges++;
                }
            }
            if (exchanges == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String[] a = {"a", "h", "op", "oep", "pet", "gr", "wqa", "rds", "as", "rfd", "ed"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
