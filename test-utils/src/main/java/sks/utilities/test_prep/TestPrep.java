package sks.utilities.test_prep;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Use the utility class for easy testing.
 */
public class TestPrep {
    public static <T> T jsoFileToData(final String filePath, final Class<T> jsonClass) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new FileInputStream(filePath)) {
            return mapper.readValue(is, jsonClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Print a two-D matrix on screen.
     * @param theMatrix two-D matrix.
     * @param <T> Type of the matrix.
     */
    public static <T> void printMatrix(final T[][] theMatrix) {
        for (T[] row : theMatrix) {
            for (T col : row)
                System.out.print(col + " ");
            System.out.println();
        }
    }

    /**
     * Returns a {@link List<T>} because we may want to supply duplicate data in some files for load testing, maybe.
     * @param testDir the test directory where all the JSON files are stored for testing.
     * @param tClass Class to conform JSON into.
     * @return {@link List<T>} from reading all the JSON files in the given testDir.
     * @param <T> Type of data to conform to provided JSON files.
     */
    public static <T> List<T> jsonTestData(final Path testDir, final Class<T> tClass) throws IOException {
        try (Stream<Path> theStream = Files.list(testDir)) {
            return theStream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                    .map(Path::toAbsolutePath)
                    .map(Path::toString)
                    .map(fileToDataFunc(tClass))
                    .toList();
        }
    }

    private static <T> Function<String, T> fileToDataFunc(final Class<T> s) {
        return t -> jsoFileToData(t, s);
    }
}
