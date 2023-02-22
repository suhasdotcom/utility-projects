package sks.utilities.config_reader.model.repo;

import sks.utilities.config_reader.model.reader.ConfigurationReader;
import sks.utilities.config_reader.model.repo.impl.ConfigReaderSingletonDictionary;

/**
 * Repository interface for storing/supplying the ConfigClass ({@link String key}) and {@link ConfigurationReader} (value)
 */
public interface ConfigReaderRepository {
    /**
     * @see ConfigReaderSingletonDictionary
     */
    ConfigReaderRepository SINGLETON_DICTIONARY = ConfigReaderSingletonDictionary.INSTANCE;

    /**
     * @param fullConfigurationClassName {@link String} key. Suggested to have much entropy and hence has the perfect
     *                                trade-off when it is the full Class name as provided by {@link Class#getName()}
     * @return {@link ConfigurationReader} corresponding to the {@link String} fullClassName key
     */
    ConfigurationReader findByName(final String fullConfigurationClassName);

    /**
     * A convenience method provided to fetch directly from ConfigClass's name.
     * @param configurationClass {@link Class} object whose name (fullName) will be used as the key
     * @return {@link ConfigurationReader} corresponding to the {@link Class} providerClass
     * @implNote if {@link #findByName(String)} is overriden to not store class fullNames then this convenience method
     * must also be overriden to match the requirement
     */
    default ConfigurationReader findByClass(final Class<?> configurationClass) {
        return findByName(configurationClass.getName());
    }

    /**
     * @param fullConfigurationClassName a {@link String} full class name
     * @return {@code true} if a {@link ConfigurationReader Reader} is already assigned to
     * {@link String} fullConfigurationClassName
     */
    boolean isReaderAssignedTo(final String fullConfigurationClassName);

    /**
     * A convenience method provided to fetch whether a ConfigClass is already assigned a {@link ConfigurationReader}
     * @param configurationClass {@link Class} object whose name (fullName) will be used as the key
     * @return {@code true} if a {@link ConfigurationReader Reader} is already assigned to
     * {@link String} fullConfigurationClassName
     * @implNote if {@link #isReaderAssignedTo(String)} is overriden to not store class fullNames
     * then this convenience method must also be overriden to match the requirement
     */
    default boolean isReaderAssignedTo(final Class<?> configurationClass) {
        return isReaderAssignedTo(configurationClass.getName());
    }

    /**
     * Add/assign a {@link ConfigurationReader} to a ConfigClass
     * @param fullConfigurationClassName a {@link String } representing key for the {@link ConfigurationReader}
     *                                   assignment. Suggested to have much entropy and hence has the perfect
     *      *                           trade-off when it is the full Class name as provided by {@link Class#getName()}
     * @param configurationReader a {@link ConfigurationReader}
     */
    void add(final String fullConfigurationClassName, final ConfigurationReader configurationReader);
}
