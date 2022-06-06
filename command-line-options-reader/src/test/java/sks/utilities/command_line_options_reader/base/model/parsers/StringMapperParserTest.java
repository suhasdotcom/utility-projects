package sks.utilities.command_line_options_reader.base.model.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sks.utilities.command_line_options_reader.CommandLineOptionsReader;
import sks.utilities.command_line_options_reader.base.Option;
import sks.utilities.command_line_options_reader.base.Parser;
import sks.utilities.command_line_options_reader.base.model.errors.ErrorMessagesAndConstants;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StringMapperParser should")
class StringMapperParserTest
{
    private Parser<String, Map<String, Option>> sut;
    private CommandLineOptionsReader commandLineOptionsReader;

    @BeforeEach
    void setUp()
    {
        commandLineOptionsReader = new CommandLineOptionsReader.CommandLineOptionsReaderBuilder()
                .addOption("first").isValued().isRequired()
                .addOption("second").mustOnlyContainValues("1", "2", "3").withSingleLetterOption("s")
                .build();
        sut = new StringMapperParser();
        sut.initializeWithRules(commandLineOptionsReader.getRules());
    }

    @Test
    @DisplayName("parse command where a required value is provided")
    void passTestForProvidedRequiredValue()
    {
        final Map<String, Option> valueMap = sut.parse("--first 1");
        assertEquals("1", valueMap.get("first").getConfiguredValue());
    }

    @Test
    @DisplayName("throw error when required option is not provided")
    void failTestForOmittedRequiredOption()
    {
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> sut.parse("--second 1"));
        assertEquals(String.format(ErrorMessagesAndConstants.MANDATORY_OPTION_IS_NOT_PROVIDED, "first"),
                illegalStateException.getMessage());
    }

    @Test
    @DisplayName("throw error when value for a valued-option is not provided")
    void failTestForOmittedRequiredValue()
    {
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> sut.parse("--first --second 1"));
        assertEquals(String.format(ErrorMessagesAndConstants.OPTION_REQUIRES_A_VALUE_WHICH_IS_NOT_PROVIDED, "first"),
                illegalStateException.getMessage());
    }

    @Test
    @DisplayName("only allowed values must be accepted - positive test")
    void passTestForAllowedValues()
    {
        final Map<String, Option> valueMaps = sut.parse("--first 1 --second 1");
        assertEquals("1", valueMaps.get("second").getConfiguredValue());
    }

    @Test
    @DisplayName("throw error when a not allowed value is encountered")
    void failTestForNotAllowedValues()
    {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> sut.parse("--first 1 --second 10"));
        final List<String> allowedValues = commandLineOptionsReader.getConfiguredOptions().get("second").allowedValues();
        assertEquals(String.format(ErrorMessagesAndConstants.GIVEN_VALUE_IS_NOT_IN_THE_SPECIFIED_ALLOWED_VALUES_LIST, 10, String.join(", ", allowedValues)),
                illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("work correctly for single-letter-option - positive test")
    void recognizeSingleLetterOptionCorrectly()
    {
        final Map<String, Option> valueMaps = sut.parse("--first 1 -s 1");
        assertEquals("1", valueMaps.get("second").getConfiguredValue());
    }

    @Test
    @DisplayName("identify unrecognized single-letter-option")
    void identifyUnrecognizedSingleLetterOption()
    {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> sut.parse("--first 1 -s 1 -c"));
        assertEquals(String.format(ErrorMessagesAndConstants.KEY_NOT_CONFIGURED_DURING_INITIALIZATION_BUT_FOUND_DURING_PARSING_TERMINATING_NOW, "c"),
                illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("assigners must work correctly for verbose options, space after assigner")
    void testWhetherAssignersWorkCorrectlyForVerboseOptionsSpaceAfterAssigner()
    {
        final Map<String, Option> valueMaps = sut.parse("--first= 1 -s 1");
        assertEquals("1", valueMaps.get("first").getConfiguredValue());
    }

    @Test
    @DisplayName("assigners must work correctly for verbose options, space before assigner")
    void testWhetherAssignersWorkCorrectlyForVerboseOptionsSpaceBeforeAssigner()
    {
        final Map<String, Option> valueMaps = sut.parse("--first =1 -s 1");
        assertEquals("1", valueMaps.get("first").getConfiguredValue());
    }

    @Test
    @DisplayName("assigners must work correctly for verbose options, space before and after assigner")
    void testWhetherAssignersWorkCorrectlyForVerboseOptionsSpaceBeforeAndAfterAssigner()
    {
        final Map<String, Option> valueMaps = sut.parse("--first = 1 -s 1");
        assertEquals("1", valueMaps.get("first").getConfiguredValue());
    }

    @Test
    @DisplayName("assigners must work correctly for verbose options, with no space before and after assigner")
    void testWhetherAssignersWorkCorrectlyForVerboseOptionsWithNoSpaceBeforeAndAfterAssigner()
    {
        final Map<String, Option> valueMaps = sut.parse("--first=1 -s 1");
        assertEquals("1", valueMaps.get("first").getConfiguredValue());
    }

    @Test
    @DisplayName("check for all inputs split by assigners")
    void testWhetherSplitterIsWorkingCorrectly()
    {
        String[] split = new StringMapperParser().getInputSplitByAssigners("--first=1 -s   1 -three  =2  --four=forty-three");
        assertArrayEquals(new String[]{"--first", "1", "-s", "1", "-three", "2", "--four", "forty-three"}, split);
    }

    @Test
    @DisplayName("check all string-like inputs are split by assigners correctly")
    void testWhetherSplitterIsWorkingCorrectlyForStringValues()
    {
        String[] split = new StringMapperParser().getInputSplitByAssigners("--first \"1 2 3 4\" -s   1 -three  =2  --four=forty-three");
        assertArrayEquals(new String[]{"--first", "1 2 3 4", "-s", "1", "-three", "2", "--four", "forty-three"}, split);
    }

    @Test
    @DisplayName("check all string-like inputs are split by assigners correctly without spaces")
    void testWhetherSplitterIsWorkingCorrectlyForStringValuesWithputSpaces()
    {
        String[] split = new StringMapperParser().getInputSplitByAssigners("--first=\"1 2 3 4\" -s   1 -three  =2  --four=forty-three");
        assertArrayEquals(new String[]{"--first", "1 2 3 4", "-s", "1", "-three", "2", "--four", "forty-three"}, split);
    }

    @Test
    @DisplayName("unexpected key found in middle of command")
    void testUnexpectedKey()
    {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> sut.parse("--first = 1 2 3 4 -s 1"));
        assertEquals(String.format(ErrorMessagesAndConstants.KEY_NOT_CONFIGURED_DURING_INITIALIZATION_BUT_FOUND_DURING_PARSING_TERMINATING_NOW, "2"), illegalArgumentException.getMessage());
    }
}