package domain;

import java.util.Random;

public class Name {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 5;
    private static final String NAME_OUT_OF_RANGE_ERROR_MESSAGE = "이름은 1 ~ 5글자 사이여야 합니다.";

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (isNameOutOfRange(name)) {
           throw new IllegalArgumentException(NAME_OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }

    private boolean isNameOutOfRange(String name) {
        return !(MIN_LENGTH <= name.length() && name.length() <= MAX_LENGTH);
    }

    public String getName() {
        return name;
    }

}
