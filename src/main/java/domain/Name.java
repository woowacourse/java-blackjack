package domain;

public class Name {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 5;
    private static final String NAME_OUT_OF_RANGE_ERROR_MESSAGE = "이름은 1 ~ 5글자 사이여야 합니다.";

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
        return MIN_LENGTH > name.length() || MAX_LENGTH < name.length();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }

        return this.value.equals(((Name) object).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
