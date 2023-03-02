package domain;

import java.util.Objects;

public class Name {

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        validateIsNullOrBlank(name);
        validateNameLength(name);
    }

    private void validateIsNullOrBlank(final String name) {
        if (Objects.isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 10자를 초과할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }
}
