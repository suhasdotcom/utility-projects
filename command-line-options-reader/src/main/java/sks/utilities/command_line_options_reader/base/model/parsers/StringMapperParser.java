package sks.utilities.command_line_options_reader.base.model.parsers;

import sks.utilities.command_line_options_reader.base.KeySignature;
import sks.utilities.command_line_options_reader.base.Option;
import sks.utilities.command_line_options_reader.base.Parser;
import sks.utilities.command_line_options_reader.base.model.errors.ErrorMessagesAndConstants;
import sks.utilities.command_line_options_reader.base.model.key_signatures.DoubleDashKeySignature;
import sks.utilities.command_line_options_reader.base.model.key_signatures.SingleDashKeySignature;
import sks.utilities.command_line_options_reader.base.model.rules.RuleConstants;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMapperParser implements Parser<String, Map<String, Option>>
{
    private Set<String> assigners = Set.of(" ", "=");
    private Map<String, KeySignature> keySignatureMap = Map.of(RuleConstants.SIMPLE_RULES, new SingleDashKeySignature(assigners),
            RuleConstants.VERBOSE_RULES, new DoubleDashKeySignature(assigners));
    private Map<String, Map<String, Option>> rules = new HashMap<>();

    /**
     * Will support commands as {@code --param 1 -v 1 -a --exists -b=2 --done=3 --file-path="some file path"}
     * @param commandsAndValues full commands with - and -- unix-like commands
     * @return a well-configured key-value pair for commands provided
     */
    @Override
    public Map<String, Option> parse(String commandsAndValues) {
        Map<String, Option> parsedMap = new HashMap<>();

        String[] commandsAndValuesSeparated = getInputSplitByAssigners(commandsAndValues);

        for(int i = 0; i< commandsAndValuesSeparated.length; i++)
        {
            int keyMiss = 1;
            for(String ruleName: keySignatureMap.keySet())
            {
                final KeySignature keySignature = keySignatureMap.get(ruleName);
                if(keySignature.isKey(commandsAndValuesSeparated[i]))
                {
                    keyMiss=1;
                    String key = keySignature.getKey(commandsAndValuesSeparated[i]);
                    Map<String, Option> rule = rules.get(ruleName);
                    if(rule.containsKey(key))
                    {
                        Option option = rule.get(key);
                        option.makePresent();
                        if(option.isValued()) {
                            i++;
                            for(KeySignature internalKeySignature: keySignatureMap.values())
                                if(internalKeySignature.isKey(commandsAndValuesSeparated[i]))
                                    throw new IllegalStateException(String.format(ErrorMessagesAndConstants.OPTION_REQUIRES_A_VALUE_WHICH_IS_NOT_PROVIDED,
                                            internalKeySignature.getKey(commandsAndValuesSeparated[--i])));

                            if(assigners.contains(commandsAndValuesSeparated[i])) i++;

                            String configuredValue = commandsAndValuesSeparated[i];
                            for(String assigner: assigners) {
                                if (commandsAndValuesSeparated[i].contains(assigner)) {
                                    configuredValue = commandsAndValuesSeparated[i].split(assigner)[1];
                                    break;
                                }
                            }
                            option.setConfiguredValue(configuredValue);
                        }

                        parsedMap.put(option.getName(), option);
                    }
                    else
                        throw new IllegalArgumentException(String.format(ErrorMessagesAndConstants.KEY_NOT_CONFIGURED_DURING_INITIALIZATION_BUT_FOUND_DURING_PARSING_TERMINATING_NOW, key));
                }
                else if(keyMiss>=getKeySignatureMapSize())
                    throw new IllegalArgumentException(String.format(ErrorMessagesAndConstants.KEY_NOT_CONFIGURED_DURING_INITIALIZATION_BUT_FOUND_DURING_PARSING_TERMINATING_NOW, commandsAndValuesSeparated[i]));
                else
                    keyMiss++;
            }
        }

        for(Map<String, Option> rule: rules.values())
            Option.allRulesMustBeSatisfied(rule.values());

        return parsedMap;
    }

    public String[] getInputSplitByAssigners(String commandsAndValues) {
        List<String> commandsAndValuesSeparatedList = new LinkedList<>();
        Matcher matcher = Pattern.compile("[^\\s\"'"+getAssigners()+"]+|\"([^\"]*)\"|'([^']*)'").matcher(commandsAndValues);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Add double-quoted string without the quotes
                commandsAndValuesSeparatedList.add(matcher.group(1));
            } else if (matcher.group(2) != null) {
                // Add single-quoted string without the quotes
                commandsAndValuesSeparatedList.add(matcher.group(2));
            } else {
                // Add unquoted word
                commandsAndValuesSeparatedList.add(matcher.group());
            }
        }

        return commandsAndValuesSeparatedList.toArray(String[]::new);
    }

    @Override
    public void initializeWithRules(Map<String, Map<String, Option>> rules) {
        this.rules = rules;
    }

    public Set<String> getAssigners() {
        return assigners;
    }

    public void setAssigners(final Set<String> assigners) {
        this.assigners = assigners;
    }

    @Override
    public Map<String, KeySignature> getKeySignatureMap() {
        return keySignatureMap;
    }

    @Override
    public void setKeySignatureMap(final Map<String, KeySignature> keySignatureMap) {
        this.keySignatureMap = keySignatureMap;
    }
}
