package sks.utilities.config_reader;

import sks.utilities.config_reader.annotations.config.database.ConfigTable;
import sks.utilities.config_reader.annotations.config.file.ConfigFilePath;
import sks.utilities.config_reader.model.reader.file.ConfigurationFileReader;
import sks.utilities.config_reader.model.repo.ConfigReaderRepository;
import sks.utilities.config_reader.providers.config.file.ConfigurationFileSourceProvider;
import sks.utilities.commons.base.dsa.PermissionedMap;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.nio.file.Path;

public class ConfigReader
{
    private static boolean readStarted = false;
    private static ConfigReaderRepository configReaderRepository = ConfigReaderRepository.SINGLETON_DICTIONARY;

    public static void startReading(final Class<?> configDescriptor) {
        Class<ConfigReader> configReaderClass = ConfigReader.class;
        Map<String, String> pMap = new PermissionedMap<>(HashMap::new, ConfigReader.class, OtherClass.class);
        Base b = new Derived();
        int retry = b.inner.retry();
        readStarted = true;
        boolean thisIsAFileBasedConfig = false;

        for(final Field field: configDescriptor.getDeclaredFields())
            System.out.println(field);

        Arrays.stream(configDescriptor.getDeclaredClasses()).map(Class::getDeclaredFields).flatMap(Stream::of).forEach(System.out::println);
        System.out.println(configDescriptor.getDeclaredAnnotation(ConfigFilePath.class).value());

        if(configDescriptor.isAnnotationPresent(ConfigFilePath.class)) {
            thisIsAFileBasedConfig = true;
            handleFileBasedConfig(configDescriptor);
        }
        if(configDescriptor.isAnnotationPresent(ConfigTable.class)) {
            if(thisIsAFileBasedConfig)
                throw new IllegalArgumentException("Cannot have file based config also pick up values from a Db Table");
            handleDbTableBasedConfig(configDescriptor);
        }

        try {
            configDescriptor.getDeclaredField("retry").set(null, 1);
            System.out.println("From ConfigReader: " + configDescriptor.getDeclaredField("retry"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleDbTableBasedConfig(Class<?> configDescriptor) {
    }

    private static void handleFileBasedConfig(Class<?> configDescriptor) {
        final ConfigFilePath theAnnotation = configDescriptor.getDeclaredAnnotation(ConfigFilePath.class);
        final String filePathPattern = theAnnotation.value();
        final ConfigurationFileSourceProvider configurationFileSourceProvider = ConfigurationFileSourceProvider.getConformingFileSource(filePathPattern);
        final Path configFilePath = configurationFileSourceProvider.getConfiguredPath(filePathPattern);

        configReaderRepository.add(configDescriptor.getName(), new ConfigurationFileReader() {
            @Override
            public String identifySelf() {
                return "FileReader";
            }

            @Override
            public Map<String, String> getConfigurationContents() {
                return null;
            }
        });
    }

    public static void startReading(final Collection<Class<?>> classes) {
        classes.forEach(ConfigReader::startReading);
    }

    public static void startReading(final Class<?> ... classes) {
        for(final Class<?> clazz: classes)
            startReading(clazz);
    }

    public static ConfigReaderRepository getConfigReaderRepository() {
        return configReaderRepository;
    }

    public static void setConfigReaderRepository(ConfigReaderRepository configReaderRepository) {
        if(readStarted)
            throw new IllegalStateException("Cannot set the config repository after read is started on the configs");
        ConfigReader.configReaderRepository = configReaderRepository;
    }
}

interface Base {
    interface Inner {
        int retry();
    }
    Inner inner = configurateAndInstantiate();

    static <T> T configurateAndInstantiate() {
        return null;
    }
}

class Derived implements Base {

}