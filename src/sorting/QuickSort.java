package sorting;

import java.util.Arrays;
import java.util.Random;


/**
 * Based on partitioning of the array into two arrays one of which holds values smaller than the specified value, called pivot,
 * and another array holds values greater than that.
 *
 * Quicksort partitions an array and then calls itself recursively twice to sort the two resulting sub-arrays.
 * Efficient for large-sized data sets - average and worst-case complexity are Ο(n log n) and O(n2) respectively.
 */
public class QuickSort {
    private static final Random random = new Random(System.currentTimeMillis());

    private static <Key extends Comparable<Key>>  void shuffle(Key[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n-i);     // between i and n-1
            Key temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    private static int uniform(int n) {
        if (n <= 0) throw new IllegalArgumentException("argument must be positive: " + n);
        return random.nextInt(n);
    }

    public static <Key extends Comparable<Key>>  void sort(Key[] a) {
        // recommended since statistically the chance of getting an array which is already sorted or almost sorted is higher
        /// and the algorithm will not perform well in those cases
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static <Key extends Comparable<Key>>  void sort(Key[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // partition a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi] and return the index j.
    private static <Key extends Comparable<Key>>  int partition(Key[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Key v = a[lo];  // take element at lo as pivot
        while (true) {
            // find item on lo to swap
            while (a[++i].compareTo(v) < 0) {
                if (i == hi) break;
            }
            // find item on hi to swap
            while (v.compareTo(a[--j]) < 0) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }
            // if pointers crossed
            if (i >= j)  break;

            Key swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
        // put partitioning item v at a[j]
        Key swap = a[lo];
        a[lo] = a[j];
        a[j] = swap;

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static void main(String[] args) {
        String[] a = {"a", "h","op", "oep", "pet", "gr", "wqa", "rds", "as", "rfd", "ed"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
