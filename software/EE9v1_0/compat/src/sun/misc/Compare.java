package sun.misc;

/** Shim for the sun.misc.Compare interface removed from modern JDKs. Needed by modiaf.jar (INGENIAS). */
public interface Compare {
    int doCompare(Object o1, Object o2);
}
