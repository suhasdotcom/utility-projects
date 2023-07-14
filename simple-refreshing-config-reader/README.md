# (Refreshing) Config Reader

## Motivation:
A configuration once read can only be changed after the configured program is restarted/rerun.
This has caused problems such as hot-fixes which don't even need hot-fixing in code but was only required because a configuration change was needed.

## Solution:
A config-reader is required which'll poll config file in a lightweight thread and update all the configured values so that hot-fixing just for a configuration change shouldn't be required.

## Include in your project:
To install in your project just add the following in POM:
```xml
<dependency>
    <groupId>sks.utilities</groupId>
    <artifactId>simple-refreshing-config-reader</artifactId>
</dependency>
```

## Configuring class file:
configure the properties file:
```properties
retry=1
multiple=2.5
name="Suhas Srivastava"
clazz=first
Other.name=Suyog Srivastava
```

configure class with class name as Config* as such:

```java
import org.apache.commons.configuration2.FileBasedConfiguration;

public class Config extends ConfigurationClass {
    public Config() {
        super(new FileBasedConfiguration());
    }

    public int retry = getInt("retry");
    public double multiple = getDouble("multiple");
    public String name = getString("name", "Suhas");
    public String clazz;

    public static class Other {
        public static String name;
    }
}
```

use the configurations in code as:
```java
import {your.package.structure}.Config
...

int retryCount = Config.retry;
String otherName = Config.Other.name;
```

in main:
```java
import {your.package.structure}.Config;
import sks.utilities.config_reader.ConfigReader;

public class SampleDriver {
    public static void main(String[] args) {
        ConfigReader.startReading(Config);
        ...
    }
}
```

of course this kind of configuration is limited as you cannot name your config file's keys as per any java keyword.