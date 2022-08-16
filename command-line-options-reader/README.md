# Command Line Options Reader:

## Features:
Read command line arguments in as you like, i.e..
1) Unix type command-line-args `--first 1 --second=2 -t --retry -n` (default provided)
2) Read semi XML type options `<first> 1 <second>=2 <third>` (plugin can be added later)
3) Read full XML type options `<first>1</first> <third />` (plugin can be added later)

## Include in your project:
To install in your project just add the following in POM:
```
<dependency>
    <groupId>sks.utilities</groupId>
    <artifactId>command-line-options-reader</artifactId>
</dependency>
```

## Initialize command line options:
```
this.reader = new CommandLineOptionsReader.CommandLineOptionsReaderBuilder()
                .addOption("first").isValued().isRequired()
                .addOption("second").mustOnlyContainValues("1", "2", "3").withSingleLetterOption("s")
                    .build();
                    
thid.reader.getCommandLineParser().parse(String.join(" ", args);
```

## Help during run:
```
java {YourApplicationName} --help
```