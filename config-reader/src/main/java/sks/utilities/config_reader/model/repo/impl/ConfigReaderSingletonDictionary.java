package sks.utilities.config_reader.model.repo.impl;

import sks.utilities.config_reader.exceptions.readers.config.file.DuplicateConfigurationReaderException;
import sks.utilities.config_reader.model.reader.ConfigurationReader;
import sks.utilities.config_reader.model.repo.ConfigReaderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Single machine implementation for {@link ConfigReaderRepository}.
 * <a href="https://en.wikipedia.org/wiki/Singleton_pattern">Singleton</a> by nature. Uses a {@link ConcurrentHashMap}
 * internally to store the class and {@link ConfigurationReader reader} association. Duplicate keys and inserting
 * duplicate keys is not allowed in this repo. {@link DuplicateConfigurationReaderException} is thrown on encounter.
 *
 * @see DuplicateConfigurationReaderException
 */
public enum ConfigReaderSingletonDictionary implements ConfigReaderRepository {
    /**
     * The <a href="https://en.wikipedia.org/wiki/Singleton_pattern">Singleton</a> instance for a single machine.
     */
    INSTANCE;

    private final Map<String, ConfigurationReader> configurationReaderMap = new ConcurrentHashMap<>();

    /**
     * Delegates to {@link HashMap#get(Object)}
     * @param fullClassName {@link String} key. Suggested to have much entropy and hence has the perfect
     *                                trade-off when it is the full Class name as provided by {@link Class#getName()}
     * @return {@link ConfigurationReader} assigned to this {@code param: fullClassName} class
     */
    @Override
    public ConfigurationReader findByName(String fullClassName) {
        return configurationReaderMap.get(fullClassName);
    }

    /**
     * Delegates to {@link HashMap#containsKey(Object)}
     * @param fullConfigurationClassName a {@link String} full class name
     * {@code true} if a {@link ConfigurationReader Reader} is already assigned to
     * {@link String} fullConfigurationClassName
     */
    @Override
    public boolean isReaderAssignedTo(String fullConfigurationClassName) {
        return configurationReaderMap.containsKey(fullConfigurationClassName);
    }

    /**
     *
     * @param fullClassName a {@link String } representing key for the {@link ConfigurationReader}
     *                      assignment. Suggested to have much entropy and hence has the perfect
     *                      trade-off when it is the full Class name as provided by {@link Class#getName()}
     * @param configurationReader a {@link ConfigurationReader}
     * @throws DuplicateConfigurationReaderException if class already has a reader assigned
     */
    @Override
    public void add(final String fullClassName, final ConfigurationReader configurationReader) throws DuplicateConfigurationReaderException {
        if(configurationReaderMap.containsKey(fullClassName))
            throw new DuplicateConfigurationReaderException(fullClassName, configurationReader);

        configurationReaderMap.put(fullClassName, configurationReader);
    }
}
