package sks.utilities.config_reader.exceptions.readers.config.file;

import sks.utilities.config_reader.model.reader.ConfigurationReader;

public class DuplicateConfigurationReaderException extends IllegalArgumentException {

    public static final String ERROR_DETAIL_STR = "'%s' Reader already for class '%s' \nReader reassignment " +
            "not allowed at runtime. Please go through the (Refreshing) config reader's specifications";

    public DuplicateConfigurationReaderException(final String fullClassName, final ConfigurationReader configurationReader) {
        super(makeErrorMessageFrom(fullClassName, configurationReader.identifySelf()));
    }

    public static String makeErrorMessageFrom(final String intendedFilePath, final String configurationReaderIdentity) {
        return ERROR_DETAIL_STR.formatted(intendedFilePath, configurationReaderIdentity);
    }
}
