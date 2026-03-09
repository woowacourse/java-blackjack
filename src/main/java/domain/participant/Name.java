package domain.participant;

import static domain.Constant.NAME_MAX_LENGTH;
import static domain.Constant.NAME_MIN_LENGTH;

import domain.ExceptionMessage;

public class Name {
    private final String value;

    public Name(String name) {
        validate(name);
        this.value = name.strip();
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.BLANK_NAME_NOT_ALLOWED.getMessage());
        }
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_OUT_OF_RANGE.getMessage());
        }
    }

    public String getValue() {
        return value;
    }
}
