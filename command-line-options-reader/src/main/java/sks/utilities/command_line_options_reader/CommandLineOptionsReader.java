package sks.utilities.command_line_options_reader;

import sks.utilities.commons.base.Builder;
import sks.utilities.command_line_options_reader.base.Option;
import sks.utilities.command_line_options_reader.base.Parser;
import sks.utilities.command_line_options_reader.base.model.errors.ErrorMessagesAndConstants;
import sks.utilities.command_line_options_reader.base.model.rules.RuleConstants;

import java.util.*;

/**
 * {@link CommandLineOptionsReader} use to read options on command line. The object can be built to use different option configurations to read from using its builder
 * @author suhas
 * @since 1.0
 */
public class CommandLineOptionsReader
{
    /**
     * The options this class is to be initialized with
     */
    private final Map<String, Option> configuredOptions;

    /**
     * single-letter options this class is to be initialized with
     */
    private final Map<String, Option> singleLetterOptions;


    private final Map<String, Map<String, Option>> rules;

    /**
     * the main pluggable parser that'll be used to parse command line
     */
    private final Parser<String, Map<String, Option>> commandLineParser;

    /**
     * {@link CommandLineOptionsReader} can only be built by using its builder {@link CommandLineOptionsReaderBuilder}
     * @param commandLineOptionsReaderBuilder {@link CommandLineOptionsReaderBuilder builder} that initializes {@link CommandLineOptionsReader}
     */
    private CommandLineOptionsReader(final CommandLineOptionsReaderBuilder commandLineOptionsReaderBuilder) {
        this.configuredOptions = commandLineOptionsReaderBuilder.options;
        this.singleLetterOptions = commandLineOptionsReaderBuilder.singleLetterOptions;
        this.commandLineParser = commandLineOptionsReaderBuilder.commandLineOptionsParser;
        this.rules = Map.of(RuleConstants.SIMPLE_RULES, commandLineOptionsReaderBuilder.singleLetterOptions,
                RuleConstants.VERBOSE_RULES, commandLineOptionsReaderBuilder.options);
        this.commandLineParser.initializeWithRules(this.rules);
    }

    public static class CommandLineOptionsReaderBuilder implements Builder<CommandLineOptionsReader>
    {
        private String currentOptionName;
        private final Map<String, Option> options = new HashMap<>();
        private final Map<String, Option> singleLetterOptions = new HashMap<>();

        private Parser<String, Map<String, Option>> commandLineOptionsParser = Parser.getUnixLikeCommandLineOptionsStringMapperParser();

        public CommandLineOptionsReaderBuilder addOption(String name)
        {
            Option op = Option.getOptionWithName(name);
            options.put(name, op);
            return this.forOptionName(name);
        }

        private CommandLineOptionsReaderBuilder forOptionName(String name) {
            this.setCurrentOption(name);
            return this;
        }

        public CommandLineOptionsReaderBuilder isRequired()
        {
            if(getCurrentOption().isValued())
                getCurrentOption().makeRequired();
            else
                throw new IllegalArgumentException(String.format(ErrorMessagesAndConstants.ATTEMPTED_TO_MAKE_A_NOT_VALUED_OPTION_AS_A_REQUIRED_OPTION, getCurrentOption().getName()));
            return this;
        }

        public CommandLineOptionsReaderBuilder withSingleLetterOption(String singleLetterOption)
        {
            if(singleLetterOption.length()>1)
                throw new IllegalArgumentException("Single Letter Option must be of single letter, current string given: "+singleLetterOption);

            return withSingleLetterOption(singleLetterOption.charAt(0));
        }

        public CommandLineOptionsReaderBuilder withSingleLetterOption(char singleLetterOption)
        {
            getCurrentOption().setSingleLetterOptionName(singleLetterOption);
            this.singleLetterOptions.put(singleLetterOption+"", getCurrentOption());
            return this;
        }

        public CommandLineOptionsReaderBuilder withHelpText(String helpText)
        {
            getCurrentOption().setHelpText(helpText);
            return this;
        }

        public CommandLineOptionsReaderBuilder isValued()
        {
            getCurrentOption().makeValued();
            return this;
        }

        public CommandLineOptionsReaderBuilder mustOnlyContainValues(final String ... optionAllowedValues)
        {
            return mustOnlyContainValues(Arrays.asList(optionAllowedValues));
        }

        public CommandLineOptionsReaderBuilder mustOnlyContainValues(final List<String> optionAllowedValues)
        {
            this.isValued().getCurrentOption().allowValues(optionAllowedValues);
            return this;
        }

        @Override
        public CommandLineOptionsReader build() {
            return new CommandLineOptionsReader(this);
        }

        public void setCurrentOption(String name) {
            this.currentOptionName = name;
        }

        public Option getCurrentOption() {
            return options.get(currentOptionName);
        }

        public Parser<String, Map<String, Option>> getCommandLineOptionsParser() {
            return this.commandLineOptionsParser;
        }

        public CommandLineOptionsReaderBuilder setCommandLineOptionsParser(final Parser<String, Map<String, Option>> commandLineOptionsParser) {
            this.commandLineOptionsParser = commandLineOptionsParser;
            return this;
        }
    }

    public Map<String, Option> getConfiguredOptions() {
        return configuredOptions;
    }

    public Map<String, Option> getSingleLetterOptions() {
        return singleLetterOptions;
    }

    public Parser<String, Map<String, Option>> getCommandLineParser() {
        return commandLineParser;
    }

    public Map<String, Map<String, Option>> getRules() {
        return rules;
    }
}
