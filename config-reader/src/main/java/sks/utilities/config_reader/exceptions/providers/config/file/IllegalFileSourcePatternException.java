package sks.utilities.config_reader.exceptions.providers.config.file;

public class IllegalFileSourcePatternException extends IllegalArgumentException {

    public static final String ERROR_DETAIL_STR = "Filepath pattern provided: %s \n doesn't match the listed file path patterns. Please go through the (Refreshing) config reader's specifications";

    public IllegalFileSourcePatternException(final String intendedFilePath) {
        super(makeErrorMessageFrom(intendedFilePath));
    }

    public static String makeErrorMessageFrom(final String intendedFilePath) {
        return ERROR_DETAIL_STR.formatted(intendedFilePath);
    }
}
