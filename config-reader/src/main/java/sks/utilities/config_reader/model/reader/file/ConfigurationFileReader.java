package sks.utilities.config_reader.model.reader.file;

import sks.utilities.config_reader.model.reader.ConfigurationReader;

/**
 * Specialized {@link ConfigurationReader reader} for refreshingly reading file.
 *
 * <pre>
 * String s = "null";
 * </pre>
 * {@snippet :
 *  String s = "null";
 * }
 */
public interface ConfigurationFileReader extends ConfigurationReader {
    @Override
    default String identifySelf() {
        return "FileReader";
    }
}
