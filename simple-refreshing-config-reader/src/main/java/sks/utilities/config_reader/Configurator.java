package sks.utilities.config_reader;

import org.apache.commons.configuration2.Configuration;

import java.util.function.Predicate;

public interface Configurator /*extends Configuration*/ {
    int getInt(String key, Predicate<Integer> validator);
    int getInt(String key, int defaultValue, Predicate<Integer> validator);
}
