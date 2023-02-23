package sks.utilities.config_reader.annotations.validate;

/**
 * Validator annotation to validate {@link Comparable comparables}.
 * Provide a value for begin, end, difference. If difference is not specified then value is checked
 * to be in {@code [begin, end)}, i.e.. begin included and end excluded
 */
public @interface ValueBetween {
    char[] charsRange() default {};
    int[] intsRange() default {};
    float[] floatsRange() default {};
    double[] doublesRange() default {};
}
