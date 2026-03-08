package domain.participant;

import exception.ExceptionMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Name(String value) {

    private static final int MINIMUM_RANGE = 2;
    private static final int MAXIMUM_RANGE = 7;
    private static final Pattern FORMAT = Pattern.compile("^[a-zA-Zㄱ-힣]*$");

    public Name {
        validate(value);
    }

    private void validate(String value) {
        validateNameRange(value);
        validateNameFormat(value);
    }

    private void validateNameRange(String value) {
        if (value.length() < MINIMUM_RANGE || value.length() > MAXIMUM_RANGE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NAME_RANGE.getMessage());
        }
    }

    private void validateNameFormat(String value) {
        Matcher matcher = FORMAT.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NAME_FORMAT.getMessage());
        }
    }
}
