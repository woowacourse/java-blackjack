package domain.participant;

import view.ExceptionMessage;

import java.util.Objects;

public class Name {

    private static final int MAX_PLAYER_NAME_LENGTH = 10;
    private static final String INVALID_NAME_CHARACTER = ",";
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    void validate(String name) {
        validateNotNull(name);
        validateNotEmpty(name);
        validateDoesNotContainComma(name);
        validateNameLength(name);
    }

    private void validateNotNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_IS_NULL.getMessage());
        }
    }

    private void validateNotEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_IS_EMPTY.getMessage());
        }
    }


    private void validateDoesNotContainComma(String name) {
        if (name.contains(INVALID_NAME_CHARACTER)) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_CONTAINS_COMMA.getMessage());
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NAME_LENGTH.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
