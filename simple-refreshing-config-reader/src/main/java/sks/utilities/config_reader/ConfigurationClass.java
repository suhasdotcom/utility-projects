package sks.utilities.config_reader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationClass implements Configuration {

    private Configurator configurator;

    public ConfigurationClass() {
        this(new SimpleConfigurator());
    }

    public ConfigurationClass(Configurator configurator) {
        this.configurator = configurator;
    }

    private final int retry = configurator.getInt("retry");

    @Override
    public Configurator getConfigurator() {
        return configurator;
    }

    @Override
    public void setConfigurator(Configurator configurator) {
        List<String> l;
        Collectors.toList();
        this.configurator = configurator;
    }
}
