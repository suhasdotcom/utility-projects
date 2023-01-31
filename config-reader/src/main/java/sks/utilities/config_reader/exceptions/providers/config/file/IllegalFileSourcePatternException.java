package sks.utilities.config_reader.exceptions.providers.config.file;

public class IllegalFileSourcePatternException extends IllegalArgumentException {

    public static final String ERROR_DETAIL_STR = "Filepath pattern provided: %s \n doesn't match the listed file path patterns. Please go through the (Refreshing) config reader's specifications";

    public IllegalFileSourcePatternException(String intendedFilePath) {
        super(makeErrorMessageFrom(intendedFilePath));
    }

    public static String makeErrorMessageFrom(String intendedFilePath) {
        return String.format(ERROR_DETAIL_STR, intendedFilePath);
    }
}
