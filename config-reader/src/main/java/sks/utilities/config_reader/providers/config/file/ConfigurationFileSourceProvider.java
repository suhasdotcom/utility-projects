package sks.utilities.config_reader.providers.config.file;

import sks.utilities.config_reader.providers.config.ConfigurationSourceProvider;

import java.nio.file.Path;

public interface ConfigurationFileSourceProvider extends ConfigurationSourceProvider {
    Path getConfiguredFilePath(final String filePath);
    boolean isCompatibleFilePathPattern(final String filePath);
}
