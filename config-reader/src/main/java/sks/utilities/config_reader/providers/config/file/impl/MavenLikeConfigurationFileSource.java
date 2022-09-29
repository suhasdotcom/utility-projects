package sks.utilities.config_reader.providers.config.file.impl;

import sks.utilities.config_reader.providers.config.file.ConfigurationFileSourceProvider;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class MavenLikeConfigurationFileSource implements ConfigurationFileSourceProvider {

    @Override
    public String getConfiguration() {
        return null;
    }

    @Override
    public Path getConfiguredFilePath(String filePath) {
        return null;
    }

    @Override
    public boolean isCompatibleFilePathPattern(final String filePath) {
        return Pattern.matches("[^~/:][a-zA-Z0-9,-./]+", filePath);
    }
}
