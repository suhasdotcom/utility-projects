package sks.utilities.config_reader.model.config_source.file;

import sks.utilities.config_reader.model.config_source.ConfigurationSource;

import java.nio.file.Path;

public interface ConfigurationFileSource extends ConfigurationSource {
    Path getFilePath();
}
