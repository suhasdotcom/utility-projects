package sks.utilities.config_reader.annotations.config.file;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is to be used on the class which will behave as the config file's value taker in java.
 * The {@link ConfigFilePath#value filePath} is the config file's path relative to src/main/resources or src/test/resources
 * in case this is not the file hierarchy, then config file's path from project's root can be given as "/project-dir/sub-dir/../sub-dir/config-file-name.{cfg/properties/json/yaml} etc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigFilePath {
    /**
     * The config file's path relative to src/main/resources or src/test/resources
     * in case this is not the file hierarchy, then config file's path from project's root can be given as "/project-dir/sub-dir/../sub-dir/config-file-name.{cfg/properties/json/yaml} etc
     */
    String value();
}
