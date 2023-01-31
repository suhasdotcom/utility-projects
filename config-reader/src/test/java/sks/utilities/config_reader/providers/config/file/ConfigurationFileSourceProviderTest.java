package sks.utilities.config_reader.providers.config.file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import sks.utilities.config_reader.exceptions.providers.config.file.IllegalFileSourcePatternException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConfigurationFileSourceProvider should")
public class ConfigurationFileSourceProviderTest
{
    @DisplayName("MAIN_CLASSPATH_LIKE should")
    @Nested
    class MAIN_CLASSPATH_LIKE_Test
    {
        @ParameterizedTest
        @DisplayName("only look for files with a simple pattern - positive test")
        @ValueSource(strings = {"app-config.cfg", "appConfig.properties", "someDir/app-conf.conf", "someDir/Configuration_New.json",
                                "rootDir/someDir/_filename-name-1.yaml"})
        public void testSimpleFileNamePattern(String fileName)
        {
            assertTrue(ConfigurationFileSourceProvider.MAIN_CLASSPATH_LIKE.getValidationFunc().test(fileName));
        }


        @ParameterizedTest
        @DisplayName("only look for files with a simple pattern - negative test")
        @ValueSource(strings = {":app-config.cfg", "/appConfig.properties", "/someDir/app-conf.conf", ":someDir/Configuration_New.json", "<someDir/Configuration_New.json",
                "~/rootDir/someDir/_filename-name-1.yaml"})
        public void testSimpleFileNamePatternNegative(String fileName)
        {
            assertFalse(ConfigurationFileSourceProvider.MAIN_CLASSPATH_LIKE.getValidationFunc().test(fileName));
        }
    }

    @DisplayName("PROJECT_SOURCE should")
    @Nested
    class PROJECT_SOURCE_Test
    {
        @ParameterizedTest
        @DisplayName("only look for files with a simple pattern - positive test")
        @ValueSource(strings = {":app-config.cfg", ":appConfig.properties", ":someDir/app-conf.conf", ":/someDir/Configuration_New.json",
                ":/someDir/_filename-name-1.yaml"})
        public void testSimpleFileNamePattern(String fileName)
        {
            assertTrue(ConfigurationFileSourceProvider.PROJECT_SOURCE.getValidationFunc().test(fileName));
        }


        @ParameterizedTest
        @DisplayName("only look for files with a simple pattern - negative test")
        @ValueSource(strings = {"app-config.cfg", "/appConfig.properties", "/someDir/app-conf.conf", "someDir/Configuration_New.json",
                "~/rootDir/someDir/_filename-name-1.yaml"})
        public void testSimpleFileNamePatternNegative(String fileName)
        {
            assertFalse(ConfigurationFileSourceProvider.PROJECT_SOURCE.getValidationFunc().test(fileName));
        }
    }

    @DisplayName("IllegalFileSourceException should")
    @Nested
    class IllegalFileSourcePatternExceptionTest
    {
        @ParameterizedTest
        @DisplayName("throw exceptions when encountering unregistered file patterns - positive test")
        @ValueSource(strings = {"/:app-config.cfg", "//:appConfig.properties", "<:someDir/app-conf.conf>", "</someDir/Configuration_New.json",
                ":>/someDir/_filename-name-1.yaml<"})
        public void testSimpleFileNamePattern(String fileName)
        {
            final IllegalFileSourcePatternException illegalFileSourcePatternException =
                    assertThrows(IllegalFileSourcePatternException.class, () -> ConfigurationFileSourceProvider.getConformingFileSource(fileName));
            assertEquals(String.format(IllegalFileSourcePatternException.ERROR_DETAIL, fileName), illegalFileSourcePatternException.getMessage());
        }
    }
}