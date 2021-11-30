package sorting;

import java.util.Arrays;

public class HeapSort {

    public static <Key extends Comparable<Key>> void sort(Key[] pq) {
        int n = pq.length;

        // heap-ify phase
        for (int k = n / 2; k >= 1; k--)
            sink(pq, k, n);

        // sort-down phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    private static<Key extends Comparable<Key>>  void sink(Key[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static <Key extends Comparable<Key>> boolean less(Key[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static <Key extends Comparable<Key>> void exch(Key[] pq, int i, int j) {
        Key swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    public static void main(String[] args) {
        String[] a = {"a", "h","op", "oep", "pet", "gr", "wqa", "rds", "as", "rfd", "ed"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
