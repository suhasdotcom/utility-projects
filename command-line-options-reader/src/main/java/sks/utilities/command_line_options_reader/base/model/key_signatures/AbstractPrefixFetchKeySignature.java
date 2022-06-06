package sks.utilities.command_line_options_reader.base.model.key_signatures;

import sks.utilities.command_line_options_reader.base.KeySignature;

import java.util.Set;

public abstract class AbstractPrefixFetchKeySignature implements KeySignature, PrefixKeySignature {
    public final String KEY_PREFIX = getPrefix();

    private final Set<String> assigners;

    public AbstractPrefixFetchKeySignature()
    {
        this(Set.of(""));
    }

    public AbstractPrefixFetchKeySignature(final Set<String> assigners)
    {
        this.assigners = assigners;
    }

    @Override
    public boolean isKey(String possibleKey) {
        boolean matches = false;
        String PREFIX_KEY_CHECKER_EXPRESSION = KEY_PREFIX + "[a-zA-Z\\d]+[-a-zA-Z\\d]*";
        for(String assigner: assigners) {
            if (possibleKey.matches(PREFIX_KEY_CHECKER_EXPRESSION + assigner)) {
                matches = true;
                break;
            }
        }
        return matches || possibleKey.matches(PREFIX_KEY_CHECKER_EXPRESSION);
    }

    @Override
    public String getKey(String mandatoryKey) {
        String currentAssigner = "";
        for(String assigner: assigners)
            if(mandatoryKey.endsWith(assigner)) {
                currentAssigner = assigner;
                break;
            }
        return mandatoryKey.substring(KEY_PREFIX.length(), mandatoryKey.length()-currentAssigner.length());
    }
}
