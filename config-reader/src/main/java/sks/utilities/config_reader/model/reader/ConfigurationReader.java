package sks.utilities.config_reader.model.reader;

/**
 * A reader to do the refreshing reading of a {@code ConfigClass}
 */
public interface ConfigurationReader {
    /**
     * @return a {@link String}; every {@link ConfigurationReader reader} is required  to identify itself as part
     * of the refreshing config reader's flow.
     */
    String identifySelf();
}
