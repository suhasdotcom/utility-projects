# Command Line Options Reader:

## Features:
Read command line arguments in as you like, i.e..
1) Unix type command-line-args `--first 1 --second=2 -t --retry -n` (default provided)
2) Read semi XML type options `<first> 1 <second>=2 <third>` (plugin can be added later)
3) Read full XML type options `<first>1</first> <third />` (plugin can be added later)

## Include in your project:
To install in your project just add the following in POM:
```xml
<dependency>
    <groupId>sks.utilities</groupId>
    <artifactId>command-line-options-reader</artifactId>
</dependency>
```

## Initialize command line options:

```java
import sks.utilities.command_line_options_reader.CommandLineOptionsReader;
import sks.utilities.command_line_options_reader.base.Option;

public class CommandLineOptionsReaderExample {

    public static void main(String[] args) {
        CommandLineOptionsReader reader = new CommandLineOptionsReader.CommandLineOptionsReaderBuilder()
                .addOption("first").isValued().isRequired()
                .addOption("second").mustOnlyContainValues("1", "2", "3").withSingleLetterOption("s")
                .build();
        
        Map <String, Option> parsedOptions = this.reader.getCommandLineParser().parse(String.join(" ", args));
    }
}
```

## Help during run:
```shell
java {YourApplicationName} --help
```