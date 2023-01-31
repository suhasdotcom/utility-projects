package sks.utilities.config_reader.model.file;

import sks.utilities.config_reader.model.ConfigurationSource;

import java.nio.file.Path;

public interface ConfigurationFileSource extends ConfigurationSource {
    Path getFilePath();
}
