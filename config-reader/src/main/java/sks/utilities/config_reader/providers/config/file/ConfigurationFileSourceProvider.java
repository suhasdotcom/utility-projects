package sks.utilities.config_reader.providers.config.file;

import sks.utilities.config_reader.providers.config.ConfigurationSourceProvider;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public enum ConfigurationFileSourceProvider implements ConfigurationSourceProvider
{
    MAIN_CLASSPATH_LIKE(path -> Pattern.matches("[^~/:][a-zA-Z0-9,-./_]+", path),
            path -> Path.of(getProjectDir(), "src", "main", "resources", path)),

    PROJECT_SOURCE(path -> Pattern.matches(":[a-zA-Z0-9,-./\\\\]+", path),
            path -> Path.of(getProjectDir(), path.replace(":", ""))),

    ABSOLUTE_PATH(path -> Pattern.matches("(([~/])|([a-zA-z]{1}?:))[a-zA-Z0-9,-./\\\\]+", path),
            Path::of);

    private final Function<String, Path> configurationFileSourceFunc;
    private final Predicate<String> validationFunc;

    ConfigurationFileSourceProvider(final Predicate<String> validationFunc, final Function<String, Path> configurationFileSourceFunc) {
        this.validationFunc = validationFunc;
        this.configurationFileSourceFunc = configurationFileSourceFunc;
    }

    public Predicate<String> getValidationFunc() {
        return validationFunc;
    }

    public static String getProjectDir() {
        return System.getProperty("user.dir");
    }

    public Function<String, Path> getConfigurationFileSourceFunc() {
        return configurationFileSourceFunc;
    }

    public Path getConfiguredPath(final String filePath) {
        return getConfigurationFileSourceFunc().apply(filePath);
    }

    public String getCurrentDir() {
        return new File(".").getAbsolutePath();
    }

    @Override
    public String getConfigurationSource() {
        return null;
    }
}