package sks.utilities.config_reader.annotations.config.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is to be used on the class which will behave as the config database table's/view's value taker in java.
 * The {@link ConfigTable#value tableName} is the config database's table/view name
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigTable {
    /**
     * The config table/view's name
     */
    String value();
}
