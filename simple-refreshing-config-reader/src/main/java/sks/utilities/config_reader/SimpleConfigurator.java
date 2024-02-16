package sks.utilities.config_reader;

public class SimpleConfigurator implements Configurator {
    @Override
    public <T> T getKey(String key) {
        System.out.println("Get Key called");
        return null;
    }
}
