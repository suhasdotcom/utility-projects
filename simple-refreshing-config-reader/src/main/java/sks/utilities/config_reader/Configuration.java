package sks.utilities.config_reader;

import java.util.function.Supplier;

public interface Configuration {
    Configurator getConfigurator();
    void setConfigurator(Configurator  configurator);
}
