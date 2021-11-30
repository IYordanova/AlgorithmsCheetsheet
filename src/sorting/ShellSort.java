package sorting;

import java.util.Arrays;


/**
 * Based on insertion sort algorithm with optimization to avoid large shifts.
 * Insertion sort is used on widely spread elements, first to sort them and then sort the less widely spaced elements.
 * This spacing is termed as interval, it's calculated based on Knuth's formula as  h = h * 3 + 1, where h's initial value is 1
 * Efficient for medium-sized data sets - average and worst-case complexity depend on the gap sequence the best Ο(n)
 */
public class ShellSort {

    public static <Key extends Comparable<Key>>  void sort(Key[] a) {
        int n = a.length;

        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1; // calculate interval using Knuth's formula
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    Key swap = a[j];
                    a[j] = a[j - h];
                    a[j - h] = swap;
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        String[] a = {"a", "h","op", "oep", "pet", "gr", "wqa", "rds", "as", "rfd", "ed"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }


}
