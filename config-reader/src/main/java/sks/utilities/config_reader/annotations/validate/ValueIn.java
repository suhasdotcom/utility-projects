package sks.utilities.config_reader.annotations.validate;

import sks.utilities.commons.base.NullEnum;

/**
 * Validator annotation for validating a value which must be in the list of supplied values.
 */
public @interface ValueIn {
    short[] shorts() default {};

    byte[] bytes() default {};

    int[] ints() default {};

    long[] longs() default {};

    float[] floats() default {};

    double[] doubles() default {};

    char[] chars() default {};

    boolean[] booleans() default {};

    String[] strings() default {};

    Class<?>[] classes() default {};

    /**
     * Use all the objects of the specified enum unless a list of objects is excluded using {@link ValueIn#excludeEnumValues()}
     */
    Class<? extends Enum<?>> enums() default NullEnum.class;

    /**
     * Exclude enum objects specified
     */
    Class<? extends Enum<?>>[] excludeEnumValues() default {NullEnum.class};
}
