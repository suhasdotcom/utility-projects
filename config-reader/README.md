# (Refreshing) Config Reader

## Motivation:
A configuration once read can only be changed after the configured program is restarted/rerun.
This has caused problems such as hot-fixes which don't even need hot-fixing in code but was only required because a configuration change was needed.

## Solution:
A config-reader is required which'll poll config file in a lightweight daemon thread and update all the configured values so that hot-fixing just for a configuration change shouldn't be necessary.

## Include in your project:
To install in your project just add the following in POM:
```xml
<dependency>
    <groupId>sks.utilities</groupId>
    <artifactId>config-reader</artifactId>
</dependency>
```

## Configuring class file:
configure the json, yaml, xml, properties file (illustration):
```properties
retry=1
multiple=2.5
name="Suhas Srivastava"
clazz=first
Other.name=Suyog Srivastava
other.inner.size = 1
```

configure configuration class:
```java
@ConfigFilePath("relative-path-to-config-file")
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
```

use the configurations in code as:
```java
import {your-package-name}.ConfigClass;

int retryCount = ConfigClass.retry;
String otherName = ConfigClass.Other.name;
```

or an interface can also be used and wired. 
Interface format:

```java
import sks.utilities.config_reader.annotations.ConfigFilePath;

@ConfigFilePath("relative-path-to-config-file")
public interface ConfigInterface
{
    int retry();
    int multiple();
    String name();
    String clazz();
    String otherName(); // other.name converted to otherName
}
```
in main:
```java
import sks.utilities.config_reader.ConfigReader;

public class SampleDriver {
    @WiredConfig
    ConfigInterface configInterface;
    
    public static void main(String[] args) {
        ConfigReader.startReading(ConfigInterface.class);
        int retries = configInterface.retry();
        // ... remainder
    }
}
```

of course this kind of configuration is limited as you cannot name your config file's keys as per any java keywords.

## Providing configuration file's path to your class/interface

```java
import sks.utilities.config_reader.annotations.ConfigFilePath;

@ConfigFilePath('app-config.cfg')               // assumes config file is in mvn like path of src/main/resources or src/test/resources for tests
public class ConfigClassExampleOne {}

@ConfigFilePath('dir/app-config.properties')    // assumes config file is in mvn like path having one more internal directory (dir) src/main/resources/dir/ or src/test/resources/dir/ for tests
public class ConfigClassExampleTwo {}

@ConfigFilePath(':src/main/resources/app-config.json')    // assumes config from project's root, so for a project named `config-reader` this annotated path is config-reader/src/main/resources/app-config.json
public class ConfigClassExampleThree {}

@ConfigFilePath('~/Applications/Config-Reader/src/main/resources/app-config.json')    // assumes config file from absolute path, i.e. any path starting from [A-Z]:/ (windows drives paths like C:/, D:\) or [~, /] (unix home and root paths) will be considered absolute paths
public interface ConfigInterfaceExampleOne {}
```

Oncoming: 
1) Authorize the config modifier person/token before config change (currently this can be done by unix user groups).
2) Maintain a history of config change.