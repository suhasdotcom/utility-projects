package sks.utilities.command_line_options_reader.base;

public interface KeySignature
{
    boolean isKey(String possibleKey);
    String getKey(String mandatoryKey);
}
