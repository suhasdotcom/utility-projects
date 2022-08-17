package sks.utilities.config_reader;

import java.lang.reflect.Field;

public class ConfigReader
{
    static void startReading(final Class<?> configDescriptor){
        for(Field field: configDescriptor.getDeclaredFields())
            System.out.println(field);
    }
}
