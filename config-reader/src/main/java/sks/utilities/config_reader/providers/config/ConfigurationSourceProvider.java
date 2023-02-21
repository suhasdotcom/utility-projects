package sks.utilities.config_reader.providers.config;

import sks.utilities.config_reader.model.config_source.ConfigurationSource;

/**
 * Provide {@link ConfigurationSource ConfiguratonSource} for different kind of configurations
 */
public interface ConfigurationSourceProvider
{
    /**
     *
     * @return Name of the expected configuration source
     */
    String getConfigurationSource();
}
