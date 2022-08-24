package sks.utilities.config_reader.config;

import sks.utilities.config_reader.annotations.ConfigFilePath;

@ConfigFilePath("app-config.cfg")
public class ConfigClass
{
    public static int retry;            // all entries are public static so that you don't need to create unnecessary objects
    public static double multiple;
    public static String name;
    public static String clazz;
    public static class Other           // one way to specify property grouping {see Other.name in the config file}
    {
        public static String name;
    }
    public static int otherInnerSize;   // other.inner.size converted to otherInnerSize. Another way to specify grouping
}
