package sks.utilities.config_reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import sks.utilities.config_reader.annotations.config.file.ConfigFilePath;
import sks.utilities.config_reader.config.FileConfigClass;
import sks.utilities.config_reader.model.reader.ConfigurationReader;
import sks.utilities.config_reader.model.reader.file.ConfigurationFileReader;
import sks.utilities.config_reader.model.repo.ConfigReaderRepository;
import sks.utilities.config_reader.providers.config.file.ConfigurationFileSourceProvider;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConfigReader should")
class ConfigReaderTest
{
    @Nested
    @DisplayName("Get correct config file")
    class GetCorrectConfigFile
    {
        private String filePath;

        @BeforeEach
        public void setUp() {
            ConfigReader.startReading(FileConfigClass.class);
            filePath = FileConfigClass.class.getDeclaredAnnotation(ConfigFilePath.class).value();
        }

        @Test
        @DisplayName("path taken up for config file should be correct")
        public void testPathPickedUpForConfigFile() {
            assertEquals(filePath, ":test/resources/app-config.cfg");
            assertTrue(ConfigurationFileSourceProvider.PROJECT_SOURCE.getValidationFunc().test(filePath));
            assertEquals(ConfigurationFileSourceProvider.PROJECT_SOURCE.getConfiguredPath(filePath),
                    Paths.get(ConfigurationFileSourceProvider.getProjectDir(), "test", "resources", "app-config.cfg"));
        }

        @Test
        @DisplayName("file is read as wanted")
        public void testFileReadCorrectly() {

        }

        @Test
        @DisplayName("correct reader should get assigned")
        public void testCorrectReaderAssignment() {
            final ConfigurationReader configurationReader = ConfigReaderRepository.SINGLETON_DICTIONARY.findByClass(FileConfigClass.class);
            assertTrue(ConfigReaderRepository.SINGLETON_DICTIONARY.isReaderAssignedTo(FileConfigClass.class));
            assertInstanceOf(ConfigurationFileReader.class, configurationReader);
        }

        @Test
        @DisplayName("Get the correct file configuration type")
        public void getCorrectFileConfigurationType() {
            assertEquals(ConfigurationFileSourceProvider.PROJECT_SOURCE, ConfigurationFileSourceProvider.getConformingFileSource(filePath));
        }
    }
}