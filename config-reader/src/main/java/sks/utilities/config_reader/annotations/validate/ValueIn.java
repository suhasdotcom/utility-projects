package sks.utilities.config_reader.annotations.validate;

import sks.utilities.commons.base.NullEnum;

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

    Class<? extends Enum<?>> enums() default NullEnum.class;

    Class<? extends Enum<?>> excludeEnumValues() default NullEnum.class;
}
