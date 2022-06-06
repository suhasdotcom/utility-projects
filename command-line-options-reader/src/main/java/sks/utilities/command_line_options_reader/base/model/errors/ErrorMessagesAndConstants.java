package sks.utilities.command_line_options_reader.base.model.errors;

public final class ErrorMessagesAndConstants
{
    public static final String ATTEMPTED_TO_MAKE_A_NOT_VALUED_OPTION_AS_A_REQUIRED_OPTION = "Attempted to make Option: '%s' a required option. An option can only be a required option if it's a valued option";

    private ErrorMessagesAndConstants() {}
    public static final String KEY_NOT_CONFIGURED_DURING_INITIALIZATION_BUT_FOUND_DURING_PARSING_TERMINATING_NOW = "Key: %s is not configured during initialization but is found in command during parsing, terminating ...";
    public static final String GIVEN_VALUE_IS_NOT_IN_THE_SPECIFIED_ALLOWED_VALUES_LIST = "Given value '%s' is not in the specified allowed values list: {%s}";
    public static final String OPTION_REQUIRES_A_VALUE_WHICH_IS_NOT_PROVIDED = "Option: '%s' requires a value, which is not provided";
    public static final String MANDATORY_OPTION_IS_NOT_PROVIDED = "Option: '%s' is a mandatory option which is not provided";
}
