package sks.utilities.config_reader;

import sks.utilities.config_reader.annotations.config.file.ConfigFilePath;

@ConfigFilePath("relative-path-to-config-file")
public class ConfigClass
{
    public static int retry;            // all entries are public static so that you don't need to create unnecessary objects
    public static double multiple = 1;  // provide a default value
    public static String name;
    public static String clazz;
    public static class Other           // one way to specify property grouping {see Other.name in the config file}
    {
        public static String name;
    }
    public static int otherInnerSize;   // other.inner.size converted to otherInnerSize. Another way to specify grouping

    public static double someBusinessMethod() { // Specify any methods (must be static)
        return retry*multiple;
    }
}
