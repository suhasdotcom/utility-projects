package sks.utilities.command_line_options_reader.base;

import sks.utilities.command_line_options_reader.base.model.errors.ErrorMessagesAndConstants;
import sks.utilities.command_line_options_reader.base.model.options.SimpleOption;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface Option
{

    static Option getOptionWithName(String name) {
        return new SimpleOption(name);
    }

    static void allRulesMustBeSatisfied(final Collection<Option> options) {
        allRequiredMustBeSatisfied(options);
        allValuedMustBeSatisfied(options);
    }

    static void allValuedMustBeSatisfied(final Collection<Option> options) {
        for(Option option: options)
            if(option.isRequired() && option.isValuedNotSatisfied())
                throw new IllegalStateException(String.format(ErrorMessagesAndConstants.OPTION_REQUIRES_A_VALUE_WHICH_IS_NOT_PROVIDED, option.getName()));
    }

    static void allRequiredMustBeSatisfied(final Collection<Option> options) {
        for(Option option: options)
            if(option.isRequiredNotSatisfied())
                throw new IllegalStateException(String.format(ErrorMessagesAndConstants.MANDATORY_OPTION_IS_NOT_PROVIDED, option.getName()));
    }

    static boolean areAllRulesSatisfied(final Collection<Option> options) {
        return areAllRequiredSatisfied(options) && areAllValuedSatisfied(options);
    }

    static boolean areAllValuedSatisfied(final Collection<Option> options) {
        for(Option option: options)
            if(option.isRequired() && option.isValuedNotSatisfied())
                return false;
        return true;
    }

    static boolean areAllRequiredSatisfied(final Collection<Option> options) {
        for(Option option: options)
            if(option.isRequiredNotSatisfied())
                return false;
        return true;
    }

    default boolean isRequiredNotSatisfied() {
        return isRequired() && !isPresent();
    }

    default boolean isValuedNotSatisfied() {
        return isValued() && Objects.isNull(getConfiguredValue());
    }

    boolean isRequired();
    void makeRequired();

    char getSingleLetterOptionName();
    void setSingleLetterOptionName(char singleLetterOption);

    String getHelpText();
    void setHelpText(String helpText);

    boolean isValued();
    void makeValued();

    List<String> allowedValues();
    void allowValues(List<String> optionAllowedValues);

    String getName();

    String getConfiguredValue();
    void setConfiguredValue(String configuredValue);

    boolean isPresent();
    void makePresent();
}
