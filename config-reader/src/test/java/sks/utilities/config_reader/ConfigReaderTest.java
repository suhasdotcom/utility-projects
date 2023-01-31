package sks.utilities.config_reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sks.utilities.config_reader.annotations.ConfigFilePath;
import sks.utilities.config_reader.config.ConfigClass;
import sks.utilities.config_reader.providers.config.file.ConfigurationFileSourceProvider;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ConfigReader should")
class ConfigReaderTest
{
    @Nested
    @DisplayName("Get correct config file")
    class GetCorrectConfigFile
    {

        @BeforeEach
        public void setUp() {
            ConfigReader.startReading(ConfigClass.class);
        }

        @Test
        @DisplayName("path taken up for config file should be correct")
        void testPathPickedUpForConfigFile() {
            String filePath = ConfigClass.class.getDeclaredAnnotation(ConfigFilePath.class).value();
            assertEquals(filePath, ":test/resources/app-config.cfg");
            assertTrue(ConfigurationFileSourceProvider.PROJECT_SOURCE.getValidationFunc().test(filePath));
            assertEquals(ConfigurationFileSourceProvider.PROJECT_SOURCE.getConfiguredPath(filePath),
                    Paths.get(ConfigurationFileSourceProvider.getProjectDir(), "test", "resources", "app-config.cfg"));
        }

        @Test
        @DisplayName("file is read as wanted")
        void testFileReadCorrectly() {
            final String filePath = ConfigClass.class.getDeclaredAnnotation(ConfigFilePath.class).value();

        }
    }
}