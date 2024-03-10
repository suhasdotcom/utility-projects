package sks.utilities.test_prep;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utilities that can be used to debug code output.
 * System property {@code test-prep.debug} can be set to {@code true} to set {@link DebugUtils#debugEnabled} to
 * {@code true}. Thus enabling debugging in tests.
 */
public class DebugUtils {
    private final static boolean debugEnabled = Boolean.getBoolean("test-prep.debug");

    /**
     * Print a two-D matrix on screen.
     *
     * @param theMatrix two-D matrix.
     * @param formatter a {@link String} representation of {@link T}.
     * @param <T>       Type of the matrix.
     */
    public static <T> void printMatrix(final T[][] theMatrix, final Function<T, String> formatter) {
        if(debugEnabled) {
            for (T[] row : theMatrix) {
                for (T col : row)
                    System.out.print(formatter.apply(col) + " ");
                System.out.println();
            }
        }
    }

    /**
     * Print a two-D matrix on screen.
     *
     * @param theMatrix two-D matrix.
     * @param <T>       Type of the matrix.
     */
    public static <T> void printMatrix(final T[][] theMatrix) {
        printMatrix(theMatrix, Objects::toString);
    }

    /**
     * Print a char two-D matrix on screen.
     *
     * @param theMatrix two-D matrix.
     * @param <T>       Type of the matrix.
     */
    public static <T> void printMatrix(final char[][] theMatrix) {
        if(debugEnabled) {
            for (char[] row : theMatrix) {
                for (char col : row)
                    System.out.print(col + " ");
                System.out.println();
            }
        }
    }

    public static <K, V> BiConsumer<K, V> printPair() {
        if(debugEnabled)
            return (k, v) -> System.out.println(k + " -> " + v);
        return (k, v) -> {};
    }

    public static <K, V> void printMap(final Map<K, V> theMap) {
        theMap.forEach(printPair());
    }

    public static <T> void printSet(final Set<T> theSet) {
        if(debugEnabled) {
            String strRepr = theSet.stream().map(String::valueOf)
                    .collect(Collectors.joining(", ", "{", "}"));
            System.out.println(strRepr);
        }
    }

    public static final Consumer<Object> println = debugWrap(System.out::println);

    public static void println(final String s) {
        println.accept(s);
    }

    public static void println() {
        if(debugEnabled) System.out.println();
    }

    private static <T> Consumer<T> debugWrap(Consumer<T> o) {
        if(debugEnabled) return o;
        return (T) -> {};
    }
}
