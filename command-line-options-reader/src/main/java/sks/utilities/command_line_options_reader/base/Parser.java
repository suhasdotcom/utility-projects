package sks.utilities.command_line_options_reader.base;

import sks.utilities.command_line_options_reader.base.model.parsers.UnixLikeCommandLineOptionsStringMapperParser;

import java.util.Map;
import java.util.Set;

public interface Parser<InputType, OutputType>
{
    static Parser<String, Map<String, Option>> getUnixLikeCommandLineOptionsStringMapperParser() {
        return new UnixLikeCommandLineOptionsStringMapperParser();
    }

    OutputType parse(final InputType inputType);

    void initializeWithRules(Map<InputType, OutputType> rules);

    Set<InputType> getAssigners();

    void setAssigners(final Set<InputType> assigners);

    Map<String, KeySignature> getKeySignatureMap();

    void setKeySignatureMap(final Map<String, KeySignature> keySignatureMap);

    default int getKeySignatureMapSize() {
        return getKeySignatureMap().size();
    }
}
