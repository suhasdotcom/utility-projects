# (Refreshing) Config Reader

## Motivation:
A configuration once read can only be changed after the configured program is restarted/rerun.
This has caused problems such as hot-fixes which don't even need hot-fixing in code but was only required because a configuration change was needed.

## Solution:
A config-reader is required which'll poll config file in a lightweight thread and update all the configured values so that hot-fixing just for a configuration change shouldn't be required.

## Include in your project:
To install in your project just add the following in POM:
```
<dependency>
    <groupId>sks.utilities</groupId>
    <artifactId>config-reader</artifactId>
</dependency>
```

