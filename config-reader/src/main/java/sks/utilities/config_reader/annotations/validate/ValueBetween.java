package sks.utilities.config_reader.annotations.validate;

public @interface ValueBetween {
    char[] charsRange() default {};
    int[] intsRange() default {};
    float[] floatsRange() default {};
    double[] doublesRange() default {};
}
