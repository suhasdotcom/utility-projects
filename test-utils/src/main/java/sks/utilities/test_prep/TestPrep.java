package sks.utilities.test_prep;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Use the utility class for easy testing.
 */
public class TestPrep {
    /**
     * Always statically initialise testDirectory or any other {@link TestPrep} resource because its
     * methods need to be called statically in JUnit's {@link MethodSource}. The same can be done in a static block.
     */
    public static Path testDirectory;

    /**
     * Get JSON data from a file.
     * @param filePath filePath
     * @param jsonClass Class this File should conform to.
     * @return Instance of {@link T} initialised from filePath.
     * @param <T> Type this jsonFile should conform to.
     */
    public static <T> T jsonFileToData(final String filePath, final Class<T> jsonClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = new FileInputStream(filePath)) {
            return mapper.readValue(is, jsonClass);
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
     * List all the *.json files in the {@link TestPrep#testDirectory} directory.
     * @return String path to all json files in {@link #testDirectory}.
     */
    public static List<String> jsonFilesInDir() throws IOException {
        try (Stream<Path> theStream = Files.list(testDirectory)) {
            return theStream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                    .map(Path::toString)
                    .toList();
        }
    }
}
