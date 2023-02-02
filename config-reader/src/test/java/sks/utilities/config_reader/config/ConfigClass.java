package sks.utilities.config_reader.config;

import sks.utilities.config_reader.annotations.config.file.ConfigFilePath;

@ConfigFilePath(":test/resources/app-config.cfg")
public class ConfigClass
{
    public static volatile int retry;            // all entries are public static so that you don't need to create unnecessary objects
    public static volatile double multiple;
    public static volatile String name;
    public static volatile String clazz;
    public static class Other           // one way to specify property grouping {see Other.name in the config file}
    {
        public static volatile String name;
    }
    public static volatile int otherInnerSize;   // other.inner.size converted to otherInnerSize. Another way to specify grouping

    public static double retries() {
        return retry*multiple;
    }
}
