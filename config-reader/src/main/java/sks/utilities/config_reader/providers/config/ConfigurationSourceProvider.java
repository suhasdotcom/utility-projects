package sks.utilities.config_reader.providers.config;

/**
 * Provide {@link sks.utilities.config_reader.model.ConfigurationSource ConfiguratonSource} for different kind of configurations
 */
public interface ConfigurationSourceProvider
{
    /**
     *
     * @return Name of the expected configuration source
     */
    String getConfigurationSource();
}
