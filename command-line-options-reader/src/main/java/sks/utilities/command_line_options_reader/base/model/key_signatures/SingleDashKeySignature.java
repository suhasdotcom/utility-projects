package sks.utilities.command_line_options_reader.base.model.key_signatures;

import sks.utilities.command_line_options_reader.base.KeySignature;

import java.util.Set;

public class SingleDashKeySignature extends AbstractPrefixFetchKeySignature implements KeySignature, PrefixKeySignature
{
    public static final String SINGLE_DASH = "-";

    public SingleDashKeySignature(Set<String> assigners) {
        super(assigners);
    }

    public SingleDashKeySignature() {
        super();
    }

    @Override
    public String getPrefix() {
        return SINGLE_DASH;
    }
}
