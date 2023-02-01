package sks.utilities.config_reader;

import sks.utilities.config_reader.annotations.config.file.ConfigFilePath;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class ConfigReader
{
    public static void startReading(final Class<?> configDescriptor) {
        for(final Field field: configDescriptor.getDeclaredFields())
            System.out.println(field);

        Arrays.stream(configDescriptor.getDeclaredClasses()).map(Class::getDeclaredFields).flatMap(Stream::of).forEach(System.out::println);
        System.out.println(configDescriptor.getDeclaredAnnotation(ConfigFilePath.class).value());
        try {
            configDescriptor.getDeclaredField("retry").set(null, 1);
            System.out.println("From ConfigReader: " + configDescriptor.getDeclaredField("retry"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void startReading(final Collection<Class<?>> classes) {
        classes.forEach(ConfigReader::startReading);
    }

    public static void startReading(final Class<?> ... classes) {
        for(final Class<?> clazz: classes)
            startReading(clazz);
    }
}
