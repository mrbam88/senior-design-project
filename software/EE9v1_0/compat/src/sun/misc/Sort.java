package sun.misc;

import java.util.Arrays;

/** Shim for the sun.misc.Sort class removed from modern JDKs. Needed by modiaf.jar (INGENIAS). */
public class Sort {
    public static void quicksort(Object[] arr, Compare c) {
        Arrays.sort(arr, c::doCompare);
    }

    public static void quicksort(Object[] arr, int lo, int hi, Compare c) {
        Arrays.sort(arr, lo, hi + 1, c::doCompare);
    }
}
