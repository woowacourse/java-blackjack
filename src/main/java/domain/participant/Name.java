package domain.participant;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 5;
    private static final Pattern INVALID_NAME_PATTERN = Pattern.compile("\\P{L}");

    private final String name;

    public Name(String name) {
        validateLength(name);
        validateContainsSpace(name);
        validateNamePattern(name);
        this.name = name;
    }

    private void validateLength(String name) {
        int length = name.length();

        if (length < MIN_NAME_LENGTH || MAX_NAME_LENGTH < length) {
            throw new IllegalArgumentException(
                    String.format("rejected value : %s - 이름은 %d ~ %d 글자여야 합니다", name, MIN_NAME_LENGTH, MAX_NAME_LENGTH)
            );
        }
    }

    private void validateContainsSpace(String name) {
        if (name.contains(" ")) {
            throw new IllegalArgumentException(
                    String.format("rejected value : %s - 이름에 공백이 포함되어 있습니다.", name)
            );
        }
    }

    private void validateNamePattern(String name) {
        if (INVALID_NAME_PATTERN.matcher(name).find()) {
            throw new IllegalArgumentException(
                    String.format("rejected value : %s - 이름에 특수문자와 숫자는 허용하지 않습니다.", name)
            );
        }
    }

    public String getValue() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Name other) {
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
