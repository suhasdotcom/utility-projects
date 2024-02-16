package sks.utilities.config_reader;

public class NullConfigurator implements Configurator {
    @Override
    public <T> T getKey(String key) {
        return null;
    }
}
