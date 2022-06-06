package sks.utilities.command_line_options_reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import sks.utilities.command_line_options_reader.base.Option;
import sks.utilities.command_line_options_reader.base.Parser;
import sks.utilities.command_line_options_reader.base.model.errors.ErrorMessagesAndConstants;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CommandLineOptionsReader should")
class CommandLineOptionsReaderTest
{
    @Nested
    @DisplayName("Test class builder's transitivity")
    class CommandLineOptionsReaderBuilderTest
    {
        private CommandLineOptionsReader reader;

        @BeforeEach
        @DisplayName("Initialize with Builder")
        void setUp()
        {
            this.reader = new CommandLineOptionsReader.CommandLineOptionsReaderBuilder()
                .addOption("first").isValued().isRequired()
                .addOption("second").mustOnlyContainValues("1", "2", "3").withSingleLetterOption("s")
                    .build();
        }

        @Test
        @DisplayName("check allowed values")
        void testBuilderTransitivityForAllowedValues(){
            assertIterableEquals(List.of("1", "2", "3"), reader.getConfiguredOptions().get("second").allowedValues());
        }

        @Test
        @DisplayName("check required option")
        void testBuilderTransitivityForOptionRequired() {
            assertTrue(reader.getConfiguredOptions().get("first").isRequired());
        }

        @Test
        @DisplayName("check valued")
        void testBuilderTransitivityForValued() {
            assertTrue(reader.getConfiguredOptions().get("first").isValued());
        }

        @Test
        @DisplayName("check not required for not required option")
        void testBuilderTransitivityForRequiredNegative() {
            assertFalse(reader.getConfiguredOptions().get("second").isRequired());
        }

        @Test
        @DisplayName("check that allowed values must also be valued")
        void testBuilderTransitivityForAllowedValuesToBeValued() {
            assertTrue(reader.getConfiguredOptions().get("second").isValued());
        }

        @Test
        @DisplayName("check single-letter-option is working correctly")
        void testSingleLetterOption(){
            final Map<String, Option> singleLetterOptions = reader.getSingleLetterOptions();
            final Map<String, Option> configuredOptions = reader.getConfiguredOptions();

            assertTrue(singleLetterOptions.containsKey("s"));
            assertEquals(1, singleLetterOptions.size());
            assertEquals(configuredOptions.get("second"), singleLetterOptions.get("s"));
        }

        @Test
        @DisplayName("check that isPresent must initially be false")
        void testIsPresentNegative() {
            assertFalse(reader.getConfiguredOptions().get("first").isPresent());
        }

        @Test
        @DisplayName("prohibit un-valued options to be made as a required option")
        void prohibitUnValuedOptionToRequiredOption() {
            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> this.reader = new CommandLineOptionsReader.CommandLineOptionsReaderBuilder()
                    .addOption("first").isValued().isRequired()
                    .addOption("second").mustOnlyContainValues("1", "2", "3").withSingleLetterOption("s")
                    .addOption("third").withSingleLetterOption('t').isRequired()
                    .build());

            assertEquals(String.format(ErrorMessagesAndConstants.ATTEMPTED_TO_MAKE_A_NOT_VALUED_OPTION_AS_A_REQUIRED_OPTION, "third"),
                    illegalArgumentException.getMessage());
        }
    }

    @Nested
    @DisplayName("Test CommandLineOptionsReader's parsing prowess")
    class CommandLineOptionsReaderProwess
    {
        private CommandLineOptionsReader reader;

        @BeforeEach
        @DisplayName("Initialize with Builder")
        void setUp()
        {
            this.reader = new CommandLineOptionsReader.CommandLineOptionsReaderBuilder()
                    .addOption("first").isValued().isRequired()
                    .addOption("second").mustOnlyContainValues("1", "2", "3").withSingleLetterOption("s")
                    .build();
        }

        @Test
        @DisplayName("read options correctly")
        void readOptionsCorrectly()
        {
            final Parser<String, Map<String, Option>> parser = this.reader.getCommandLineParser();
            final Map<String, Option> parsedMap = parser.parse("--first 1 -s=3");
            final Option firstOption = parsedMap.get("first");
            final Option secondOption = parsedMap.get("second");
            assertTrue(firstOption.isPresent());
            assertTrue(firstOption.isValued());
            assertFalse(firstOption.isRequiredNotSatisfied());
            assertFalse(firstOption.isValuedNotSatisfied());
        }
    }
}