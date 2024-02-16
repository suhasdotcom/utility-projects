package sks.utilities.config_reader;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Driver {
    public static void main(String[] args) throws ConfigurationException {
        Configurations configurations = new Configurations();
        JSONConfiguration cfg = configurations.fileBased(JSONConfiguration.class, "");
        Configuration configuration;
        ConfigurationClass configurationClass = new ConfigurationClass(new NullConfigurator());
    }
}
