package sks.utilities.config_reader.annotations.format;

/**
 * Annotation to denote a key-value reader for the supplied source.
 * The key-value are defaulted to have the {@code =} delimiter.
 */
public @interface KeyValueFormat {
    String delimiter() default "=";
}
