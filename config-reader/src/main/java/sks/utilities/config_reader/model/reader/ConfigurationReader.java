package sks.utilities.config_reader.model.reader;

import java.util.Map;

/**
 * A reader to do the refreshing reading of a {@code ConfigClass}
 */
public interface ConfigurationReader {
    /**
     * @return a {@link String}; every {@link ConfigurationReader reader} is required  to identify itself as part
     * of the refreshing config reader's flow.
     */
    String identifySelf();

    /**
     * @return a {@link Map} of Key-value pairs. Grouped keys are to be reported as g1.g2...key and then the value.
     */
    Map<String, String> getConfigurationContents();
}
