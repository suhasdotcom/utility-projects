package sks.utilities.config_reader;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class ConfigReader
{
    public static void startReading(final Class<?> configDescriptor) {
        for(Field field: configDescriptor.getDeclaredFields())
            System.out.println(field);

        Arrays.stream(configDescriptor.getDeclaredClasses()).map(Class::getDeclaredFields).flatMap(Stream::of).forEach(System.out::println);
    }

    public static void startReading(final Collection<Class<?>> classes) {
        classes.forEach(ConfigReader::startReading);
    }

    public static void startReading(final Class<?> ... classes) {
        for(final Class<?> clazz: classes)
            startReading(clazz);
    }
}
