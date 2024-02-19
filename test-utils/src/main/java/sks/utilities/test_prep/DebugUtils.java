package sks.utilities.test_prep;

import java.util.Objects;
import java.util.function.Function;

/**
 * Utilities that can be used to debug code output.
 */
public class DebugUtils {
    /**
     * Print a two-D matrix on screen.
     *
     * @param theMatrix two-D matrix.
     * @param formatter a {@link String} representation of {@link T}.
     * @param <T>       Type of the matrix.
     */
    public static <T> void printMatrix(final T[][] theMatrix, final Function<T, String> formatter) {
        for (T[] row : theMatrix) {
            for (T col : row)
                System.out.print(formatter.apply(col) + " ");
            System.out.println();
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
        for (char[] row : theMatrix) {
            for (char col : row)
                System.out.print(col + " ");
            System.out.println();
        }
    }
}
