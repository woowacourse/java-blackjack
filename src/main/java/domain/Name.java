package domain;

import java.util.Objects;

public class Name {

    public static final Name DEALER = new Name("딜러");
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 5;
    private static final String NAME_OUT_OF_RANGE_ERROR_MESSAGE = String.format("이름은 %d ~ %d글자 사이여야 합니다.",
            MIN_LENGTH, MAX_LENGTH);

    private final String value;

    public Name(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String name) {
        if (isNameOutOfRange(name)) {
            throw new IllegalArgumentException(NAME_OUT_OF_RANGE_ERROR_MESSAGE);
        }
    }

    private boolean isNameOutOfRange(String name) {
        return name.length() < MIN_LENGTH || name.length() > MAX_LENGTH;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
