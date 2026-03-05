package domain;

import exception.ExceptionMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {

    private static final int MINIMUM_RANGE = 2;
    private static final int MAXIMUM_RANGE = 7;
    private static final Pattern FORMAT = Pattern.compile("^[a-zA-Zㄱ-힣]*$");

    private String value;

    //TODO: 중복된 이름 예외 처리 필요
    public Name(String value) {
        validate(value);
        this.value = value;
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

    public String getValue() {
        return value;
    }
}
