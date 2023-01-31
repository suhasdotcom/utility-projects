package sks.utilities.config_reader;

import sks.utilities.config_reader.providers.config.file.ConfigurationFileSourceProvider;

import java.io.File;
import java.nio.file.Paths;

public class Driver {
    public static void main(String[] args) {
        System.out.println(Paths.get(".").toAbsolutePath().normalize());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(new File(".").getAbsolutePath());
        System.out.println(ConfigurationFileSourceProvider.MAIN_CLASSPATH_LIKE.getCurrentDir());
        ConfigReader.startReading(ConfigClass.class);
        System.out.println("From Driver: " + ConfigClass.retry);
        OtherClass c = new OtherClass();
        c.printIt();
    }
}
