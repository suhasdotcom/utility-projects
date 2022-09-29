package sks.utilities.config_reader.providers.config.file.impl;

import sks.utilities.config_reader.providers.config.file.ConfigurationFileSourceProvider;

import java.nio.file.Path;

public class AbsolutePathConfigurationFileSource implements ConfigurationFileSourceProvider {
    @Override
    public String getConfiguration() {
        return null;
    }

    @Override
    public Path getConfiguredFilePath(String filePath) {
        return null;
    }

    @Override
    public boolean isCompatibleFilePathPattern(String filePath) {
        return false;
    }
}
