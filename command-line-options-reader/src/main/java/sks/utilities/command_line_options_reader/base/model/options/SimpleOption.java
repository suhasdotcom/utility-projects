package sks.utilities.command_line_options_reader.base.model.options;

import sks.utilities.command_line_options_reader.base.Option;
import sks.utilities.command_line_options_reader.base.model.errors.ErrorMessagesAndConstants;

import java.util.Collections;
import java.util.List;

public class SimpleOption implements Option {
    private final String name;
    private boolean required = false;
    private boolean valued = false;
    private Character singleLetterOptionName = null;
    private String helpText = null;
    private List<String> allowedValues = Collections.emptyList();
    private String configuredValue = null;
    private boolean isPresent = false;

    public SimpleOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getConfiguredValue() {
        return configuredValue;
    }

    @Override
    public void setConfiguredValue(String configuredValue) {
        if(allowedValues().size()==0 || allowedValues().contains(configuredValue))
            this.configuredValue = configuredValue;
        else
            throw new IllegalArgumentException(String.format(ErrorMessagesAndConstants.GIVEN_VALUE_IS_NOT_IN_THE_SPECIFIED_ALLOWED_VALUES_LIST, configuredValue, String.join(", ", allowedValues())));
    }

    @Override
    public boolean isPresent() {
        return isPresent;
    }

    @Override
    public void makePresent() {
        this.isPresent = true;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public void makeRequired() {
        required = true;
    }

    @Override
    public char getSingleLetterOptionName() {
        return singleLetterOptionName;
    }

    @Override
    public void setSingleLetterOptionName(char singleLetterOption) {
        this.singleLetterOptionName = singleLetterOption;
    }

    @Override
    public String getHelpText() {
        return this.helpText;
    }

    @Override
    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    @Override
    public boolean isValued() {
        return valued;
    }

    @Override
    public void makeValued() {
        valued = true;
    }

    @Override
    public List<String> allowedValues() {
        return this.allowedValues;
    }

    @Override
    public void allowValues(List<String> optionAllowedValues) {
        this.allowedValues = optionAllowedValues;
    }
}
