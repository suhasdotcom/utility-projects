package sks.utilities.command_line_options_reader.base.model.key_signatures;

import sks.utilities.command_line_options_reader.base.KeySignature;

import java.util.Set;

public class DoubleDashKeySignature extends AbstractPrefixFetchKeySignature implements KeySignature, PrefixKeySignature
{
    public static final String DOUBLE_DASH = "--";

    public DoubleDashKeySignature() {}

    public DoubleDashKeySignature(Set<String> assigners) {
        super(assigners);
    }

    @Override
    public String getPrefix() {
        return DOUBLE_DASH;
    }
}
